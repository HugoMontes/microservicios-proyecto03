package com.spring.cqrs.query.controller;

import com.spring.cqrs.query.model.ProductoResponse;
import com.spring.cqrs.query.queries.GetProductoQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoQueryController {

    // Gateway de Axon para enviar queries (consultas)
    @Autowired
    private QueryGateway queryGateway;

    @GetMapping
    public List<ProductoResponse> listarProductos() {
        // 1. Crear el objeto Query (mensaje de consulta)
        GetProductoQuery getProductoQuery = new GetProductoQuery();
        // 2. Enviar la query a Axon
        // Axon buscará un @QueryHandler que maneje GetProductoQuery
        List<ProductoResponse> productoRestModels = queryGateway
                .query(
                        getProductoQuery,
                        // 3. Indicamos el tipo de respuesta esperado (lista de ProductoResponse)
                        ResponseTypes.multipleInstancesOf(ProductoResponse.class))
                .join(); // 4. Espera la respuesta de forma síncrona (bloqueante)
        // 5. Retornar la respuesta al cliente
        return productoRestModels;
    }
}