package be.ipam.thepriceisright.controllers;

import be.ipam.thepriceisright.dto.PriceDto;
import be.ipam.thepriceisright.services.PriceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PriceController {
    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @PostMapping("/{productId}/prices")
    @ResponseStatus(HttpStatus.CREATED)
    public PriceDto addPrice(@PathVariable Long productId,
                             @RequestBody PriceDto priceDto) {
        return priceService.addPrice(productId, priceDto);
    }

    @DeleteMapping("/prices/{priceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePrice(@PathVariable Long priceId) {
        priceService.deletePrice(priceId);
    }
}
