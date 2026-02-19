package com.eyebeem.tests.layout;

import com.eyebeem.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

/** Tests that footer is visible on every page. */
public class LayoutFooterTest extends BaseTest {

    @Test
    public void footerIsVisibleOnHomePage() {
        driver.get(baseUrl + "/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement footer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("footer")));
        Assert.assertTrue(footer.isDisplayed(), "Footer should be visible");
    }
}
