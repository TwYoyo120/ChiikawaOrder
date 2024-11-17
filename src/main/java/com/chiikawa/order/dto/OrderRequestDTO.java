package com.chiikawa.order.dto;

import com.chiikawa.order.model.Order;
import com.chiikawa.order.model.OrderItem;
import com.chiikawa.order.model.ShippingInfo;

import java.util.List;

public class OrderRequestDTO {
    private Double totalAmount;
    private String status;
    private ShippingInfo shippingInfo;
    private List<OrderItem> items;

    // Getters and Setters
    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public ShippingInfo getShippingInfo() { return shippingInfo; }
    public void setShippingInfo(ShippingInfo shippingInfo) { this.shippingInfo = shippingInfo; }

    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }

    // Convert DTO to Order
    public Order toOrder() {
        Order order = new Order();
        order.setTotalAmount(this.totalAmount);
        order.setStatus(this.status);
        order.setShippingInfo(this.shippingInfo);
        order.setItems(this.items);
        return order;
    }
}
