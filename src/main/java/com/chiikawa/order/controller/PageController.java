package com.chiikawa.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/cart")
    public String showCartPage() {
        return "cart/cart"; // 對應 templates/cart/cart.html
    }

    @GetMapping("/checkout")
    public String showCheckoutPage() {
        return "checkout/checkout"; // 對應 templates/checkout/checkout.html
    }

    @GetMapping("/order/details")
    public String showOrderDetailsPage() {
        return "order/order-details"; // 對應 templates/order/order-details.html
    }

    @GetMapping("/order/management")
    public String showOrderManagementPage() {
        return "order/order-management"; // 對應 templates/order/order-management.html
    }

    @GetMapping("/order/tracking")
    public String showOrderTrackingPage() {
        return "order/order-tracking"; // 對應 templates/order/order-tracking.html
    }

    @GetMapping("/product")
    public String showProductPage() {
        return "product/product"; // 對應 templates/product/product.html
    }
}
