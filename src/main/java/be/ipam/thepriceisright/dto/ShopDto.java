package be.ipam.thepriceisright.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link be.ipam.thepriceisright.models.Shop}
 */
@Value
public class ShopDto implements Serializable {
    Long id;
    String name;
}