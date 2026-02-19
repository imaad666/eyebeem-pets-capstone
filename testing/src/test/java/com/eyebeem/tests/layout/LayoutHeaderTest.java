package com.eyebeem.tests.layout;

import com.eyebeem.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

/** Tests that header is visible on every page. */
public class LayoutHeaderTest extends BaseTest {

    @Test
    public void headerIsVisibleOnHomePage() {
        driver.get(baseUrl + "/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("header")));
        Assert.assertTrue(header.isDisplayed(), "Header should be visible");
        Assert.assertTrue(header.findElement(By.tagName("nav")).isDisplayed(), "Nav should be in header");
    }
}
