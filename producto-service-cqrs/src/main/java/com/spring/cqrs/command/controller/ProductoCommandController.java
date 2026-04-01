package com.spring.cqrs.command.controller;

import com.spring.cqrs.command.command.CreateProductoCommand;
import com.spring.cqrs.command.model.ProductoRequest;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/productos")
public class ProductoCommandController {

    // Inyecta el gateway de Axon para enviar comandos
    @Autowired
    private CommandGateway commandGateway;

    @PostMapping
    public String guardarProducto(@RequestBody ProductoRequest productoRequest) {
        // Construir el comando a partir de los datos recibidos
        CreateProductoCommand createProductoCommand =
                CreateProductoCommand.builder()
                        .productoId(UUID.randomUUID().toString()) // Genera ID único automáticamente
                        .nombre(productoRequest.getNombre())      // Toma el nombre del request
                        .precio(productoRequest.getPrecio())      // Toma el precio del request
                        .cantidad(productoRequest.getCantidad())  // Toma la cantidad del request
                        .build();
        //  Enviar el comando a Axon para que sea procesado
        String result = commandGateway.sendAndWait(createProductoCommand);
        return result;
    }
}
