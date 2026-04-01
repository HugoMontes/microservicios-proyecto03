package com.spring.cqrs.command.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductoRequest {
    private String productoId;
    private String nombre;
    private BigDecimal precio;
    private Integer cantidad;
}
