package com.example.bistro_springboot.repo;

import com.example.bistro_springboot.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
