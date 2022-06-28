package com.codedifferently.stayready.namazon;

import com.codedifferently.stayready.accounts.Account;
import com.codedifferently.stayready.accounts.Customer;
import com.codedifferently.stayready.accounts.Vendor;

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

    public Vendor selectVendor(Vendor vendor) throws VendorNotAvailableException {
        for (Vendor v : vendors) {
            if (vendor.equals(v)) {
                return v;
            }
        }
        throw new VendorNotAvailableException();
    }

    public Account promptUserToCreateAccount(Scanner scanner, String type) {
        System.out.println("Please Enter your Account Information ");
        System.out.println("firstName: ");
        String firstName = scanner.next();
        System.out.println("LastName: ");
        String lastName = scanner.next();
        String brandName = "";
        if (type.equals("v")) {
            System.out.println("BrandName: ");
            brandName = scanner.next();
        }
        System.out.println("email: ");
        String email = scanner.next();
        System.out.println("password: ");
        String password = scanner.next();
        Boolean isValid = false;
        while (isValid == false) {
            try {
                if (type.equals("v")) {
                    Vendor user = signUpAsAVendor(firstName, lastName, email, password, brandName);
                    isValid = true;
                    return user;

                } else {
                    Customer user = signUpAsACustomer(firstName, lastName, email, password);
                    isValid = true;
                    return user;
                }
            } catch (AccountCreationException ex) {
                System.out.println("email is invalid enter again: ");
                email = scanner.next();
            }
        }
        return null;
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
        switch (selection) {
            case "v":
                System.out.println("Enter BrandName:");
                String brandName  = scanner.next();
                Boolean flag = true;
                while (flag == true) {
                    try {
                        currentUser = signUpAsAVendor(firstName, lastName, email, password, brandName);
                        flag = false;
                    } catch (AccountCreationException e) {
                        System.out.println("Invalid email enter again: ");
                        email = scanner.next();
                    }
                }
            case "c":

                try {
                    currentUser = signUpAsACustomer(firstName,lastName,email,password);
                } catch (AccountCreationException e) {
                    System.out.println("Invalid email enter again ");
                }

        }

    }

    public void startUp() {
        Boolean flag = true;
        while (flag){
            if(currentUser == null)
                flag = welcomeScreen();
            else{
                userOptionsScreen();
            }
        }
    }

    private void userOptionsScreen() {

    }


    public static void main(String[] args) {
        Namazon namazon = new Namazon();
        namazon.startUp();
    }



}
