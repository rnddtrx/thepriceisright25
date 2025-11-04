package be.ipam.thepriceisright.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link be.ipam.thepriceisright.models.Product}
 */
@Value
public class ProductDto implements Serializable {
    Long id;
    String name;
    String description;
}