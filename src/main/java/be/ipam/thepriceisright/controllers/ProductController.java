package be.ipam.thepriceisright.controllers;

import be.ipam.thepriceisright.exceptions.ErrorResponse;
import be.ipam.thepriceisright.dto.ProductDto;
import be.ipam.thepriceisright.dto.ProductWithPriceDto;
import be.ipam.thepriceisright.dto.pagination.ProductWithPricePage;
import be.ipam.thepriceisright.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Products", description = "Products management API")
class ProductController {
    private final ProductService productService;

    // Constructeur et Injection
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //CRUD
    //READ by id
    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    }

    //READ by name
    @GetMapping("/by-name/{name}")
    public ProductDto findByName(@PathVariable("name") String name) {
        return productService.getProductByName(name);
    }

    //CREATE
    @PostMapping()
    public ProductDto save(@RequestBody ProductDto productDto) {
        return productService.addProduct(productDto);
    }

    //UPDATE
    @PutMapping("/{id}")
    public ProductDto update(@PathVariable("id") Long id, @RequestBody ProductDto productDto) {
        return productService.updateProduct(id, productDto);
    }

    //DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public void deleteById(@PathVariable("id") Long id) {
        productService.deleteProductById(id);
    }

    //Liste complète des produits :
    // Attention : peut être lourd si beaucoup de produits
    // => préférer la pagination voir méthode en dessous
    @GetMapping
    public List<ProductWithPriceDto> findAll() {
        return productService.getAllProductsDto();
    }

    // Liste paginée des produits :
    //Pagination des produits :
    //?page=1&size=10&sort=name,asc
    @GetMapping("/page")
    //Documentation OpenAPI
    @Operation(
            summary = "Get list of products with pagination",
            description = "Retrieve a paginated list of products with their prices."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Paginated list",
                    content = @Content(schema = @Schema(implementation = ProductWithPricePage.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public Page<ProductWithPriceDto> findAll(@ParameterObject Pageable pageable) {
        return productService.getProductWithPricePage(pageable);
    }
}