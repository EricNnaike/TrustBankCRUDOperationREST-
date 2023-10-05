package com.example.trustbankcrud.dto;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String name;
    private String email;
    private String phone;

    private AddressRequest address;
}
