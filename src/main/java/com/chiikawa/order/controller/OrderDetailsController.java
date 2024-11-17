package com.chiikawa.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chiikawa.order.model.Order; // 導入你的 Order 模型類
import com.chiikawa.order.service.OrderService; // 導入你的 OrderService 類


@RestController
@RequestMapping("/api/order-details")
public class OrderDetailsController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderDetails(@PathVariable Long orderId) {
        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(order);
    }
}
