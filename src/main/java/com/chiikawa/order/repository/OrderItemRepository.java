package com.chiikawa.order.repository;

import com.chiikawa.order.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    // 根據訂單 ID 查詢訂單項目
    List<OrderItem> findByOrderOrderId(Long orderId);
}
