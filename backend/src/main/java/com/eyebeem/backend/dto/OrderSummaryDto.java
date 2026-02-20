package com.eyebeem.backend.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class OrderSummaryDto {
    private Long id;
    private BigDecimal total;
    private Instant placedAt;
    private List<OrderItemDto> items;

    public OrderSummaryDto() {}
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public Instant getPlacedAt() { return placedAt; }
    public void setPlacedAt(Instant placedAt) { this.placedAt = placedAt; }
    public List<OrderItemDto> getItems() { return items; }
    public void setItems(List<OrderItemDto> items) { this.items = items; }
}
