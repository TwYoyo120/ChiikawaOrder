package com.chiikawa.order.service;

import com.chiikawa.order.model.CartItem;
import com.chiikawa.order.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    // 保存或更新購物車項目
    public CartItem saveOrUpdateCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    // 獲取所有購物車項目
    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    // 清空購物車
    public void clearCart() {
        cartItemRepository.deleteAll();
    }
}
