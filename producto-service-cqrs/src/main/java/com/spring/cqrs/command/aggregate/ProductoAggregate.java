package com.spring.cqrs.command.aggregate;

import com.spring.cqrs.command.command.CreateProductoCommand;
import com.spring.cqrs.shared.event.ProductoCreatedEvent;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Aggregate         // Marca esta clase como un agregado gestionado por Axon
public class ProductoAggregate {

    // Identificador único del agregado (coincide con @TargetAggregateIdentifier del comando)
    @AggregateIdentifier
    private String productoId;
    private String nombre;
    private BigDecimal precio;
    private Integer cantidad;

    // Maneja el comando de creación de producto.
    @CommandHandler
    public ProductoAggregate(CreateProductoCommand createProductoCommnad){
        // 1. Crea un evento vacio
        ProductoCreatedEvent productoCreatedEvent =
                ProductoCreatedEvent.builder().build();
        // 2. Copiar los datos del comando al evento
        BeanUtils.copyProperties(createProductoCommnad, productoCreatedEvent);
        // 3. Aplicar el evento: lo almacena en Event Store y lo publica en Event Bus
        AggregateLifecycle.apply(productoCreatedEvent);
    }

    // Actualiza el estado interno del agregado cuando recibe un evento.
    @EventSourcingHandler
    public void on(ProductoCreatedEvent productoCreatedEvent){
        // Restaurar el estado del agregado a partir del evento
        this.cantidad = productoCreatedEvent.getCantidad();
        this.productoId = productoCreatedEvent.getProductoId();
        this.precio = productoCreatedEvent.getPrecio();
        this.nombre = productoCreatedEvent.getNombre();
    }
}
