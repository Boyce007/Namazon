package com.codedifferently.stayready.accounts;

public abstract class Account {
    public static long ID_COUNT = 1;
    private static Long id;
    private String firstName;
    private String lastName;
    public String email;
    public String password;

    public Account(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        id = ID_COUNT++;
    }

    public Boolean validatePassword(String password) {
        if (password.equals(this.password)){
            return true;
        }
        return false;
    }

    public Boolean validateEmail(String email) {
        if (email.equals(this.email)) {
            return true;
        }
        return false;
    }


    public Long getId() {
        return id;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String toString() {
        return String.format("%s %s %s %s %d",firstName,lastName,email,password,id);
    }

}
