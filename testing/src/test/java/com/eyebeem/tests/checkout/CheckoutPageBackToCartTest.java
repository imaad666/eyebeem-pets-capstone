package com.eyebeem.tests.checkout;

import com.eyebeem.tests.BaseTest;
import com.eyebeem.tests.DataDependent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

/** Tests that BACK TO CART link navigates to cart from checkout. */
@DataDependent
public class CheckoutPageBackToCartTest extends BaseTest {

    @Test
    public void backToCartLinkNavigatesToCart() {
        driver.get(baseUrl + "/products");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".retro-container")));
        for (WebElement b : driver.findElements(By.cssSelector("button"))) {
            if (b.getText().contains("ADD TO CART")) {
                b.click();
                break;
            }
        }
        driver.findElement(By.cssSelector("a[href='/cart']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h2")));
        for (WebElement b : driver.findElements(By.cssSelector("button"))) {
            if ("GO TO CHECKOUT".equals(b.getText())) {
                b.click();
                break;
            }
        }
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("checkout-street")));

        WebElement backLink = driver.findElement(By.partialLinkText("BACK TO CART"));
        backLink.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h2")));
        Assert.assertTrue(driver.getCurrentUrl().contains("/cart"), "Should navigate to cart");
    }
}
