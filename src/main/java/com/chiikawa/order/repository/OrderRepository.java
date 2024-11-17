package com.chiikawa.order.repository;

import com.chiikawa.order.model.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // 根據買家 ID 查詢訂單
    List<Order> findByBuyerUserId(Long buyerId);

    // 根據賣家 ID 查詢訂單
    List<Order> findBySellerUserId(Long sellerId);

    // 查詢所有訂單並加載關聯數據
    @EntityGraph(attributePaths = {"shippingInfo"})
    List<Order> findAll();
}
