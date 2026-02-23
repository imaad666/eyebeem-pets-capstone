package com.eyebeem.tests.cart;

import com.eyebeem.tests.BaseTest;
import com.eyebeem.tests.DataDependent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

/** Tests that CLEAR CART button empties the cart. */
@DataDependent
public class CartPageClearButtonTest extends BaseTest {

    @Test
    public void clearCartButtonEmptiesCart() {
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
            if (b.getText().contains("CLEAR CART")) {
                b.click();
                break;
            }
        }
        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("SHOP PRODUCTS")));
        Assert.assertTrue(driver.findElement(By.cssSelector(".retro-container")).getText().contains("empty"),
                "Cart should show empty after clear");
    }
}
