package com.pragmatic.selenium.examples.testng.dataprovider;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
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

        String relativePath = "src/test/java/com/pragmatic/selenium/examples/testng/dataprovider/testdata.csv";

        // Construct the absolute path
        File file = new File(relativePath);
        String filePath = file.getAbsolutePath();

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


    // Define a POJO class to map JSON objects
    static class TestDataCredentials {
        String username;
        String password;
        String expectedMessage;
    }

    // DataProvider that reads test data from a JSON file
    @DataProvider(name = "jsonUserCredentials")
    public Object[][] readFromJSON() throws IOException {
        // Path to the JSON file
        String relativePath = "src/test/java/com/pragmatic/selenium/examples/testng/dataprovider/testdata.json";
        String absolutePath = new File(relativePath).getAbsolutePath();

        // Use Gson to parse the JSON file into a List of TestData objects
        Gson gson = new Gson();
        Type listType = new TypeToken<List<TestDataCredentials>>() {}.getType();
        List<TestDataCredentials> testDataCredentialsList;

        try (FileReader reader = new FileReader(absolutePath)) {
            testDataCredentialsList = gson.fromJson(reader, listType);
        }

        // Convert List<TestData> to Object[][] for TestNG DataProvider
        Object[][] data = new Object[testDataCredentialsList.size()][3];
        for (int i = 0; i < testDataCredentialsList.size(); i++) {
            TestDataCredentials testDataCredentials = testDataCredentialsList.get(i);
            data[i] = new Object[]{testDataCredentials.username, testDataCredentials.password, testDataCredentials.expectedMessage};
        }
        return data;
    }
}
