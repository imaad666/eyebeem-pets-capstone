package com.eyebeem.backend.dto;

import java.math.BigDecimal;

public class OrderItemDto {
    private Integer productId;
    private String productName;
    private Integer quantity;
    private BigDecimal unitPrice;

    public OrderItemDto() {}
    public Integer getProductId() { return productId; }
    public void setProductId(Integer productId) { this.productId = productId; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }
}
