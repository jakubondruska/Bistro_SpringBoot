/*package com.example.bistro_springboot.service;

import com.example.bistro_springboot.model.MenuCategory;
import com.example.bistro_springboot.model.MenuItem;
import com.example.bistro_springboot.repo.MenuCategoryRepository;
import com.example.bistro_springboot.repo.MenuItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public Optional<MenuItem> getItemById(Long menuId) {
        return menuItemRepository.findById(menuId);
    }

    public List<MenuItem> getItemByCategoryId(Long categoryId) {
        return menuItemRepository.findMenuItemByMenuCategoryCategoryId(categoryId);

    }
    public void createItem(MenuItem item, Long categoryId, MultipartFile file) {
        try {
            MenuCategory menuCategory = menuCategoryRepository.findById(categoryId)
                    .orElseThrow(() -> new EntityNotFoundException("Category not found with id" + categoryId));

            item.setMenuCategory(menuCategory);
            item.setAllergens(item.getAllergens());
            item.setDescription(item.getDescription());
            item.setName(item.getName());
            item.setPrice(item.getPrice());
            item.setWeight(item.getWeight());

            if (file != null && !file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                item.setProfilePicture(fileName);
                item.setContent(file.getBytes());
                item.setSize(file.getSize());
            }

            menuItemRepository.save(item);
        } catch (Exception e) {
            throw new RuntimeException("Creating task failed.", e);
        }
    }

    public void updateItem(Long menuId, MenuItem editedItem) {

        MenuItem existingItem = menuItemRepository.findById(menuId)
                .orElseThrow(() -> new EntityNotFoundException("Item not found !"));

        existingItem.setWeight(editedItem.getWeight());
        existingItem.setPrice(editedItem.getPrice());
        existingItem.setDescription(existingItem.getDescription());
        existingItem.setName(editedItem.getName());
        existingItem.setAllergens(editedItem.getAllergens());
        existingItem.setProfilePicture(editedItem.getProfilePicture());
        existingItem.setContent(existingItem.getContent());
        existingItem.setSize(existingItem.getSize());

        menuItemRepository.save(existingItem);
    }

    public Optional<MenuItem> getEditItem(Long menuId) {
        return menuItemRepository.findById(menuId);
    }
    public void deleteItem(Long id) {
        menuItemRepository.deleteById(id);
    }


    public String getContentTypes(String fileName) {
        // Mapa typov obsahu
        Map<String, String> contentTypes = new HashMap<>();
        contentTypes.put("jpeg", "image/jpeg");
        contentTypes.put("jpg", "image/jpeg");
        contentTypes.put("png", "image/png");
        contentTypes.put("pdf", "application/pdf");

        // Získanie prípony súboru zo zadaného názvu súboru
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

        // Získanie typu obsahu zo zoznamu typov obsahu na základe prípony súboru
        return contentTypes.getOrDefault(fileExtension, "application/octet-stream");
    }

}

*/