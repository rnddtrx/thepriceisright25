package be.ipam.thepriceisright.dto;

import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link be.ipam.thepriceisright.models.Price}
 */
@Value
public class PriceDto implements Serializable {
    Long id;
    BigDecimal value;
}