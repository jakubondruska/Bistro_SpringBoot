package com.example.bistro_springboot.controller;

import com.example.bistro_springboot.model.MenuCategory;
import com.example.bistro_springboot.service.MenuCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MenuCategoryController {

    private final MenuCategoryService categoryService;

    @Autowired
    public MenuCategoryController(MenuCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public String getAllCategories(Model model) {
        List<MenuCategory> viewAllCategories = categoryService.getAllMenuCategory();
        model.addAttribute("categories", viewAllCategories);
        return "all";
    }


    @PostMapping("/addCategory")
    public String createCategory(@ModelAttribute @Validated MenuCategory category,
                                 @RequestParam("categoryPics") MultipartFile file,
                                 RedirectAttributes redirectAttributes) {
        MenuCategory createCategory = categoryService.createCategory(category,file);
        redirectAttributes.addFlashAttribute("success message", "Category added successfully");

        Long categoryId = createCategory.getCategoryId();
        return "redirect:/all";
    }


    @PostMapping("/edit/{categoryId}")
    public String editCategory(@PathVariable Long categoryId, @ModelAttribute MenuCategory editedCategory) {
        categoryService.editCategory(categoryId, editedCategory);
        return "redirect:/all";
    }

    @GetMapping("/delete/{categoryId}")
    public String deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return "redirect:/all";
    }
}
