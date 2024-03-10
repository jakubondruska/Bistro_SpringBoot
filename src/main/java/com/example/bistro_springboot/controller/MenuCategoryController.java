package com.example.bistro_springboot.controller;

import com.example.bistro_springboot.model.MenuCategory;
import com.example.bistro_springboot.service.MenuCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/add")
    public String showAddCategoryForm(Model model) {
        model.addAttribute("category", new MenuCategory());
        return "all";
    }

    @PostMapping("/addCategory")
    public String createCategory(@RequestParam String categoryName,
                                 RedirectAttributes redirectAttributes) {
        MenuCategory createCategory = categoryService.createCategory(categoryName);

        Long categoryId = createCategory.getCategoryId();
        return "redirect:/all/" + categoryId + "/starters";
    }

    @GetMapping("/edit/{id}")
    public String showEditCategoryForm(@PathVariable Long id, Model model) {
        MenuCategory category = categoryService.getMenuCategoryById(id).orElseThrow();
        model.addAttribute("category", category);
        return "editCategory";
    }

    @PostMapping("/edit/{id}")
    public String editCategory(@PathVariable Long id, @ModelAttribute("category") MenuCategory editedCategory) {
        categoryService.editCategory(id, editedCategory);
        return "redirect:all";
    }

    @GetMapping("/delete/{categoryId}")
    public String deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return "redirect:/all";
    }
}
