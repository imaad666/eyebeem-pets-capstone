package com.eyebeem.backend.service;

import com.eyebeem.backend.dto.OrderItemDto;
import com.eyebeem.backend.dto.OrderSummaryDto;
import com.eyebeem.backend.dto.UserDto;
import com.eyebeem.backend.entity.Order;
import com.eyebeem.backend.entity.OrderItem;
import com.eyebeem.backend.entity.User;
import com.eyebeem.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public UserDto getUserWithOrderHistory(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setCreatedAt(user.getCreatedAt());
        List<OrderSummaryDto> orders = user.getOrders().stream()
                .sorted((a, b) -> b.getPlacedAt().compareTo(a.getPlacedAt()))
                .map(this::toOrderSummaryDto)
                .collect(Collectors.toList());
        dto.setOrderHistory(orders);
        return dto;
    }

    private OrderSummaryDto toOrderSummaryDto(Order order) {
        OrderSummaryDto dto = new OrderSummaryDto();
        dto.setId(order.getId());
        dto.setTotal(order.getTotal());
        dto.setPlacedAt(order.getPlacedAt());
        dto.setItems(order.getItems().stream().map(this::toOrderItemDto).collect(Collectors.toList()));
        return dto;
    }

    private OrderItemDto toOrderItemDto(OrderItem item) {
        OrderItemDto dto = new OrderItemDto();
        dto.setProductId(item.getProductId());
        dto.setProductName(item.getProductName());
        dto.setQuantity(item.getQuantity());
        dto.setUnitPrice(item.getUnitPrice());
        return dto;
    }
}
