/*package com.example.bistro_springboot.controller;

import com.example.bistro_springboot.model.MenuItem;
import com.example.bistro_springboot.service.MenuItemService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;


@Controller
public class MenuItemController {

    private final MenuItemService menuItemService;

    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }


    @GetMapping("all/{categoryId}/itemsAdmin")
    public String getAllItemsAdmin(@PathVariable Long categoryId, Model model) {
        List<MenuItem> viewAllItems = menuItemService.getItemByCategoryId(categoryId);
        model.addAttribute("itemsAdmin", viewAllItems);
        model.addAttribute("categoryId", categoryId);
        return "itemsAdmin";
    }

    @GetMapping("johny_bistro_main/{categoryId}/itemsUser")
    public String getAllItemsUser(@PathVariable Long categoryId, Model model) {
        List<MenuItem> viewAllItems = menuItemService.getItemByCategoryId(categoryId);
        model.addAttribute("itemsUser", viewAllItems);
        model.addAttribute("categoryId", categoryId);
        return "itemsUser";
    }


    @GetMapping("/all/{categoryId}/createItem")
    public String getTaskToCreate(@PathVariable Long categoryId, Model model) {
        List<MenuItem> viewItemToCreate = menuItemService.getItemByCategoryId(categoryId);


        model.addAttribute("viewItemToCreate", viewItemToCreate);
        model.addAttribute("categoryId", categoryId.toString());
        model.addAttribute("item", new MenuItem());

        return "createItem";
    }


    @PostMapping("/all/{categoryId}/createItem")
    public String createItem(@PathVariable Long categoryId,
                             @ModelAttribute MenuItem menuItem,
                             @RequestParam("file") MultipartFile multipartFile) throws IOException {
        menuItem.setProfilePicture(multipartFile.getOriginalFilename());
        menuItem.setContent(multipartFile.getBytes());
        menuItemService.createItem(menuItem, categoryId, multipartFile);
        return "redirect:/all/" + categoryId + "/itemsAdmin";
    }

    @GetMapping("/images/{itemId}")
    public void showItemImage(@RequestParam Long id, @PathVariable Long itemId, HttpServletResponse response) throws IOException {
        Optional<MenuItem> menuItemOptional = menuItemService.getItemById(itemId);

        if (menuItemOptional.isPresent()) {
            MenuItem menuItem = menuItemOptional.get();

            // Získanie typu obsahu na základe prípony súboru
            String contentType = menuItemService.getContentTypes(menuItem.getProfilePicture());

            // Nastavenie typu obsahu v odpovedi
            response.setContentType(contentType);

            try (OutputStream outputStream = response.getOutputStream()) {
                outputStream.write(menuItem.getContent());
            }
        } else {
            // Riešenie prípadu, keď položka menu s daným ID nie je nájdená
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }





}*/