package com.example.bistro_springboot.model;

import jakarta.persistence.*;

@Entity
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




}
