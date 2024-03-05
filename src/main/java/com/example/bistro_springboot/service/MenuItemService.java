package com.example.bistro_springboot.service;

import com.example.bistro_springboot.model.MenuCategory;
import com.example.bistro_springboot.model.MenuItem;
import com.example.bistro_springboot.repo.MenuCategoryRepository;
import com.example.bistro_springboot.repo.MenuItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class MenuItemService {

    private final MenuCategoryRepository menuCategoryRepository;

    private final MenuItemRepository menuItemRepository;

    public MenuItemService(MenuCategoryRepository menuCategoryRepository, MenuItemRepository menuItemRepository) {
        this.menuCategoryRepository = menuCategoryRepository;
        this.menuItemRepository = menuItemRepository;
    }

    public List<MenuItem> getAllItems() {
       return menuItemRepository.findAll();
    }

    public Optional<MenuItem> getItemById(Long id) {
        return menuItemRepository.findById(id);
    }
    public List<MenuItem> getItemByCategoryId(Long id) {
        return menuItemRepository.findMenuItemByMenuCategoryId(id);

    }
    public void createItem(MenuItem item, Long categoryId) {

        try {
            MenuCategory menuCategory = menuCategoryRepository.findById(categoryId)
                    .orElseThrow(() -> new EntityNotFoundException("Category not found with id" + categoryId));

            item.setMenuCategory(menuCategory);
            item.setAllergens(item.getAllergens());
            item.setDescription(item.getDescription());
            item.setName(item.getName());
            item.setPrice(item.getPrice());
            item.setWeight(item.getWeight());

            menuItemRepository.save(item);

        } catch (Exception e) {
            throw new RuntimeException("Creating task failed.", e);
        }
    }
    public void editItem(Long taskId, MenuItem editedItem) {

        MenuItem existingItem = menuItemRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Item not found !"));

        existingItem.setWeight(editedItem.getWeight());
        existingItem.setPrice(editedItem.getPrice());
        existingItem.setDescription(existingItem.getDescription());
        existingItem.setName(editedItem.getName());
        existingItem.setAllergens(editedItem.getAllergens());

        menuItemRepository.save(existingItem);
    }
    public void deleteItem(Long id) {
        menuItemRepository.deleteById(id);
    }





}

