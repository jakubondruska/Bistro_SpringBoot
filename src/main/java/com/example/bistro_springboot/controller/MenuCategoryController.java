package com.example.bistro_springboot.controller;

import com.example.bistro_springboot.model.MenuCategory;
import com.example.bistro_springboot.service.MenuCategoryService;
import com.example.bistro_springboot.service.UserService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class MenuCategoryController {

    @Autowired
    private final MenuCategoryService categoryService;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    public MenuCategoryController(MenuCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public String adminPage(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        List<MenuCategory> viewAllCategories = categoryService.getAllMenuCategory();
        model.addAttribute("categoriesAdmin", viewAllCategories);
        return "all";
    }

    @GetMapping("/johny_bistro_main")
    public String userPage(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        List<MenuCategory> viewAllCategories = categoryService.getAllMenuCategory();
        model.addAttribute("categoriesUser", viewAllCategories);
        return "johny_bistro_main";
    }

    @PostMapping("/addCategory")
    public String createCategory(@ModelAttribute @Validated MenuCategory category,
                                 @RequestParam("file") MultipartFile file,
                                 RedirectAttributes redirectAttributes, Model model) throws IOException {
        MenuCategory createCategory = categoryService.createCategory(category, file);
        redirectAttributes.addFlashAttribute("success message", "Category added successfully");
        model.addAttribute("success", "File uploaded Successfully!!");

        Long categoryId = createCategory.getCategoryId();
        return "redirect:/all";
    }


    @GetMapping("/edit/{categoryId}")
    public String editCategoryForm(@PathVariable Long categoryId, Model model) {
        MenuCategory existingCategory = categoryService.getMenuCategoryById(categoryId).orElseThrow();
        model.addAttribute("editedCategory", existingCategory);
        return "editCategory";
    }

    @PostMapping("/edit/{categoryId}")
    public String editCategory(@PathVariable Long categoryId,
                               @ModelAttribute MenuCategory editedCategory) {
        categoryService.editCategory(categoryId, editedCategory);

        return "redirect:/all";
    }


    @GetMapping("/delete/{categoryId}")
    public String deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return "redirect:/all";
    }

    @GetMapping("/downloadfile")
    public void downloadFile(@Param("categoryId") Long categoryId, Model model, HttpServletResponse response) throws IOException {

        Optional<MenuCategory> temp = categoryService.getMenuCategoryById(categoryId);

        if (temp.isPresent()) {

            MenuCategory menuCategory = temp.get();
            response.setContentType("application/octet-steam");
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename = " + menuCategory.getProfilePicture();
            response.setHeader(headerKey, headerValue);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(menuCategory.getContent());
            outputStream.close();
        }
    }

    @GetMapping("/image")
    public void showImage(@RequestParam("categoryId") Long categoryId, HttpServletResponse response) throws IOException {
        Optional<MenuCategory> categoryOptional = categoryService.getMenuCategoryById(categoryId);

        if (categoryOptional.isPresent()) {
            MenuCategory menuCategory = categoryOptional.get();

            // Getting content type based on file suffix
            String contentType = categoryService.getContentTypes(menuCategory.getProfilePicture());

            // Content type setting based on the response
            response.setContentType(contentType);

            try (OutputStream outputStream = response.getOutputStream()) {
                outputStream.write(menuCategory.getContent());
            }
        } else {
            // Handle the case when the employee with the specified ID is not found
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

}
