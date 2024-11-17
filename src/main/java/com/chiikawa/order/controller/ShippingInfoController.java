package com.chiikawa.order.controller;

import com.chiikawa.order.model.ShippingInfo;
import com.chiikawa.order.service.ShippingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shipping-info")
public class ShippingInfoController {

    @Autowired
    private ShippingInfoService shippingInfoService;

    // 更新物流信息
    @PutMapping("/{orderId}")
    public ResponseEntity<ShippingInfo> updateShippingInfo(@PathVariable Long orderId, @RequestBody ShippingInfo updatedInfo) {
        try {
            if (updatedInfo == null || updatedInfo.getAddress() == null || updatedInfo.getRecipientName() == null || updatedInfo.getContactNumber() == null) {
                return ResponseEntity.badRequest().body(null);
            }

            // 獲取現有的 ShippingInfo
            ShippingInfo existingInfo = shippingInfoService.getShippingInfoByOrderId(orderId);
            if (existingInfo == null) {
                return ResponseEntity.status(404).body(null);
            }

            // 更新相應字段
            existingInfo.setAddress(updatedInfo.getAddress());
            existingInfo.setRecipientName(updatedInfo.getRecipientName());
            existingInfo.setContactNumber(updatedInfo.getContactNumber());
            if (updatedInfo.getStatus() != null) {
                existingInfo.setStatus(updatedInfo.getStatus());
            }

            // 保存更新後的數據
            ShippingInfo savedInfo = shippingInfoService.saveOrUpdateShippingInfo(existingInfo);
            return ResponseEntity.ok(savedInfo);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    // 根據 Order ID 獲取物流信息
    @GetMapping("/{orderId}")
    public ResponseEntity<ShippingInfo> getShippingInfo(@PathVariable Long orderId) {
        ShippingInfo shippingInfo = shippingInfoService.getShippingInfoByOrderId(orderId);
        if (shippingInfo == null) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(shippingInfo);
    }
}
