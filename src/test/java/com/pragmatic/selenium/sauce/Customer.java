package com.pragmatic.selenium.sauce;

public class Customer {


    private final String firstName;
    private final String lastName;
    private final String postcode;

    public Customer(String firstName, String lastName, String postcode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.postcode = postcode;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPostcode() {
        return postcode;
    }
}
