package com.pragmatic.selenium.examples.email;

import com.mailosaur.MailosaurClient;
import com.mailosaur.MailosaurException;
import com.mailosaur.models.Code;
import com.mailosaur.models.Message;
import com.mailosaur.models.MessageSearchParams;
import com.mailosaur.models.SearchCriteria;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Date;

public class EmailIntegrationTest {

    // Available in the API tab of a server
    String apiKey;
    String serverId;

    MailosaurClient mailosaur;
    SearchCriteria criteria;
    MessageSearchParams params;

    WebDriver driver;


    @BeforeClass
    public void beforeClass(){
         //apiKey = System.getenv("MAILOSAUR_API_KEY");
         //serverId = System.getenv("MAILOSAUR_SERVER_ID");
         apiKey = "ik13CNx8nb3deBvb";
         serverId = "l5gzya4r";
    }

    @BeforeMethod
    public void beforeMethod() throws MailosaurException {

        mailosaur = new MailosaurClient(apiKey);
       // mailosaur.messages().deleteAll(serverId);
        criteria = new SearchCriteria();
        params = new MessageSearchParams();
        params
                .withServer(serverId)
                .withTimeout(12000)
                .withReceivedAfter(new Date().getTime());

        driver = new ChromeDriver();

    }

    @AfterMethod
    public void afterMethod() throws MailosaurException {
        mailosaur.messages().deleteAll(serverId);
        driver.quit();

    }

    @Test
    public void testEmailWithSelenium() throws IOException, MailosaurException {

        String testEmail = mailosaur.servers().generateEmailAddress(serverId);

        driver.get("https://example.mailosaur.com/password-reset");
        driver.findElement(By.id("email")).sendKeys(testEmail);
        driver.findElement(By.xpath("//button")).click();

        criteria.withSentTo(testEmail);
        Message message = mailosaur.messages().get(params, criteria);

        Assert.assertNotNull(message);
        Assert.assertEquals(message.subject(), "Set your new password for ACME Product");
        Assert.assertEquals(message.to().get(0).email(), testEmail);
        Assert.assertEquals(message.from().get(0).email(), "noreply@example.mailosaur.net");

        String linkText = message.html()
                .links()
                .get(0)
                .text()
                .replace("\n", "")
                .replace("                        ", " ")
                .trim(); // Added trim() for safety

        Assert.assertEquals(linkText, "Set my password");
        Assert.assertEquals(message.html().links().get(0).href(), "https://example.mailosaur.com/set-password");

        driver.get(message.html().links().get(0).href());

        //Resetting the password
        driver.findElement(By.id("password")).sendKeys("PTL@#321");
        driver.findElement(By.id("confirmPassword")).sendKeys("PTL@#321");
        driver.findElement(By.xpath("//button[text()='Reset my password']")).click();

        Assert.assertEquals(driver.findElement(By.tagName("h1")).getText(), "Your new password has been set!");
    }


    @Test
    public void testOTP() throws IOException, MailosaurException {

        String testEmail = mailosaur.servers().generateEmailAddress(serverId);

        driver.get("https://example.mailosaur.com/otp");
        driver.findElement(By.id("email")).sendKeys(testEmail);
        driver.findElement(By.tagName("button")).click();

        criteria.withSentTo(testEmail);
        Message message = mailosaur.messages().get(params, criteria);

        Assert.assertNotNull(message);
        Assert.assertEquals(message.subject(), "Here is your access code for ACME Product");
        Assert.assertEquals(message.to().get(0).email(), testEmail);
        Assert.assertEquals(message.from().get(0).email(), "noreply@example.mailosaur.net");

        Code firstCode = message.html().codes().get(0);
        Assert.assertEquals(message.html().codes().size(),1); //Assert number of
        Assert.assertTrue(firstCode.value().matches("[0-9]{6}")); //Assert number of
    }

}
