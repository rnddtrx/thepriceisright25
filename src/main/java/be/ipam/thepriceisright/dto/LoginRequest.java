package be.ipam.thepriceisright.dto;

import lombok.Value;

@Value
public class LoginRequest {
    String email;
    String password;
}
