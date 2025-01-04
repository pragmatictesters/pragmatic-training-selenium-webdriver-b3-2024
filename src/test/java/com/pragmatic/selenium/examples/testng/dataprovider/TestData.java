package com.pragmatic.selenium.examples.testng.dataprovider;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestData {


    @DataProvider
    public Object[][] userCredentials() {
        return new Object[][]{
                {"","", "Epic sadface: Username is required"},
                {"","secret_sauce", "Epic sadface: Username is required"},
                {"standard_user","", "Epic sadface: Password is required"},
                {"standard_user","invalidPWD", "Epic sadface: Username and password do not match any user in this service"},
        };
    }

    // DataProvider that reads data from CSV
    @DataProvider(name = "csvUserCredentials")
    public Object[][] readFromCSV() throws IOException {
        String filePath = "/Users/janeshkodikara/Documents/SeleniumB3/selenium-b3/src/test/java/com/pragmatic/selenium/examples/testng/dataprovider/testdata.csv"; // Path to your CSV file
        List<Object[]> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true; // To skip the header line
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // Skip header
                }
                String[] values = line.split(",", -1); // Split by comma
                data.add(new Object[]{values[0], values[1], values[2]});
            }
        }

        // Convert List to Object[][]
        return data.toArray(new Object[0][0]);
    }
}
