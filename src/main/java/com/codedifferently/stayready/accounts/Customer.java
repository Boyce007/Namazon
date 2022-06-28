package com.codedifferently.stayready.accounts;

import com.codedifferently.stayready.product.Product;

public class Customer extends  Account {

    public Customer(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);
    }

    public Product purchase(Product product) {
        return product;
    }

}
