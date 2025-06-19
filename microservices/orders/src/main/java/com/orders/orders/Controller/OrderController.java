package com.orders.orders.Controller;

import com.orders.orders.Model.Entity.Order;
import com.orders.orders.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class OrderController {

    private final OrderService orderService;

    @GetMapping("api/orders")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getOrders(){
        return orderService.getOrders();
    }

    @PostMapping("api/orders")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Order> newOrders(@RequestBody Order order){
        return (ResponseEntity<Order>) orderService.newOrder(order);
    }

    @DeleteMapping("/api/orders/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Order> deleteProduct(@PathVariable Long id){
        return orderService.deleteOrder(id);
    }



}

