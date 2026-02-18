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
 * Tests that submitting the login form with valid data works correctly.
 */
public class LoginPageSubmitTest extends BaseTest {

    @Test
    public void loginFormAcceptsValidCredentials() {
        driver.get(baseUrl + "/login");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login-name")));
        
        // Fill in name field
        WebElement nameInput = driver.findElement(By.id("login-name"));
        nameInput.clear();
        nameInput.sendKeys("Test User");
        
        // Fill in email field
        WebElement emailInput = driver.findElement(By.id("login-email"));
        emailInput.clear();
        emailInput.sendKeys("test@example.com");
        
        // Submit form
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();
        
        // Wait for profile page to appear (user logged in)
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h2")));
        
        // Verify we're on profile page
        WebElement profileHeading = driver.findElement(By.cssSelector("h2"));
        Assert.assertTrue(profileHeading.getText().contains("USER PROFILE"),
                "After login, should see USER PROFILE heading");
        
        // Verify user info is displayed
        WebElement userInfo = driver.findElement(By.cssSelector("p"));
        Assert.assertTrue(userInfo.getText().contains("Test User"),
                "Profile should display user name");
        Assert.assertTrue(userInfo.getText().contains("test@example.com"),
                "Profile should display user email");
    }
}
