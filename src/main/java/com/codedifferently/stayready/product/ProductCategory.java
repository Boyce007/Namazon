package com.codedifferently.stayready.product;

public enum ProductCategory {
    ELECTRONICS("Electronics"), ATHLETICS("Athletics"), CLOTHING("Clothing"), HOME_APPLIANCES("Hone Appliances");
    String name;

    ProductCategory(String name) {
        this.name = name;
    }
}
