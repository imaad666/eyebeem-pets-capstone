package com.eyebeem.backend.dto;

import java.math.BigDecimal;

public class ProductDto {
    private Integer productId;
    private String name;
    private BigDecimal price;
    private String type;
    private Integer quantity;

    public ProductDto() {}
    public ProductDto(Integer productId, String name, BigDecimal price, String type, Integer quantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.type = type;
        this.quantity = quantity;
    }

    public Integer getProductId() { return productId; }
    public void setProductId(Integer productId) { this.productId = productId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
