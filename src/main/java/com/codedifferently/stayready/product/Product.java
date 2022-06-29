package com.codedifferently.stayready.product;

import java.util.Objects;

public class Product {

    public static Long ID_Count = Long.valueOf(1);
    private Long id;
    private String name;
    private ProductCategory category;
    private Double price;

    public Product(String name,ProductCategory category,Double price){
        this.name = name;
        this.category = category;
        this.price = price;
        this.id = ID_Count++;

    }

    public static Long getID_Count() {
        return ID_Count;
    }

    public static void setID_Count(Long ID_Count) {
        Product.ID_Count = ID_Count;
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

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && category == product.category && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category, price);
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    public String toString() {
        return String.format("%s %s %.2f %d",name,category.name,price,id);
    }
}
