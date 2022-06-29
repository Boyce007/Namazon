package com.codedifferently.stayready.product;

public enum ProductCategory {
    ELECTRONICS("Electronics"), ATHLETICS("Athletics"), CLOTHING("Clothing"), HOME_APPLIANCES("Home Appliances");
   String name;

    ProductCategory(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public static String disPlayAllOptions() {
        String options = "";
        for (ProductCategory category:ProductCategory.values()) {
            options += category.name + "\n";
        }
        return options;
    }


}
