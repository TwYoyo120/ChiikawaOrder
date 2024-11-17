package com.chiikawa.order.service;

import com.chiikawa.order.model.Order;
import com.chiikawa.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // 保存訂單
    public Order saveOrder(Order order) {
        try {
            return orderRepository.save(order);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save order: " + e.getMessage());
        }
    }

    // 根據訂單 ID 獲取訂單
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> 
            new RuntimeException("Order not found for ID: " + orderId));
    }

    // 獲取所有訂單
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // 根據用戶 ID 獲取訂單
    public List<Order> findOrdersByBuyer(Long buyerId) {
        return orderRepository.findByBuyerUserId(buyerId);
    }

    // 更新訂單狀態
    public Order updateOrderStatus(Long orderId, String status) {
        Order order = getOrderById(orderId);
        order.setStatus(status);
        return orderRepository.save(order);
    }
}
