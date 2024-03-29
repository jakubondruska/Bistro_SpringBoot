/*package com.example.bistro_springboot.model;

import jakarta.persistence.*;

@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    private String city;

    private Integer postalCode;

    private String street;

    private String phoneNumber;

    private Boolean orderDelivered;

    public Order(Long id, String name, String city, Integer postalCode, String street, String phoneNumber, Boolean orderDelivered) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.postalCode = postalCode;
        this.street = street;
        this.phoneNumber = phoneNumber;
        this.orderDelivered = orderDelivered;
    }

    public Order() {
    }

    public Long getId() {
        return id;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getOrderDelivered() {
        return orderDelivered;
    }

    public void setOrderDelivered(Boolean orderDelivered) {
        this.orderDelivered = orderDelivered;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", postalCode=" + postalCode +
                ", street='" + street + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", orderDelivered=" + orderDelivered +
                '}';
    }
}
*/