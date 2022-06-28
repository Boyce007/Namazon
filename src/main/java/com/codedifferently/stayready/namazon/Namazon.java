package com.codedifferently.stayready.namazon;

import com.codedifferently.stayready.accounts.Account;
import com.codedifferently.stayready.accounts.Customer;
import com.codedifferently.stayready.accounts.Vendor;
import com.codedifferently.stayready.product.Product;
import com.codedifferently.stayready.product.ProductCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Namazon {

    private List<Customer> customers;
    private List<Vendor> vendors;
    Scanner scanner;
    Account currentUser = null;
    Vendor currentVendor = null;
    Customer currentCustomer = null;

    public Namazon() {
        customers = new ArrayList<>();
        vendors = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public Account signIn(String email, String password) throws AccountAuthenticationException {
        List<Account> accounts = new ArrayList<>();
        accounts.addAll(vendors);
        accounts.addAll(customers);
        for (Account account : accounts) {
            if (email.equals(account.getEmail()) && password.equals(account.getPassword())) {
                return account;
            }
        }
        throw new AccountAuthenticationException("Invalid information");
    }

    public Vendor signUpAsAVendor(String firstName, String lastName, String email, String password, String brandName) throws AccountCreationException {
        Vendor vendor = new Vendor(firstName, lastName, email, password, brandName);
        if ((isEmailUnique(email)) && (isEmailValid(email))) {
            vendors.add(vendor);
            return vendor;

        }
        throw new AccountCreationException("Invalid Email");
    }

    public Customer signUpAsACustomer(String firstName, String lastName, String email, String password) throws AccountCreationException {
        Customer customer = new Customer(firstName, lastName, email, password);
        if ((isEmailUnique(email)) && (isEmailValid(email))) {
            customers.add(customer);
            return customer;
        }
        throw new AccountCreationException("Invalid email");
    }

    public Boolean isEmailUnique(String email) {
        List<Account> accounts = new ArrayList<>();
        accounts.addAll(vendors);
        accounts.addAll(customers);
        for (Account account : accounts) {
            if (email.equals(account.getEmail())) {
                return false;
            }
        }
        return true;
    }

    public Boolean isEmailValid(String email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Vendor> getVendors() {
        return vendors;
    }

    public Vendor selectVendor(String vendor) throws VendorNotAvailableException {
        for (Vendor v : vendors) {
            if (v.getBrandName().equals(vendor)) {
                return v;
            }
        }
        throw new VendorNotAvailableException();
    }

    private Boolean welcomeScreen() {
        Boolean flag = true;
        String output = "Welcome to Namazon. Please select from the following options."
                + "\nPress 1 to login"
                + "\nPress 2 to create new account"
                + "\nPress 3 to exit";
        System.out.println(output);
        String selection = scanner.next();
        switch (selection){
            case "1":
                attemptSignIn();
                break;
            case "2":
                attemptSignUp();
                break;
            case "3":
            default:
                System.out.println("Good Bye");
                flag = false;
        }
        return flag;

    }

    private void attemptSignIn() {

        System.out.println("Please enter valid email:");
        String email = scanner.next();
        System.out.println("Please enter valid password");
        String password = scanner.next();
        try {
            currentUser = signIn(email, password);
        } catch (AccountAuthenticationException e) {
            System.out.println("Invalid user credentials");

        }

    }

    private void attemptSignUp() {
        System.out.println("Enter valid first name: ");
        String firstName = scanner.next();
        System.out.println("Enter valid last name: ");
        String lastName = scanner.next();
        System.out.println("Enter valid email: ");
        String email = scanner.next();
        System.out.println("Enter password: ");
        String password = scanner.next();
        System.out.println("Are you Customer or vendor(c for customer v for vendor)");
        String selection = scanner.next();
        Boolean flag = true;
        if (selection.equals("v")) {
            System.out.println("Enter BrandName:");
            String brandName = scanner.next();
            while (flag == true) {
                try {
                    currentVendor =  signUpAsAVendor(firstName, lastName, email, password, brandName);
                    flag = false;
                } catch (AccountCreationException e) {
                    System.out.println("Invalid email enter again: ");
                    email = scanner.next();
                }
            }
        } else if (selection.equals("c")) {
            try {
                currentCustomer =  signUpAsACustomer(firstName, lastName, email, password);
                flag = false;
            } catch (AccountCreationException e) {
                System.out.println("Invalid email enter again ");
            }

        }
    }

    public void startUp() {
        Boolean flag = true;
        while (flag){
            if(currentVendor == null && currentCustomer == null) {
                flag = welcomeScreen();
            }else if(!currentVendor.equals(null)){
                flag = VendorOptionsScreen();
            } else if(!currentCustomer.equals(null));
        }
    }

    private Boolean VendorOptionsScreen() {
        Boolean flag = true;
        String output = "Hello " + currentVendor.getFirstName()
                + "\nPress 1 to View inventory"
                + "\nPress 2 to add products"
                + "\nPress 3 to remove a product"
                + "\nPress 4 to remove a exit";
        System.out.println(output);
        Integer selection = scanner.nextInt();

        if (selection == 1 ){
            System.out.println("You have " + currentVendor.getInventoryAmount() +" products in  your inventory " );
        } else if(selection == 2) {
            System.out.println("Enter a product name: ");
            String productName = scanner.next();
            System.out.println("Enter a category : ");

            //Product addedProduct = new Product(productName,)
            //currentVendor.addProduct();
        } else if (selection == 3) {
            System.out.println("Not done");
        } else{
            System.out.println("GoodBye");
            flag = false;
        }
        return flag;
    }


    public static void main(String[] args) {
        Namazon namazon = new Namazon();
        namazon.startUp();
    }



}
