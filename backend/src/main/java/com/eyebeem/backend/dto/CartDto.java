package com.eyebeem.backend.dto;

import java.math.BigDecimal;
import java.util.List;

public class CartDto {
    private Long userId;
    private List<CartItemDto> items;
    private BigDecimal total;

    public CartDto() {}
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public List<CartItemDto> getItems() { return items; }
    public void setItems(List<CartItemDto> items) { this.items = items; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
}
