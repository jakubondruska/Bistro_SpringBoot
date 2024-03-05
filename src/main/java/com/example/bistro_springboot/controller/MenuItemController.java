package com.example.bistro_springboot.controller;

import com.example.bistro_springboot.model.MenuItem;
import com.example.bistro_springboot.service.MenuItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class MenuItemController {

    private final MenuItemService menuItemService;

    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @GetMapping("all/{id}/getAllItems")
    public ResponseEntity<List<MenuItem>> getAllItems(@PathVariable Long id) {
        List<MenuItem> viewAllItems = menuItemService.getItemByCategoryId(id);
        return new ResponseEntity<>(viewAllItems,HttpStatus.OK);
    }

    @PostMapping("/add/{id}/createItem")
    public ResponseEntity<MenuItem> createItem(@RequestBody MenuItem item, @PathVariable Long id) {

        menuItemService.createItem(item,id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/edit/{categoryId}/editItem/{taskId}")
    public ResponseEntity<MenuItem> editItem(@PathVariable Long taskId,
                                             @PathVariable Long categoryId, @RequestBody MenuItem editedItem) {
        menuItemService.editItem(taskId, editedItem);

        String convertedCategoryId = categoryId.toString();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("delete/{categoryId}/deleteItem/{taskId}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long taskId, @PathVariable Long categoryId ) {
        menuItemService.deleteItem(taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}