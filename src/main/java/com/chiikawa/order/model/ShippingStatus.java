package com.chiikawa.order.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ShippingStatus {
    PENDING("待發貨"),
    IN_TRANSIT("配送中"),
    DELIVERED("已送達"),
    RETURNED("已退回");

    private final String description;

    ShippingStatus(String description) {
        this.description = description;
    }

    @JsonValue
    public String getDescription() {
        return description;
    }

    @JsonCreator
    public static ShippingStatus fromDescription(String description) {
        for (ShippingStatus status : ShippingStatus.values()) {
            if (status.getDescription().equalsIgnoreCase(description)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown shipping status description: " + description);
    }
}
