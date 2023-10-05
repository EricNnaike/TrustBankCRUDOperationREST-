package com.example.trustbankcrud.exceptions;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;


@Data
@Builder
public class ErrorDetails {

    private LocalDateTime timestamp;
    private String message;
    private String details;


}
