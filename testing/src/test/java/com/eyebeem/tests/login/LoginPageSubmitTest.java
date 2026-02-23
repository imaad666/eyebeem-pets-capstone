package com.eyebeem.tests.login;

import com.eyebeem.tests.BaseTest;
import com.eyebeem.tests.DataDependent;
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
@DataDependent
public class LoginPageSubmitTest extends BaseTest {

    @Test
    public void loginFormAcceptsValidCredentials() {
        driver.get(baseUrl + "/login");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login-name")));
        
        // Fill in name field
        WebElement nameInput = driver.findElement(By.id("login-name"));
        nameInput.clear();
        nameInput.sendKeys("Test User");
        
        // Fill in email field
        WebElement emailInput = driver.findElement(By.id("login-email"));
        emailInput.clear();
        emailInput.sendKeys("test@eyebeem.com");
        
        // Submit form
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();
        
        // Wait for async login + profile to appear (LOG OUT button only shows when logged in)
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'LOG OUT')]")));
        
        // Verify we're on profile page
        WebElement profileHeading = driver.findElement(By.xpath("//h2[contains(text(),'USER PROFILE')]"));
        Assert.assertNotNull(profileHeading, "After login, should see USER PROFILE heading");
        
        // Verify user info is displayed
        WebElement userInfo = driver.findElement(By.cssSelector(".retro-container p"));
        Assert.assertTrue(userInfo.getText().contains("Test User"),
                "Profile should display user name");
        Assert.assertTrue(userInfo.getText().contains("test@eyebeem.com"),
                "Profile should display user email");
    }
}
