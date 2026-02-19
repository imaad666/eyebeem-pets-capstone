package com.eyebeem.tests.checkout;

import com.eyebeem.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

/** Tests that checkout shows empty message when cart is empty. */
public class CheckoutPageEmptyCartTest extends BaseTest {

    @Test
    public void checkoutShowsEmptyMessageWhenCartEmpty() {
        driver.get(baseUrl + "/checkout");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".retro-container")));
        String text = driver.findElement(By.cssSelector(".retro-container")).getText();
        Assert.assertTrue(text.contains("empty") || text.contains("Add items"), "Should show empty cart message");
    }
}
