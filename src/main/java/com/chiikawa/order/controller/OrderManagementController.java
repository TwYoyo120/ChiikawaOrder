package com.chiikawa.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.chiikawa.order.dto.OrderRequestDTO;
import com.chiikawa.order.model.Order;
import com.chiikawa.order.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderManagementController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequestDTO requestDTO) {
        try {
            Order order = requestDTO.toOrder();
            Order savedOrder = orderService.saveOrder(order);
            return ResponseEntity.ok("Order created successfully. ID: " + savedOrder.getOrderId());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to create order: " + e.getMessage());
        }
    }
}
