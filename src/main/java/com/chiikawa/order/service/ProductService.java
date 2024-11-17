package com.chiikawa.order.service;

import com.chiikawa.order.model.Product;
import com.chiikawa.order.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // 根據 ID 獲取產品
    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    // 獲取所有產品
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
