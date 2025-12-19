package be.ipam.thepriceisright.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link be.ipam.thepriceisright.models.AppRole}
 */
@Value
public class AppRoleDto implements Serializable {
    Long id;
    String name;
}