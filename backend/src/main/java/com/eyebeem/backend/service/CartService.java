package com.eyebeem.backend.service;

import com.eyebeem.backend.dto.CartDto;
import com.eyebeem.backend.dto.CartItemDto;
import com.eyebeem.backend.entity.*;
import com.eyebeem.backend.repository.CartRepository;
import com.eyebeem.backend.repository.ProductRepository;
import com.eyebeem.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public CartDto getCart(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = cartRepository.findByUserId(userId).orElse(null);
        CartDto dto = new CartDto();
        dto.setUserId(userId);
        if (cart == null || cart.getItems().isEmpty()) {
            dto.setItems(List.of());
            dto.setTotal(BigDecimal.ZERO);
            return dto;
        }
        List<CartItemDto> items = cart.getItems().stream().map(ci -> {
            Product p = productRepository.findByProductId(ci.getProductId()).orElse(null);
            CartItemDto item = new CartItemDto();
            item.setId(ci.getId());
            item.setProductId(ci.getProductId());
            item.setProductName(p != null ? p.getName() : "?");
            item.setUnitPrice(p != null ? p.getPrice() : BigDecimal.ZERO);
            item.setQuantity(ci.getQuantity());
            return item;
        }).collect(Collectors.toList());
        dto.setItems(items);
        BigDecimal total = items.stream()
                .map(i -> i.getUnitPrice().multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        dto.setTotal(total);
        return dto;
    }

    @Transactional
    public CartDto addToCart(Long userId, Integer productId, int quantity) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findByProductId(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        if (product.getQuantity() < quantity) {
            throw new RuntimeException("Not enough stock for " + product.getName());
        }
        Cart cart = cartRepository.findByUserId(userId).orElseGet(() -> {
            Cart c = new Cart();
            c.setUser(user);
            return cartRepository.save(c);
        });
        CartItem existing = cart.getItems().stream()
                .filter(ci -> ci.getProductId().equals(productId))
                .findFirst().orElse(null);
        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + quantity);
        } else {
            CartItem ci = new CartItem();
            ci.setCart(cart);
            ci.setProductId(productId);
            ci.setQuantity(quantity);
            cart.getItems().add(ci);
        }
        cartRepository.save(cart);
        return getCart(userId);
    }

    @Transactional
    public void removeFromCart(Long userId, Long cartItemId) {
        Cart cart = cartRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("Cart not found"));
        cart.getItems().removeIf(ci -> ci.getId().equals(cartItemId));
        cartRepository.save(cart);
    }

    @Transactional
    public void clearCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId).orElse(null);
        if (cart != null) {
            cart.getItems().clear();
            cartRepository.save(cart);
        }
    }
}
