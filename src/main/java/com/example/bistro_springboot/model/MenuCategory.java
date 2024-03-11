package com.example.bistro_springboot.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class MenuCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    private String categoryName;

    private String categoryDescription;

    private String profilePicture;

    private Long size;

    private byte[] content;


    @OneToMany(mappedBy = "menuCategory", cascade = CascadeType.ALL)
    private List<MenuItem> itemToCategory = new ArrayList<>();

    public MenuCategory(Long categoryId, String categoryName, String categoryDescription, String profilePicture, Long size, byte[] content, List<MenuItem> itemToCategory) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.profilePicture = profilePicture;
        this.size = size;
        this.content = content;
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
                ", categoryDescription='" + categoryDescription + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", size=" + size +
                ", content=" + Arrays.toString(content) +
                ", itemToCategory=" + itemToCategory +
                '}';
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
