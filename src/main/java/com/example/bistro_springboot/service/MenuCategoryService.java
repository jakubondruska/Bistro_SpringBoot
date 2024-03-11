package com.example.bistro_springboot.service;

import com.example.bistro_springboot.model.MenuCategory;
import com.example.bistro_springboot.repo.MenuCategoryRepository;
import com.example.bistro_springboot.repo.MenuItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    public MenuCategory createCategory(MenuCategory category, MultipartFile file) {

        try {
            MenuCategory newMenuCategory = new MenuCategory();
            newMenuCategory.setCategoryName(category.getCategoryName());
            newMenuCategory.setCategoryDescription(category.getCategoryDescription());
            menuCategoryRepository.save(newMenuCategory);
            return newMenuCategory;

        } catch (Exception e){
            throw  new RuntimeException("Creating new category failed", e);

        }
    }

    public MenuCategory editCategory(Long categoryId, MenuCategory editedCategory) {

        MenuCategory existingCategory = menuCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        existingCategory.setCategoryName(editedCategory.getCategoryName());
        existingCategory.setCategoryDescription(editedCategory.getCategoryDescription());

        return menuCategoryRepository.save(existingCategory);
    }


    public void deleteCategory(Long id) {
        menuCategoryRepository.deleteById(id);
    }


}

