package com.example.bistro_springboot.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.aspectj.bridge.IMessage;
import org.hibernate.annotations.NotFound;
import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class MenuCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    private String categoryName;

    private String categoryDescription;




    @OneToMany(mappedBy = "menuCategory", cascade = CascadeType.ALL)
    private List<MenuItem> itemToCategory = new ArrayList<>();

    public MenuCategory(Long categoryId, String categoryName, String categoryDescription,
                         List<MenuItem> itemToCategory) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.itemToCategory = itemToCategory;
    }

    public MenuCategory() {
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<MenuItem> getItemToCategory() {
        return itemToCategory;
    }

    public void setItemToCategory(List<MenuItem> itemToCategory) {
        this.itemToCategory = itemToCategory;
    }

    @Override
    public String toString() {
        return "MenuCategory{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", categoryDescription='" + categoryDescription +
                ", itemToCategory=" + itemToCategory +
                '}';
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }


}
