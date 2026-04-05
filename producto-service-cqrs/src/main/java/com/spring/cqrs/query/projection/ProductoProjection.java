package com.spring.cqrs.query.projection;

import com.spring.cqrs.query.data.Producto;
import com.spring.cqrs.query.model.ProductoResponse;
import com.spring.cqrs.query.queries.GetProductoQuery;
import com.spring.cqrs.query.repository.ProductoRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductoProjection {

    // Repositorio que accede a la base de datos de lectura (H2 en tu caso)
    @Autowired
    private ProductoRepository productoRepository;

    // Este metodo maneja la consulta GetProductoQuery
    // Se ejecuta automáticamente cuando se envía una query a través del QueryGateway.
    @QueryHandler
    public List<ProductoResponse> handle(GetProductoQuery getProductoQuery) {
        // Obtener todos los productos desde la base de datos (read model)
        List<Producto> productos = productoRepository.findAll();
        // Convertir las entidades a DTOs de respuesta
        // (para no exponer la entidad directamente)
        List<ProductoResponse> productoResponseList = productos.stream()
                .map(producto -> ProductoResponse
                        .builder()
                        .cantidad(producto.getCantidad())
                        .precio(producto.getPrecio())
                        .nombre(producto.getNombre())
                        .productoId(producto.getProductoId())
                        .build()
                ).collect(Collectors.toList());
        // Retornar la lista de productos al cliente
        return productoResponseList;
    }
}
