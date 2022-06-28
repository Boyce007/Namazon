package com.codedifferently.stayready.order;

import com.codedifferently.stayready.address.Address;
import com.codedifferently.stayready.product.Product;

public class Order {
    public static Long ID_Count = Long.valueOf(1);
    private long id;
    private Product product;
    private Address destination;
    private OrderStatus status;

    public Order(Product product, Address destination, OrderStatus status) {
        this.product = product;
        this.destination = destination;
        this.status = status;
        this.id = ID_Count++;
    }

    public static Long getID_Count() {
        return ID_Count;
    }

    public Product getProduct() {
        return product;
    }

    public Address getDestination() {
        return destination;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public String toString() {
        return String.format("%s %s %s %d", product.toString(), destination.toString(), status,id);
    }
}