package com.example.bistro_springboot.service;

import com.example.bistro_springboot.model.MenuCategory;
import com.example.bistro_springboot.repo.MenuCategoryRepository;
import com.example.bistro_springboot.repo.MenuItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MenuCategoryService {

    private final MenuCategoryRepository menuCategoryRepository;

    private final MenuItemRepository menuItemRepository;

    public MenuCategoryService(MenuCategoryRepository menuCategoryRepository, MenuItemRepository menuItemRepository) {
        this.menuCategoryRepository = menuCategoryRepository;
        this.menuItemRepository = menuItemRepository;
    }

    public List<MenuCategory> getAllMenuCategory() {
        return menuCategoryRepository.findAll();
    }

    public Optional<MenuCategory> getMenuCategoryById(Long id) {
        return menuCategoryRepository.findById(id);
    }

    public MenuCategory createCategory(String name) {

        try {
            MenuCategory newMenuCategory = new MenuCategory();
            newMenuCategory.setName(name);
            menuCategoryRepository.save(newMenuCategory);
            return newMenuCategory;
        } catch (Exception e){
            throw  new RuntimeException("Creating new category failed");
        }
    }

    public MenuCategory editCategory(Long id, MenuCategory editedCategory) {

        MenuCategory existingCategory = menuCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        existingCategory.setName(editedCategory.getName());
        return menuCategoryRepository.save(existingCategory);
    }


    public void deleteCategory(Long id) {
        menuCategoryRepository.deleteById(id);
    }


}

