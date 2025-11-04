package be.ipam.thepriceisright.controllers;

import be.ipam.thepriceisright.dto.ProductDto;
import be.ipam.thepriceisright.dto.ProductWithPriceDto;
import be.ipam.thepriceisright.models.Product;
import be.ipam.thepriceisright.repositories.ProductRepository;
import be.ipam.thepriceisright.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
class ProductRestController {
    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductWithPriceDto> findAll() {
        return productService.getAllProductsDto();
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    }

    @PostMapping()
    public ProductDto save(@RequestBody ProductDto productDto) {
        ProductDto product = productService.addProduct(productDto);
        return product;
    }

}