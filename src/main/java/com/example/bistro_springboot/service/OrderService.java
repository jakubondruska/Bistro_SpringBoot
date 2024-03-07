package com.example.bistro_springboot.service;

import com.example.bistro_springboot.model.Order;
import com.example.bistro_springboot.repo.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrderDetails() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {

        return orderRepository.findById(id);
    }

}
