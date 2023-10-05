package com.example.trustbankcrud.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Builder
public class CreateUserRequest {
    @NotBlank(message = "Name is mandatory")
    private String name;
    @Email(message = "Invalid email address")
    private String email;
    @Pattern(regexp = "^[0-9]*$", message = "Phone number should contain only digits")
    private String phone;
    private AddressRequest address;
}
