package be.ipam.thepriceisright.controllers;

import be.ipam.thepriceisright.dto.ProductDto;
import be.ipam.thepriceisright.dto.ShopDto;
import be.ipam.thepriceisright.services.ProductService;
import be.ipam.thepriceisright.services.ShopService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shops")
@Tag(name = "Shops", description = "Products management API")
public class ShopController {
    private final ShopService shopService;
    private final ProductService productService;

    public ShopController(ShopService shopService,
                          ProductService productService) {
        this.shopService = shopService;
        this.productService = productService;
    }

    //CRUD
    //READ by id
    @GetMapping("/{id}")
    public ShopDto findById(@PathVariable("id") Long id) {
        return shopService.findShopById(id);
    }



    //CREATE
    @PostMapping
    public ShopDto save(@RequestBody ShopDto shopDto) {
        return shopService.createShop(shopDto);
    }

    @GetMapping("/page")
    public Page<ShopDto> findAll(@ParameterObject Pageable pageable) {
        return shopService.listShops(pageable);
    }

    @GetMapping("/{shopId}/products")
    public Page<ProductDto> getProductsByShop(@PathVariable Long shopId,
                                              @PageableDefault(
                                                size = 20,
                                                sort = "name",
                                                direction = Sort.Direction.ASC
                                              )
                                              @ParameterObject Pageable pageable) {
        return productService.findByShop(shopId, pageable);
    }
}
