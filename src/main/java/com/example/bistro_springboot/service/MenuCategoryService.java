package com.example.bistro_springboot.service;

import com.example.bistro_springboot.model.MenuCategory;
import com.example.bistro_springboot.repo.MenuCategoryRepository;
import com.example.bistro_springboot.repo.MenuItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public MenuCategory createCategory(MenuCategory category, MultipartFile file) throws IOException {

        try {
            MenuCategory newMenuCategory = new MenuCategory();
            newMenuCategory.setCategoryName(category.getCategoryName());
            newMenuCategory.setCategoryDescription(category.getCategoryDescription());
            String fileName = file.getOriginalFilename();
            newMenuCategory.setProfilePicture(fileName);
            newMenuCategory.setContent(file.getBytes());
            newMenuCategory.setSize(file.getSize());
            menuCategoryRepository.save(newMenuCategory);
            return newMenuCategory;

        } catch (Exception e){
            throw  new RuntimeException("Creating new category failed", e);

        }
    }

    public void editCategory(Long categoryId, MenuCategory editedCategory) {
        MenuCategory existingCategory = menuCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category not found: " + categoryId));

        existingCategory.setCategoryName(editedCategory.getCategoryName());
        existingCategory.setCategoryDescription(editedCategory.getCategoryDescription());

        // Assuming profilePicture is a URL and not a file upload
        existingCategory.setProfilePicture(editedCategory.getProfilePicture());

        menuCategoryRepository.save(existingCategory);
    }



    public void deleteCategory(Long id) {
        menuCategoryRepository.deleteById(id);
    }

    public String getContentTypes(String fileName) {

        // Content types map
        Map<String, String> contentTypes = new HashMap<>();
        contentTypes.put("jpeg", "image/jpeg");
        contentTypes.put("jpg", "image/jpeg");
        contentTypes.put("png", "image/png");
        contentTypes.put("pdf", "application/pdf");

        // Getting suffix from the file name behind the dot
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

        // Getting content type from map based on file suffix
        return contentTypes.getOrDefault(fileExtension, "application/octet-stream");
    }


}

