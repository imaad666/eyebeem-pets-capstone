package com.eyebeem.tests.login;

import com.eyebeem.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

/**
 * Tests that the login form displays with all required fields.
 */
public class LoginPageFormTest extends BaseTest {

    @Test
    public void loginFormDisplaysNameAndEmailFields() {
        driver.get(baseUrl + "/login");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login-name")));
        
        // Verify form heading
        WebElement heading = driver.findElement(By.cssSelector("h2"));
        Assert.assertTrue(heading.getText().contains("LOGIN") || heading.getText().contains("SIGN UP"),
                "Login page should have LOGIN/SIGN UP heading");
        
        // Verify NAME input field
        WebElement nameInput = driver.findElement(By.id("login-name"));
        Assert.assertTrue(nameInput.isDisplayed(), "Name input should be visible");
        Assert.assertEquals(nameInput.getAttribute("type"), "text",
                "Name input should be text type");
        
        // Verify EMAIL input field
        WebElement emailInput = driver.findElement(By.id("login-email"));
        Assert.assertTrue(emailInput.isDisplayed(), "Email input should be visible");
        Assert.assertEquals(emailInput.getAttribute("type"), "email",
                "Email input should be email type");
        
        // Verify submit button
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        Assert.assertTrue(submitButton.isDisplayed(), "Submit button should be visible");
        Assert.assertTrue(submitButton.getText().contains("LOGIN") || submitButton.getText().contains("SIGN UP"),
                "Submit button should have LOGIN/SIGN UP text");
    }
}
