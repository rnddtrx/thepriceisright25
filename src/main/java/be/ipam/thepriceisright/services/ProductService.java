package be.ipam.thepriceisright.services;

import be.ipam.thepriceisright.dto.ProductDto;
import be.ipam.thepriceisright.dto.ProductWithPriceDto;
import be.ipam.thepriceisright.exceptions.BadRequestException;
import be.ipam.thepriceisright.exceptions.ConflictException;
import be.ipam.thepriceisright.exceptions.ResourceNotFoundException;
import be.ipam.thepriceisright.mappers.ProductMapper;
import be.ipam.thepriceisright.mappers.ProductWithPriceMapper1;
import be.ipam.thepriceisright.models.Product;
import be.ipam.thepriceisright.repositories.ProductRepository;
import ch.qos.logback.classic.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;
    private final ProductWithPriceMapper1 productWithPriceMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper, ProductWithPriceMapper1 productWithPriceMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.productWithPriceMapper = productWithPriceMapper;
    }

    //CRUD - READ by id
    public ProductDto getProductById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    //CRUD - READ by name
    public ProductDto getProductByName(String name) {
       // Optional<Product> product = productRepository.findByName(name);
       // if (product.isEmpty()) {
       //     throw new ResourceNotFoundException("Product not found with name: " + name);
       // }
       // return productMapper.toDto(product.get());

        return productRepository.findByName(name)
                .map(productMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with name: " + name));
    }

    //Read page
    public Page<ProductWithPriceDto> getProductWithPricePage(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(productWithPriceMapper::toDto);
    }

    //CRUD - CREATE
    public ProductDto addProduct(ProductDto productDto) {
        //Teste si un objet avec le meme nom existe.
        if (productRepository.existsByName(productDto.getName())) {
            throw new ConflictException("A product with this name already exists: " + productDto.getName());
        }

        Product product = productMapper.toEntity(productDto);

        try {
            product = productRepository.save(product);
        } catch (DataIntegrityViolationException ex) {
            Throwable root = ex.getMostSpecificCause();
            String message = root.getMessage().toLowerCase();

            // Violation de contrainte unique : 409 Conflict
            if (message.contains("unique") || message.contains("duplicate") || message.contains("constraint")) {
                throw new ConflictException("A product with this name already exists: " + productDto.getName());
            }

            // Autres erreurs d'intégrité : 400 Bad Request
            throw new BadRequestException("Invalid product data: " + message);
        }

        return productMapper.toDto(product);
    }

    //CRUD - UPDATE
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        // Le produit avec l'id donné doit exister
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        // Vérifie si le nouveau nom existe déjà pour un autre produit
        if (!existingProduct.getName().equals(productDto.getName()) &&
                productRepository.existsByName(productDto.getName())) {
            throw new ConflictException("A product with this name already exists: " + productDto.getName());
        }

        productMapper.partialUpdate(productDto, existingProduct);

        try {
            existingProduct = productRepository.save(existingProduct);
        }
        catch (DataIntegrityViolationException ex) {
            Throwable root = ex.getMostSpecificCause();
            String message = root.getMessage().toLowerCase();

            // Violation de contrainte unique : 409 Conflict
            if (message.contains("unique") || message.contains("duplicate") || message.contains("constraint")) {
                throw new ConflictException("A product with this name already exists: " + productDto.getName());
            }

            // Autres erreurs d'intégrité : 400 Bad Request
            throw new BadRequestException("Invalid product data: " + message);
        }

        return productMapper.toDto(existingProduct);
    }

    //CRUD - DELETE
    public void deleteProductById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    //Autres méthodes

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<ProductWithPriceDto> getAllProductsDto() {
        return productRepository.findAll()
                .stream().map(productWithPriceMapper::toDto)
                .toList();
    }
}
