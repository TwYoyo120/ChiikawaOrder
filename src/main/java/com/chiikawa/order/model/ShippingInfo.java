package com.chiikawa.order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "shipping_info")
public class ShippingInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipping_id")
    private Long shippingId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false, unique = true)
    @JsonIgnore // 避免序列化 Order 以防止循環引用
    private Order order;

    @NotEmpty
    @Column(name = "address", nullable = false, length = 255)
    private String address;

    @NotEmpty
    @Column(name = "recipient_name", nullable = false, length = 100)
    private String recipientName;

    @NotEmpty
    @Column(name = "contact_number", nullable = false, length = 20)
    private String contactNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private ShippingStatus status = ShippingStatus.PENDING; // 默認狀態

    // 無參數構造函數
    public ShippingInfo() {}

    // 帶參數構造函數
    public ShippingInfo(Order order, String address, String recipientName, String contactNumber) {
        this.order = order;
        this.address = address;
        this.recipientName = recipientName;
        this.contactNumber = contactNumber;
        this.status = ShippingStatus.PENDING;
    }

    // Getter 和 Setter 方法

    public Long getShippingId() {
        return shippingId;
    }

    public void setShippingId(Long shippingId) {
        this.shippingId = shippingId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public ShippingStatus getStatus() {
        return status;
    }

    public void setStatus(ShippingStatus status) {
        this.status = status;
    }
}
