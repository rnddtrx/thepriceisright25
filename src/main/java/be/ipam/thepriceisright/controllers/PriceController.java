package be.ipam.thepriceisright.controllers;

import be.ipam.thepriceisright.dto.PriceDto;
import be.ipam.thepriceisright.services.PriceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Tag(name = "Prices", description = "Prices management API")
public class PriceController {
    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @PostMapping("/products/{productId}/shops/{shopId}/prices")
    @ResponseStatus(HttpStatus.CREATED)
    public PriceDto addPrice(@PathVariable Long productId,
                             @PathVariable Long shopId,
                             @RequestBody PriceDto priceDto) {
        return priceService.addPrice(productId, shopId, priceDto);
    }

    @DeleteMapping("/prices/{priceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePrice(@PathVariable Long priceId) {
        priceService.deletePrice(priceId);
    }
}
