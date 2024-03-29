package com.example.bistro_springboot.service;

import com.example.bistro_springboot.model.Product;
import com.example.bistro_springboot.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }


}
