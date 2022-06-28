package com.codedifferently.stayready.accounts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CustomerTest {
    @Test
    @DisplayName("Constructor Test")
    public void constructorTest() {
        Customer customer = new Customer("John","Doe","doe@gmail.com","14951p");
        String actual = customer.toString();
        String expected = "John Doe doe@gmail.com 14951p 1";
        Assertions.assertEquals(expected,actual);
    }
}
