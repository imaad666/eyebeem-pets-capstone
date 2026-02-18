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
 * Tests that navigation to and from login page works correctly.
 */
public class LoginPageNavigationTest extends BaseTest {

    @Test
    public void loginPageUrlAndTitleAreCorrect() {
        driver.get(baseUrl + "/login");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login-name")));
        
        // Verify URL contains /login
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/login"),
                "URL should contain /login, but was: " + currentUrl);
        
        // Verify page title
        String title = driver.getTitle();
        Assert.assertNotNull(title, "Page title should not be null");
        Assert.assertTrue(title.contains("EYE BEE M Pets"),
                "Page title should contain app name");
        
        // Verify login form is present
        WebElement loginForm = driver.findElement(By.cssSelector("form"));
        Assert.assertTrue(loginForm.isDisplayed(), "Login form should be visible");
    }
}
