package com.eyebeem.backend.dto;

import java.time.Instant;
import java.util.List;

public class UserDto {
    private Long id;
    private String name;
    private String email;
    private Instant createdAt;
    private List<OrderSummaryDto> orderHistory;

    public UserDto() {}
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
    public List<OrderSummaryDto> getOrderHistory() { return orderHistory; }
    public void setOrderHistory(List<OrderSummaryDto> orderHistory) { this.orderHistory = orderHistory; }
}
