package com.spring.cqrs.query.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductoResponse {
    private String productoId;
    private String nombre;
    private BigDecimal precio;
    private Integer cantidad;
}
