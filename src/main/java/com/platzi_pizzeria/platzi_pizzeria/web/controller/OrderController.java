package com.platzi_pizzeria.platzi_pizzeria.web.controller;

import com.platzi_pizzeria.platzi_pizzeria.persistence.entity.OrderEntity;
import com.platzi_pizzeria.platzi_pizzeria.persistence.projection.OrderSummary;
import com.platzi_pizzeria.platzi_pizzeria.service.OrderService;
import com.platzi_pizzeria.platzi_pizzeria.service.dto.RandomOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<?>> getAll() {
        return ResponseEntity.ok(this.orderService.getAll());
    }

    @GetMapping("/todayOrders")
    public ResponseEntity<?> getTodayOrders() {
        return ResponseEntity.ok(this.orderService.getTodayOrders());
    }

    @GetMapping("/outsideOrders")
    public ResponseEntity<List<OrderEntity>> getOutsideOrders() {
        return ResponseEntity.ok(this.orderService.getOutsideOrders());
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<OrderEntity>> getCustomerOrders(@PathVariable("id") String idCustomer) {
        return ResponseEntity.ok(this.orderService.getCustomerOrders(idCustomer));
    }

    @GetMapping("/summary/{id}")
    public ResponseEntity<OrderSummary> getOrderSummary(@PathVariable("id") int idOrder) {
        return ResponseEntity.ok(this.orderService.getSummary(idOrder));
    }

    @PostMapping("/random")
    public ResponseEntity<Boolean> randomOrder(@RequestBody RandomOrderDto randomOrderDto) {
        return ResponseEntity.ok(this.orderService.saveRandomOrder(randomOrderDto));
    }
}
