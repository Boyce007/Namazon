package com.codedifferently.stayready.accounts;

import com.codedifferently.stayready.address.Address;
import com.codedifferently.stayready.order.Order;
import com.codedifferently.stayready.order.OrderStatus;
import com.codedifferently.stayready.product.Product;
import com.codedifferently.stayready.product.ProductCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class VendorTest {
    @Test
    @DisplayName("Constructor test ")
    public void constructorTest() {
        Vendor vendor = new Vendor("Steve", "Boyle", "steve@gmail.com", "123456789", "Fresh");
        String expected = "Steve Boyle steve@gmail.com 123456789 1 Fresh";
        String actual = vendor.toString();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Add product test")
    public void addProductTest01() {
        Product product = new Product("stove", ProductCategory.HOME_APPLIANCES, 100.00);
        Vendor vendor = new Vendor("Daniel", "Boyce", "danielboyce@gmail.com",
                "456982t", "Daniel's");
        vendor.addProduct(product, 3);
        Integer expected = 1;
        Integer actual = vendor.getInventoryAmount();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Add product test")
    public void addProductTest02() {
        Product product = new Product("stove", ProductCategory.HOME_APPLIANCES, 100.00);
        Vendor vendor = new Vendor("Daniel", "Boyce", "danielboyce@gmail.com",
                "456982t", "Daniel's");
        vendor.addProduct(product, 1);
        vendor.addProduct(product,3);

        Integer actual = vendor.getInventory().get(product);
        Integer expected = 4;
        Assertions.assertEquals(expected,actual);
    }



    @Test
    @DisplayName("remove product Test")
    public void removeProductTestSize() {
        Product product = new Product("stove", ProductCategory.HOME_APPLIANCES, 100.00);
        Vendor vendor = new Vendor("Daniel", "Boyce", "danielboyce@gmail.com",
                "456982t", "Daniel's");
        vendor.addProduct(product, 3);
        vendor.removeProduct(product);
        Integer expected = 0;
        Integer actual = vendor.getInventoryAmount();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("remove product Test")
    public void removeProductTestTrue() {
        Product product = new Product("stove", ProductCategory.HOME_APPLIANCES, 100.00);
        Vendor vendor = new Vendor("Daniel", "Boyce", "danielboyce@gmail.com",
                "456982t", "Daniel's");
        vendor.addProduct(product, 3);
        Integer expected = 0;
        Integer actual = vendor.getInventoryAmount();
        Assertions.assertTrue(vendor.removeProduct(product));
    }

    @Test
    @DisplayName("remove product Test")
    public void removeProductTestFalse() {
        Product product = new Product("stove", ProductCategory.HOME_APPLIANCES, 100.00);
        Product product2 = new Product("Washing Machine", ProductCategory.HOME_APPLIANCES, 100.00);
        Vendor vendor = new Vendor("Daniel", "Boyce", "danielboyce@gmail.com",
                "456982t", "Daniel's");
        vendor.addProduct(product, 3);
        Integer expected = 0;
        Integer actual = vendor.getInventoryAmount();
        Assertions.assertFalse(vendor.removeProduct(product2));
    }

    @Test
    @DisplayName("add product to showcase")
    public void addProductToShowcaseTest() {
        Product product = new Product("stove", ProductCategory.HOME_APPLIANCES, 100.00);
        Vendor vendor = new Vendor("Daniel", "Boyce", "danielboyce@gmail.com",
                "456982t", "Daniel's");
        vendor.addProduct(product, 3);
        vendor.addProductToShowCase(product, 0);
        Product expected = product;
        Product[] showcase = vendor.getShowcase();
        Product actual = showcase[0];
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Search by Category Test")
    public void SearchByCategoryTest() throws ProductNotAvailableException {
        Product product = new Product("stove", ProductCategory.HOME_APPLIANCES, 100.00);
        Vendor vendor = new Vendor("Daniel", "Boyce", "danielboyce@gmail.com",
                "456982t", "Daniel's");
        vendor.addProduct(product, 3);
        List<Product> expected = new ArrayList<>();
        expected.add(product);
        List<Product> actual = vendor.searchByCategory(ProductCategory.HOME_APPLIANCES);
        Assertions.assertTrue(expected.get(0).equals(actual.get(0)));
    }

    @Test
    @DisplayName("Search by Category Test")
    public void SearchByCategoryTestFail() throws ProductNotAvailableException {
        Product product = new Product("steve", ProductCategory.CLOTHING, 100.00);
        Vendor vendor = new Vendor("Daniel", "Boyce", "danielboyce@gmail.com",
                "456982t", "Daniel's");
        vendor.addProduct(product,4);
        Assertions.assertThrows(ProductNotAvailableException.class,()->{
           vendor.searchByCategory(ProductCategory.ATHLETICS);
        });
    }

    @Test
    @DisplayName("Search by name test ")
    public void searchByNameTest() throws ProductNotAvailableException {
        Product product = new Product("stove", ProductCategory.HOME_APPLIANCES, 100.00);
        Vendor vendor = new Vendor("Daniel", "Boyce", "danielboyce@gmail.com",
                "456982t", "Daniel's");
        vendor.addProduct(product, 3);
        List<Product> expected = new ArrayList<>();
        expected.add(product);
        List<Product> actual = vendor.searchByName("stove");
        Assertions.assertTrue(expected.get(0).equals(actual.get(0)));
    }

    @Test
    @DisplayName("Search by name test ")
    public void searchByNameTestFail() throws ProductNotAvailableException {
        Vendor vendor = new Vendor("Daniel", "Boyce", "danielboyce@gmail.com",
                "456982t", "Daniel's");
        vendor.addProduct(new Product("home", ProductCategory.HOME_APPLIANCES, 100.00), 3);
        Assertions.assertThrows(ProductNotAvailableException.class, () -> {
            vendor.searchByName("stove");
        });
    }

    @Test
    @DisplayName("Display All orders test ")
    public void displayAllOrdersTest() {
        Vendor vendor = new Vendor("Daniel", "Boyce", "danielboyce@gmail.com",
                "456982t", "Daniel's");
        vendor.getOrders().add(new Order(new Product("stove", ProductCategory.CLOTHING, 100.00)
                , new Address("spring st", "30", "Springfield", "Portland"), OrderStatus.PENDING));
        String expected = " stove Clothing 100.00 1 spring st 30 Springfield Portland PENDING 1\n";
        String actual = vendor.displayALlOrders();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Validate password Test")
    public void validatePasswordTest01() {
        Vendor vendor = new Vendor("Daniel", "Boyce", "danielboyce@gmail.com",
                "456982t", "Daniel's");
        Assertions.assertTrue(vendor.validatePassword("456982t"));
    }

    @Test
    @DisplayName("Validate password Test")
    public void validatePasswordTest02() {
        Vendor vendor = new Vendor("Daniel", "Boyce", "danielboyce@gmail.com",
                "456982t", "Daniel's");
        Assertions.assertFalse(vendor.validatePassword("456982"));
    }
}
