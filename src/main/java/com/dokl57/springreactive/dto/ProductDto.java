package com.dokl57.springreactive.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ProductDto {
    private UUID id;
    private String name;
    private int quantity;
    private double price;

}
