package com.example.trustbankcrud.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressRequest {
    private String street;
    private String city;
    private String state;
    private String zipCode;
}
