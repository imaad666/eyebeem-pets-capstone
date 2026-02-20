package com.eyebeem.backend.controller;

import com.eyebeem.backend.dto.OrderSummaryDto;
import com.eyebeem.backend.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /** Place order for user (uses current cart). Reduces inventory and clears cart. */
    @PostMapping
    public ResponseEntity<OrderSummaryDto> placeOrder(@RequestParam Long userId) {
        return ResponseEntity.ok(orderService.placeOrder(userId));
    }

    @GetMapping("/history")
    public ResponseEntity<List<OrderSummaryDto>> orderHistory(@RequestParam Long userId) {
        return ResponseEntity.ok(orderService.getOrderHistory(userId));
    }
}
