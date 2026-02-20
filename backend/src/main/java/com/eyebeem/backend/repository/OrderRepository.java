package com.eyebeem.backend.repository;

import com.eyebeem.backend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser_IdOrderByPlacedAtDesc(Long userId);
}
