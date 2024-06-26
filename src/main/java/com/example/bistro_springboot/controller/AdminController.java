package com.example.bistro_springboot.controller;

import com.example.bistro_springboot.dto.ProductDto;
import com.example.bistro_springboot.model.Category;
import com.example.bistro_springboot.service.CategoryService;
import com.example.bistro_springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class AdminController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @GetMapping("/admin")
    public String adminHome(){
        return "adminHome";
    }

    @GetMapping("/admin/categories")
    public String getCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "categories";
    }
    @GetMapping("/admin/categories/add")
    public String getCatAdd(Model model) {
        model.addAttribute("category", new Category());
        return "categoriesAdd";
    }
    @PostMapping("admin/categories/add")
    public String postCatAdd(@ModelAttribute("category") Category category){
        categoryService.addCategory(category);
        return "redirect:/admin/categories";

    }
    @GetMapping("admin/categories/delete/{id}")
    public String deleteCategory(@PathVariable int id ) {
        categoryService.removeCategoryById(id);
        return "redirect:/admin/categories";
    }
    @GetMapping("admin/categories/update/{id}")
    public String updateCategory(@PathVariable int id, Model model) {
        Optional<Category> category = categoryService.getCategoryById(id);
        if (category.isPresent()) {
            model.addAttribute("category", category.get());
            return "categoriesAdd";
        } else  {
            return "ERROR 404";
        }
    }

    // Product section
    @GetMapping("/admin/products")
    public String getProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @GetMapping("/admin/products/add")
    public String getAddProduct(Model model) {
        model.addAttribute("productDTO", new ProductDto());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "productsAdd";
    }








}
