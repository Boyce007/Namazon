package com.codedifferently.stayready.accounts;

import com.codedifferently.stayready.address.Address;
import com.codedifferently.stayready.order.Order;
import com.codedifferently.stayready.order.OrderStatus;
import com.codedifferently.stayready.product.Product;
import com.codedifferently.stayready.product.ProductCategory;

import java.util.*;

public class Vendor extends Account {

    private String brandName;
    private Map<Product, Integer> inventory;
    private Product[] showcase;
    private List<Order> orders;

    public Vendor(String firstName, String lastName, String email, String password, String brandName) {
        super(firstName, lastName, email, password);
        this.brandName = brandName;
        inventory = new HashMap<>();
        showcase = new Product[5];
        orders = new ArrayList<>();

    }

    public void addProduct(Product product, Integer amount) {
        if (inventory.containsKey(product)) {
            Integer newAmount = inventory.get(product) + amount;
            inventory.put(product,newAmount);
            return;
        }
        inventory.put(product, amount);
    }
    public void purchaseFromVendor(Product product, Address address) throws ProductNotAvailableException {
        if (inventory.containsKey(product)) {
            if (inventory.get(product) == 0) {
                System.out.println("Sorry we are out of that product");
            } else {
                sellProduct(product);
                Order order = new Order(product, address, OrderStatus.PENDING);
                orders.add(order);
            }
        } else{
            throw new ProductNotAvailableException();
        }
    }
    private void sellProduct(Product product){
        Integer newAmount = inventory.get(product) - 1;
        inventory.put(product, newAmount);
    }


    public Boolean removeProduct(Product product) {
        if (inventory.containsKey(product)) {
            inventory.remove(product);
            return true;
        }
        return false;
    }

    public void addProductToShowCase(Product product, Integer slot) {
        Product productToAdd = null;
        for (Product item : inventory.keySet()) {
            if (item.equals(product)) ;
            productToAdd = product;
        }
        showcase[slot] = productToAdd;
    }

    public List<Product> searchByCategory(ProductCategory category) throws ProductNotAvailableException {
        List<Product> searched = new ArrayList<>();
        for (Product c : inventory.keySet()) {
            ProductCategory looking = c.getCategory();
            if (looking.name().equals(category.name())) {
                searched.add(c);

            } else {
                throw new ProductNotAvailableException();
            }

        }
        return searched;
    }

    public List<Product> searchByName(String name) throws ProductNotAvailableException {
        List<Product> searched = new ArrayList<>();
        for (Product product : inventory.keySet()) {

            if (product.getName().equals(name)) {
                searched.add(product);

            } else {
                throw new ProductNotAvailableException();
            }
        }
        return searched;
    }

    public String displayALlOrders() {
        String ordersDisplayed = " ";
        for (Order order : orders) {
            ordersDisplayed += order.toString() + "\n";
        }
        return ordersDisplayed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vendor vendor = (Vendor) o;
        return Objects.equals(brandName, vendor.brandName) && Objects.equals(inventory, vendor.inventory) && Arrays.equals(showcase, vendor.showcase) && Objects.equals(orders, vendor.orders);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(brandName, inventory, orders);
        result = 31 * result + Arrays.hashCode(showcase);
        return result;
    }

    public Integer getInventoryAmount() {
        return inventory.size();
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Map<Product, Integer> getInventory() {
        return inventory;
    }


    public Product[] getShowcase() {
        return showcase;
    }

    public List<Order> getOrders() {
        return orders;
    }


    public String toString() {
        return String.format("%s %s", super.toString(), brandName);
    }

}
