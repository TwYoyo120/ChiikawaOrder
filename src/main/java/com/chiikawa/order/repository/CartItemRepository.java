package com.chiikawa.order.repository;

import com.chiikawa.order.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    // 清空購物車
    @Modifying
    @Transactional
    @Query("DELETE FROM CartItem")
    void clearCart();
}
