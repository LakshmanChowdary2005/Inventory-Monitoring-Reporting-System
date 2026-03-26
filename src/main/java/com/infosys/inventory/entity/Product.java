package com.infosys.inventory.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="Product name required")
    private String name;

    @NotBlank(message="Category required")
    @Pattern(regexp="^\\S+$",message="Category cannot contain spaces")
    private String category;

    private int quantity;

    private double price;

    public Product(){}

    public Product(String name,String category,int quantity,double price){
        this.name=name;
        this.category=category;
        this.quantity=quantity;
        this.price=price;
    }

    public Long getId(){return id;}

    public String getName(){return name;}
    public void setName(String name){this.name=name;}

    public String getCategory(){return category;}
    public void setCategory(String category){this.category=category;}

    public int getQuantity(){return quantity;}
    public void setQuantity(int quantity){this.quantity=quantity;}

    public double getPrice(){return price;}
    public void setPrice(double price){this.price=price;}

}