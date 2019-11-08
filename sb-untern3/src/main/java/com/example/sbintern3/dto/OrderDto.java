package com.example.sbintern3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDto {
    private String orderName;
    private String clientName;
    private Integer cost;
}
