package com.platzi_pizzeria.platzi_pizzeria.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_item")
@IdClass(OrderItemId.class)
@Getter @Setter @NoArgsConstructor
public class OrderItemEntity {

    @Id
    @Column(name = "id_order", nullable = false)
    private Integer idOrder;

    @Id
    @Column(name = "id_item", nullable = false)
    private Integer idItem;

    @Column(name = "id_pizza", nullable = false)
    private Integer idPizza;

    @Column(name = "quantity", nullable = false, columnDefinition = "Decimal(2,1)")
    private Double quantity;

    @Column(name = "price", nullable = false, columnDefinition = "Decimal(5,2)")
    private Double price;


    @OneToOne
    @JoinColumn(name = "id_pizza", referencedColumnName = "id_pizza",updatable = false, insertable = false)
    private PizzaEntity pizza;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_order", referencedColumnName = "id_order", updatable = false, insertable = false)
    private OrderEntity order;
}
