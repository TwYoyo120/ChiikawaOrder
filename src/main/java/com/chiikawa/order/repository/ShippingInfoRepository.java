package com.chiikawa.order.repository;

import com.chiikawa.order.model.ShippingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingInfoRepository extends JpaRepository<ShippingInfo, Long> {

    // 根據訂單 ID 獲取 ShippingInfo
    ShippingInfo findByOrderOrderId(Long orderId);
}
