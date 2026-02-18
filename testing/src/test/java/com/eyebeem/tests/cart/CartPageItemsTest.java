package com.eyebeem.tests.cart;

import com.eyebeem.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

/**
 * Tests that cart page displays items correctly when cart has products.
 */
public class CartPageItemsTest extends BaseTest {

    @Test
    public void cartPageDisplaysItemsWhenCartHasProducts() {
        // First add a product to cart
        driver.get(baseUrl + "/products");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".retro-container")));
        
        // Find and click first ADD TO CART button
        List<WebElement> buttons = driver.findElements(By.cssSelector("button"));
        for (WebElement button : buttons) {
            if (button.getText().contains("ADD TO CART")) {
                button.click();
                break;
            }
        }
        
        // Navigate to cart page
        driver.get(baseUrl + "/cart");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h2")));
        
        // Verify cart heading
        WebElement heading = driver.findElement(By.cssSelector("h2"));
        Assert.assertEquals(heading.getText(), "CART",
                "Cart page should have CART heading");
        
        // Verify cart items are displayed (find list items)
        List<WebElement> cartItems = driver.findElements(By.cssSelector(".retro-container li"));
        Assert.assertTrue(cartItems.size() > 0,
                "Cart should display at least one item");
        
        // Verify item shows product name and quantity
        WebElement firstItem = cartItems.get(0);
        String itemText = firstItem.getText();
        Assert.assertFalse(itemText.isEmpty(), "Cart item should display product information");
    }
}
