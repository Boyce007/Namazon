package com.codedifferently.stayready.namazon;

import com.beust.ah.A;
import com.codedifferently.stayready.accounts.Account;
import com.codedifferently.stayready.accounts.Customer;
import com.codedifferently.stayready.accounts.Vendor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NamazonTest {

    @Test
    @DisplayName("Sign in test ")
    public void signInTest() throws AccountAuthenticationException {
        Namazon namazon = new Namazon();
        namazon.getVendors().add(new Vendor("Daniel", "Boyce", "dboyce@gmail.com", "123abc"
                , "Danny's"));
        String actual = namazon.signIn("dboyce@gmail.com", "123abc").toString();
        String expected = "Daniel Boyce dboyce@gmail.com 123abc 1 Danny's";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Sign in Fail test")
    public void signInTestFail() {
        Assertions.assertThrows(AccountAuthenticationException.class, () -> {
            Namazon namazon = new Namazon();
            namazon.getVendors().add(new Vendor("Daniel", "Boyce", "dboyce@gmail.com", "123abc"
                    , "Danny's"));
            Account actual = namazon.signIn("dbiyce@gmail.com", "5678");
        });
    }

    @Test
    @DisplayName("sign up as a Vendor Test ")
    public void signUpAsAVendorTest() throws AccountCreationException {
        Namazon namazon = new Namazon();
        String expected = namazon.signUpAsAVendor("Daniel", "Boyce", "daniel@gmail.com",
                "1394dsf", "Fresh").toString();
        String actual = "Daniel Boyce daniel@gmail.com 1394dsf 1 Fresh";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Sign up as a vendor Fail")
    public void signUpAsAVendorTestFail() {
        Assertions.assertThrows(AccountCreationException.class, () -> {
            Namazon namazon = new Namazon();
            Vendor expected = namazon.signUpAsAVendor("Daniel", "Boyce", "daniel#gmail.com",
                    "1394dsf", "Fresh");
        });
    }

    @Test
    @DisplayName("sign up as a Customer test")
    public void signUpAsACustomerTest() throws AccountCreationException {
        Namazon namazon = new Namazon();
        String expected = namazon.signUpAsACustomer("Daniel", "Boyce", "daniel@gmail.com",
                "1394dsf").toString();
        String actual = "Daniel Boyce daniel@gmail.com 1394dsf 1";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("sign up as a Customer test")
    public void signUpAsACustomerTestFail() throws AccountCreationException {
        Assertions.assertThrows(AccountCreationException.class, () -> {
            Namazon namazon = new Namazon();
            Customer expected = namazon.signUpAsACustomer("Daniel", "Boyce", "daniel#gmail.com",
                    "1394dsf");
        });
    }

    @Test
    @DisplayName("Is email valid Test ")
    public void IsEmailUnique() {
        Namazon namazon = new Namazon();
        namazon.getVendors().add(new Vendor("Daniel", "Boyce", "dboyce@gmail.com", "123abc"
                , "Danny's"));
        Assertions.assertTrue(namazon.isEmailUnique("jeff@gamil.com"));
    }

    @Test
    @DisplayName("Is email valid Test false ")
    public void IsEmailUniqueFalse() {
        Namazon namazon = new Namazon();
        namazon.getVendors().add(new Vendor("Daniel", "Boyce", "dboyce@gmail.com", "123abc"
                , "Danny's"));
        Assertions.assertFalse(namazon.isEmailUnique("dboyce@gmail.com"));
    }

    @Test
    @DisplayName("Is email valid test ")
    public void isEmailValidTest() {
        Namazon namazon = new Namazon();
        Assertions.assertTrue(namazon.isEmailValid("Dnice@Gamil.com"));
    }

    @Test
    @DisplayName("Is email valid test ")
    public void isEmailValidTestFail() {
        Namazon namazon = new Namazon();
        Assertions.assertFalse(namazon.isEmailValid("Dnice#gmail.com"));
    }

    @Test
    @DisplayName("Select Vendor Test")
    public void selectVendorTest() {
        Namazon namazon = new Namazon();
        Vendor vendor = new Vendor("Daniel", "Boyce", "dboyce@gmail.com", "123abc"
                ,"Danny's");
        namazon.getVendors().add(vendor);
        String actual = vendor.toString();
        String expected = "Daniel Boyce dboyce@gmail.com 123abc 1 Danny's";
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Select Vendor Fail Test")
    public void selectVendorTestFail() {
        Namazon namazon = new Namazon();
        Vendor vendor = new Vendor("Daniel", "Boyce", "dboyce@gmail.com", "123abc"
                ,"Danny's");
        Assertions.assertThrows(VendorNotAvailableException.class,()->{
           namazon.selectVendor(vendor);
        });
    }
}
