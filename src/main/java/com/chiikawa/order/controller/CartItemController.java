package com.chiikawa.order.controller;

import com.chiikawa.order.dto.CartItemRequest;
import com.chiikawa.order.model.CartItem;
import com.chiikawa.order.model.Product;
import com.chiikawa.order.service.CartItemService;
import com.chiikawa.order.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ProductService productService;

    // 添加商品到購物車
    @PostMapping
    public ResponseEntity<?> addOrUpdateCartItem(@RequestBody CartItemRequest cartItemRequest) {
        try {
            Long productId = cartItemRequest.getProductId();
            Integer quantity = cartItemRequest.getQuantity();

            if (productId == null || quantity == null || quantity <= 0) {
                return ResponseEntity.badRequest().body("Invalid productId or quantity");
            }

            // 獲取 Product 實體
            Product product = productService.getProductById(productId);
            if (product == null) {
                return ResponseEntity.badRequest().body("Product not found");
            }

            // 創建或更新 CartItem 對象
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setPrice(product.getPrice() * quantity); // 計算總價

            // 保存或更新購物車項目
            CartItem savedCartItem = cartItemService.saveOrUpdateCartItem(cartItem);
            return ResponseEntity.ok(savedCartItem);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("An unexpected error occurred");
        }
    }

    // 獲取購物車中的所有商品
    @GetMapping
    public ResponseEntity<List<CartItem>> getAllCartItems() {
        try {
            List<CartItem> cartItems = cartItemService.getAllCartItems();
            return ResponseEntity.ok(cartItems);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}
