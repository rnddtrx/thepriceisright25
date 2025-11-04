package be.ipam.thepriceisright.dto;

import lombok.Value;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link be.ipam.thepriceisright.models.Product}
 */
@Value
public class SpecialProductDto implements Serializable {
    Long id;
    String name;
    String description;
    Set<PriceDto> prices;
}