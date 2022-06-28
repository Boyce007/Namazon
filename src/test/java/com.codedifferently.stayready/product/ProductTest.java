package com.codedifferently.stayready.product;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.Test;

public class ProductTest {

    @Test
    @DisplayName("constructor test")
    public void constructorTest() {
        Product product = new Product("Shoes",ProductCategory.CLOTHING,30.00);
        String expected = "Shoes Clothing 30.00 1";
        String actual = product.toString();
        Assertions.assertEquals(expected,actual);

    }

}
