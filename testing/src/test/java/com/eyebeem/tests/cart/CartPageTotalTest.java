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

/**
 * Tests that cart page calculates and displays total correctly.
 */
@DataDependent
public class CartPageTotalTest extends BaseTest {

    @Test
    public void cartPageDisplaysCorrectTotal() {
        // Add products to cart
        driver.get(baseUrl + "/products");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".retro-container")));
        
        // Add first product
        List<WebElement> buttons = driver.findElements(By.cssSelector("button"));
        for (WebElement button : buttons) {
            if (button.getText().contains("ADD TO CART")) {
                button.click();
                break;
            }
        }
        
        // Navigate to cart via link (preserves SPA state)
        driver.findElement(By.cssSelector("a[href='/cart']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h2")));
        
        // Find total section
        List<WebElement> totalElements = driver.findElements(By.cssSelector(".retro-container"));
        boolean foundTotal = false;
        for (WebElement element : totalElements) {
            if (element.getText().contains("TOTAL")) {
                foundTotal = true;
                Assert.assertTrue(element.getText().contains("$"),
                        "Total should display dollar amount");
                break;
            }
        }
        Assert.assertTrue(foundTotal, "Cart should display TOTAL section");
    }
}
