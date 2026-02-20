package com.eyebeem.backend.dto;

import java.math.BigDecimal;

public class CartItemDto {
    private Long id;
    private Integer productId;
    private String productName;
    private BigDecimal unitPrice;
    private Integer quantity;

    public CartItemDto() {}
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getProductId() { return productId; }
    public void setProductId(Integer productId) { this.productId = productId; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
