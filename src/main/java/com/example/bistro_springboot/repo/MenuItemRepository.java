package com.example.bistro_springboot.repo;

import com.example.bistro_springboot.model.MenuItem;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    List<MenuItem> findMenuItemByMenuCategoryId(Long id);
}