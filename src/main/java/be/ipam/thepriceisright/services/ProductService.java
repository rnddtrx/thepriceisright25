package be.ipam.thepriceisright.services;

import be.ipam.thepriceisright.dto.ProductDto;
import be.ipam.thepriceisright.dto.ProductWithPriceDto;
import be.ipam.thepriceisright.mappers.ProductMapper;
import be.ipam.thepriceisright.mappers.ProductWithPriceMapper1;
import be.ipam.thepriceisright.models.Product;
import be.ipam.thepriceisright.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<ProductWithPriceDto> getAllProductsDto() {
        return productRepository.findAll()
                .stream().map(productWithPriceMapper::toDto)
                .toList();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public ProductDto addProduct(ProductDto productDto) {
        Product product = productMapper.toEntity(productDto);
        product = productRepository.save(product);
        return productMapper.toDto(product);
    }
}
