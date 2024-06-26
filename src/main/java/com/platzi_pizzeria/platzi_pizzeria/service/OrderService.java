package com.platzi_pizzeria.platzi_pizzeria.service;

import com.platzi_pizzeria.platzi_pizzeria.persistence.entity.OrderEntity;
import com.platzi_pizzeria.platzi_pizzeria.persistence.projection.OrderSummary;
import com.platzi_pizzeria.platzi_pizzeria.persistence.repository.OrderRepository;
import com.platzi_pizzeria.platzi_pizzeria.service.dto.RandomOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final String DELIVERY = "D";
    private final String CARRYOUT = "C";
    private final String ON_SITE = "S";

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderEntity> getAll() {
        List<OrderEntity> orders = this.orderRepository.findAll();
        orders.forEach(o -> System.out.println("Customer: " + o.getCustomer().getName()));
        return orders;
    }

    public List<OrderEntity> getTodayOrders() {
        LocalDateTime today = LocalDate.now().atTime(0, 0);
        return this.orderRepository.findAllByDateAfter(today);
    }

    public List<OrderEntity> getOutsideOrders() {
        List<String> methods = Arrays.asList(DELIVERY, CARRYOUT,ON_SITE);
        return this.orderRepository.findAllByMethodIn(methods);
    }

    @Secured("ROLE_ADMIN")
    public List<OrderEntity> getCustomerOrders(String idCustomer) {
        return this.orderRepository.findCustomerOrders(idCustomer);
    }

    public OrderSummary getSummary(int idOrder) {
        return this.orderRepository.findSummary(idOrder);
    }

    @Transactional
    public boolean saveRandomOrder(RandomOrderDto randomOrderDto) {
        return  this.orderRepository.saveRandonOrder(randomOrderDto.getIdCustomer(), randomOrderDto.getMethod());
    }
}
