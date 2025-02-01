package com.pragmatic.steps;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class SauceLoginSteps {

    private  WebDriver webDriver;




    @After
    public void after(Scenario scenario){
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Screenshot " + scenario.getName());
        }
        webDriver.close();
    }

    @Given("user has accessed the sauce demo login page")
    public void userHasAccessedTheSauceDemoLoginPage() {
        webDriver = new ChromeDriver();
        webDriver.get("https://www.saucedemo.com");
    }

    @When("user provide valid credentials")
    public void userProvideValidCredentials() {
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
    }

    @Then("user should be in the landing page")
    public void userShouldBeInTheLandingPage() {
        Assert.assertEquals(webDriver.findElement(By.cssSelector(".title")).getText(), "Products");
    }


    @When("user provide valid credentials username {string} and password {string}")
    public void userProvideValidCredentialsUsernameAndPassword(String username, String password) {
        webDriver.findElement(By.id("user-name")).sendKeys(username);
        webDriver.findElement(By.id("password")).sendKeys(password);
        webDriver.findElement(By.id("login-button")).click();
    }

    @Then("user should see error message {string}")
    public void userShouldSeeErrorMessage(String expectedError) {
        Assert.assertEquals(webDriver.findElement(By.cssSelector("h3[data-test='error']")).getText(), expectedError);
    }
}
