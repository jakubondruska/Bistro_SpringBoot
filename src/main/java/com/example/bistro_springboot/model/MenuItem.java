/*package com.example.bistro_springboot.model;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Optional;

@Entity
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private double price;

    private String allergens;

    private double weight;
    @Lob
    @Column(name = "contents")
    private byte[] content;

    @Column(name = "profilePictures")
    private String profilePicture;

    @Column(name = "sizes")
    private Long size;


    @ManyToOne
    @JoinColumn(name = "menu_category")
    private MenuCategory menuCategory;

    public MenuItem() {
    }

    public MenuItem(Long id, String name, String description, double price, String allergens,
                    double weight, byte[] content, String profilePicture, Long size, MenuCategory menuCategory) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.allergens = allergens;
        this.weight = weight;
        this.content = content;
        this.profilePicture = profilePicture;
        this.size = size;
        this.menuCategory = menuCategory;
    }

    public Long getId() {
        return id;
    }

    public MenuCategory getMenuCategory() {
        return menuCategory;
    }

    public void setMenuCategory(MenuCategory menuCategory) {
        this.menuCategory = menuCategory;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAllergens() {
        return allergens;
    }

    public void setAllergens(String allergens) {
        this.allergens = allergens;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", allergens='" + allergens + '\'' +
                ", weight=" + weight +
                ", content=" + Arrays.toString(content) +
                ", profilePicture='" + profilePicture + '\'' +
                ", size=" + size +
                ", menuCategory=" + menuCategory +
                '}';
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
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
}
*/