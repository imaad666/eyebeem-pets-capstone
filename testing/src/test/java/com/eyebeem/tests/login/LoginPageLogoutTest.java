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
 * Tests that logout functionality works correctly.
 */
public class LoginPageLogoutTest extends BaseTest {

    @Test
    public void logoutButtonReturnsToLoginForm() {
        driver.get(baseUrl + "/login");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login-name")));
        
        // First, log in
        WebElement nameInput = driver.findElement(By.id("login-name"));
        nameInput.clear();
        nameInput.sendKeys("Test User");
        
        WebElement emailInput = driver.findElement(By.id("login-email"));
        emailInput.clear();
        emailInput.sendKeys("test@example.com");
        
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();
        
        // Wait for profile page
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h2")));
        
        // Click logout button
        WebElement logoutButton = driver.findElement(By.cssSelector("button"));
        Assert.assertTrue(logoutButton.getText().contains("LOG OUT"),
                "Logout button should be visible");
        logoutButton.click();
        
        // Wait for login form to reappear
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login-name")));
        
        // Verify we're back at login form
        WebElement loginHeading = driver.findElement(By.cssSelector("h2"));
        Assert.assertTrue(loginHeading.getText().contains("LOGIN") || loginHeading.getText().contains("SIGN UP"),
                "After logout, should see LOGIN/SIGN UP form");
    }
}
