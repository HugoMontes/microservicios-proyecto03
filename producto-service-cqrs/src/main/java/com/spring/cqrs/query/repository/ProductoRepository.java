package com.spring.cqrs.query.repository;

import com.spring.cqrs.query.data.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, String> {
}
