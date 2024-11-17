package com.chiikawa.order.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order-tracking")
public class OrderTrackingController {

    @GetMapping("/{orderId}")
    public ResponseEntity<String> getTrackingInfo(@PathVariable Long orderId) {
        // 獲取訂單追蹤邏輯
        return ResponseEntity.ok("訂單追蹤信息");
    }
}
