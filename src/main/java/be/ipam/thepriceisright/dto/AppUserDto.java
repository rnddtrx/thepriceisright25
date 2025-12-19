package be.ipam.thepriceisright.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link be.ipam.thepriceisright.models.AppUser}
 */
@Value
public class AppUserDto implements Serializable {
    Long id;
    String firstName;
    String lastName;
    String email;
    String password;
}