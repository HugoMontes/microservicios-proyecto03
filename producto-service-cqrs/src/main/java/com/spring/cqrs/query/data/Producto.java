package com.spring.cqrs.query.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
public class Producto {
    @Id
    private String productoId;
    private String nombre;
    private BigDecimal precio;
    private Integer cantidad;
}
