package com.chiikawa.order.service;

import com.chiikawa.order.model.ShippingInfo;
import com.chiikawa.order.repository.ShippingInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShippingInfoService {

    @Autowired
    private ShippingInfoRepository shippingInfoRepository;

    // 保存或更新 ShippingInfo
    public ShippingInfo saveOrUpdateShippingInfo(ShippingInfo shippingInfo) {
        return shippingInfoRepository.save(shippingInfo);
    }

    // 根據 Order ID 獲取 ShippingInfo
    public ShippingInfo getShippingInfoByOrderId(Long orderId) {
        return shippingInfoRepository.findByOrderOrderId(orderId);
    }
}
