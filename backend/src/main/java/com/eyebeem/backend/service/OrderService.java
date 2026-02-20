package com.eyebeem.backend.service;

import com.eyebeem.backend.dto.OrderSummaryDto;
import com.eyebeem.backend.entity.*;
import com.eyebeem.backend.repository.OrderRepository;
import com.eyebeem.backend.repository.ProductRepository;
import com.eyebeem.backend.repository.UserRepository;
import com.eyebeem.backend.repository.CartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartService cartService;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository,
                        ProductRepository productRepository, CartRepository cartRepository,
                        CartService cartService) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartService = cartService;
    }

    @Transactional
    public OrderSummaryDto placeOrder(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = cartRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("Cart not found"));
        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }
        Order order = new Order();
        order.setUser(user);
        BigDecimal total = BigDecimal.ZERO;
        for (CartItem ci : cart.getItems()) {
            Product p = productRepository.findByProductId(ci.getProductId()).orElseThrow();
            if (p.getQuantity() < ci.getQuantity()) {
                throw new RuntimeException("Not enough stock for " + p.getName());
            }
            OrderItem oi = new OrderItem();
            oi.setOrder(order);
            oi.setProductId(p.getProductId());
            oi.setProductName(p.getName());
            oi.setQuantity(ci.getQuantity());
            oi.setUnitPrice(p.getPrice());
            order.getItems().add(oi);
            total = total.add(p.getPrice().multiply(BigDecimal.valueOf(ci.getQuantity())));
            p.setQuantity(p.getQuantity() - ci.getQuantity());
            productRepository.save(p);
        }
        order.setTotal(total);
        orderRepository.save(order);
        cartService.clearCart(userId);
        return toSummaryDto(order);
    }

    @Transactional(readOnly = true)
    public List<OrderSummaryDto> getOrderHistory(Long userId) {
        return orderRepository.findByUser_IdOrderByPlacedAtDesc(userId).stream()
                .map(this::toSummaryDto)
                .collect(Collectors.toList());
    }

    private OrderSummaryDto toSummaryDto(Order order) {
        OrderSummaryDto dto = new OrderSummaryDto();
        dto.setId(order.getId());
        dto.setTotal(order.getTotal());
        dto.setPlacedAt(order.getPlacedAt());
        dto.setItems(order.getItems().stream().map(oi -> {
            com.eyebeem.backend.dto.OrderItemDto item = new com.eyebeem.backend.dto.OrderItemDto();
            item.setProductId(oi.getProductId());
            item.setProductName(oi.getProductName());
            item.setQuantity(oi.getQuantity());
            item.setUnitPrice(oi.getUnitPrice());
            return item;
        }).collect(Collectors.toList()));
        return dto;
    }
}
