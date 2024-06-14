package com.platzi_pizzeria.platzi_pizzeria.web.controller;

import com.platzi_pizzeria.platzi_pizzeria.persistence.entity.CustomerEntity;
import com.platzi_pizzeria.platzi_pizzeria.persistence.entity.OrderEntity;
import com.platzi_pizzeria.platzi_pizzeria.service.CustomerService;
import com.platzi_pizzeria.platzi_pizzeria.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final OrderService orderService;
    private final CustomerService customerService;

    @Autowired
    public CustomerController(OrderService orderService, CustomerService customerService) {
        this.orderService = orderService;
        this.customerService = customerService;
    }

    @GetMapping("/{phone}")
    public ResponseEntity<CustomerEntity> getByPhone(@PathVariable("phone") String phone) {
        return ResponseEntity.ok(this.customerService.getByPhone(phone));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<OrderEntity>> getCustomerOrders(@PathVariable("id") String idCustomer) {
        return ResponseEntity.ok(this.orderService.getCustomerOrders(idCustomer));
    }
}
