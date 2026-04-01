package com.spring.cqrs.query.handler;

import com.spring.cqrs.query.data.Producto;
import com.spring.cqrs.query.repository.ProductoRepository;
import com.spring.cqrs.shared.event.ProductoCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductoEventHandler {

    @Autowired
    private ProductoRepository productoRepository;

    @EventHandler
    public void on(ProductoCreatedEvent productoCreatedEvent){
        Producto producto = new Producto();
        BeanUtils.copyProperties(productoCreatedEvent, producto);
        productoRepository.save(producto);
    }
}
