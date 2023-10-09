package com.employee.backend.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Component
@Data
public class ResponseDTO {
    private String code;
    private String message;
    private Object content;
}
