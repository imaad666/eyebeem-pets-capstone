package com.eyebeem.tests.login;

import com.eyebeem.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

/** Tests that login form has NAME and EMAIL labels. */
public class LoginPageFormLabelsTest extends BaseTest {

    @Test
    public void loginFormHasNameAndEmailLabels() {
        driver.get(baseUrl + "/login");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login-name")));

        String pageText = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue(pageText.contains("NAME"), "Form should have NAME label");
        Assert.assertTrue(pageText.contains("EMAIL"), "Form should have EMAIL label");
    }
}
