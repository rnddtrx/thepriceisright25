package be.ipam.thepriceisright.dto.pagination;

import be.ipam.thepriceisright.dto.ProductWithPriceDto;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Paginated ProductWithPriceDto response")
public class ProductWithPricePage extends PageResponse<ProductWithPriceDto> {
    public ProductWithPricePage() {
        super(null);
    }
}
