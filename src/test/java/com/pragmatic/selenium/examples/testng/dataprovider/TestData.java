package com.pragmatic.selenium.examples.testng.dataprovider;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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


    // DataProvider to read test data from Excel file
    @DataProvider(name = "excelUserCredentials")
    public Object[][] readFromExcel() throws IOException {
        // Path to the Excel file
        String relativePath = "src/test/java/com/pragmatic/selenium/examples/testng/dataprovider/testdata.xlsx";
        String absolutePath = new File(relativePath).getAbsolutePath();

        // Load the Excel file
        FileInputStream fis = new FileInputStream(absolutePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0); // Get the first sheet

        // Create a list to store the test data
        List<Object[]> data = new ArrayList<>();

        // Skip the header row (row 0) and iterate over the rows
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            String username = row.getCell(0).getStringCellValue();
            String password = row.getCell(1).getStringCellValue();
            String expectedMessage = row.getCell(2).getStringCellValue();
            data.add(new Object[]{username, password, expectedMessage});
        }

        // Close the workbook and input stream
        workbook.close();
        fis.close();

        // Convert List<Object[]> to Object[][] for DataProvider
        return data.toArray(new Object[0][0]);
    }


    // DataProvider to read test data from XML file
    @DataProvider(name = "xmlUserCredentials")
    public Object[][] readFromXML() throws Exception {
        // Path to the XML file
        String relativePath = "src/test/java/com/pragmatic/selenium/examples/testng/dataprovider/testdata.xml";
        String absolutePath = new File(relativePath).getAbsolutePath();

        // Parse the XML file
        File xmlFile = new File(absolutePath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        // Get all <testcase> elements
        NodeList testCaseNodes = doc.getElementsByTagName("testcase");

        // Prepare data for the DataProvider
        Object[][] data = new Object[testCaseNodes.getLength()][3]; // 3 fields: username, password, expectedMessage

        for (int i = 0; i < testCaseNodes.getLength(); i++) {
            String username = testCaseNodes.item(i).getChildNodes().item(1).getTextContent();
            String password = testCaseNodes.item(i).getChildNodes().item(3).getTextContent();
            String expectedMessage = testCaseNodes.item(i).getChildNodes().item(5).getTextContent();

            data[i] = new Object[]{username, password, expectedMessage};
        }

        return data;
    }

    // DataProvider to fetch test data from SQLite database
    @DataProvider(name = "sqliteUserCredentials")
    public Object[][] fetchFromDatabase() throws Exception {
        // Path to SQLite database
        String relativePath = "src/test/resources/testdata.db";
        String absolutePath = new java.io.File(relativePath).getAbsolutePath();

        // SQLite connection URL
        String url = "jdbc:sqlite:" + absolutePath;

        // SQL query to fetch data
        String query = "SELECT username, password, expected_message FROM user_credentials";

        // Connect to SQLite database
        Connection connection = DriverManager.getConnection(url);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        // Store fetched data into a List
        List<Object[]> data = new ArrayList<>();
        while (resultSet.next()) {
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String expectedMessage = resultSet.getString("expected_message");
            data.add(new Object[]{username, password, expectedMessage});
        }

        // Close connections
        resultSet.close();
        statement.close();
        connection.close();

        // Convert List<Object[]> to Object[][] for DataProvider
        return data.toArray(new Object[0][0]);
    }
}
