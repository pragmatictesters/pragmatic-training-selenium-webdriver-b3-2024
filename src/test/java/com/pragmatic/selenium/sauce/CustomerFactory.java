package com.pragmatic.selenium.sauce;

import net.datafaker.Faker;

public class CustomerFactory {

    private static final Faker faker = new Faker();

    public static Customer createRandomCustomer() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String postcode = faker.address().postcode();
        return new Customer(firstName, lastName, postcode);
    }
}
