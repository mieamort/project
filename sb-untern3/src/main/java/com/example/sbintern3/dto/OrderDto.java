package com.example.sbintern3.dto;

import lombok.Data;

@Data
public class OrderDto {
    private String orderName;
    private String clientName;
    private Integer cost;
}
