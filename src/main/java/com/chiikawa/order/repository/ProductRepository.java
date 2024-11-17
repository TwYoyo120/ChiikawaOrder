package com.chiikawa.order.repository;

import com.chiikawa.order.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // JpaRepository 自帶常用方法，無需額外定義
}
