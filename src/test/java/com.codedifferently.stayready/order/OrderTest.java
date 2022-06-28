package com.codedifferently.stayready.order;

import com.codedifferently.stayready.address.Address;
import com.codedifferently.stayready.product.Product;
import com.codedifferently.stayready.product.ProductCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderTest {
    @Test
    @DisplayName("Constructor Test")
    public void constructorTest() {
        Order order = new Order(new Product("shirt", ProductCategory.CLOTHING,10.00)
                ,new Address("spruce","12","NewYorkCity","NY"),OrderStatus.PENDING);
        String expected = "shirt Clothing 10.00 1 spruce 12 NewYorkCity NY PENDING 1";
        String actual = order.toString();
        Assertions.assertEquals(expected,actual);
    }
}
