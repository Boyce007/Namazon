package com.codedifferently.stayready.address;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class AddressTest {

    @Test
    @DisplayName("Constructor Test")
    public void ConstructorTest() {
        Address address = new Address("spring st","30","Springfield","Portland");
        String expected = "spring st 30 Springfield Portland";
        String actual = address.toString();
        Assertions.assertEquals(expected,actual);
    }
}
