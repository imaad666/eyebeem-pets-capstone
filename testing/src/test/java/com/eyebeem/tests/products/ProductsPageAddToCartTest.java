package com.eyebeem.tests.products;

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
 * Tests that adding products to cart from products page works correctly.
 */
@DataDependent
public class ProductsPageAddToCartTest extends BaseTest {

    @Test
    public void addToCartButtonAddsProductToCart() {
        driver.get(baseUrl + "/products");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".retro-container")));
        
        // Find first "ADD TO CART" button
        List<WebElement> addToCartButtons = driver.findElements(By.cssSelector("button"));
        WebElement firstAddButton = null;
        for (WebElement button : addToCartButtons) {
            if (button.getText().contains("ADD TO CART")) {
                firstAddButton = button;
                break;
            }
        }
        
        Assert.assertNotNull(firstAddButton, "Should find at least one ADD TO CART button");
        
        // Get initial cart count from navbar
        WebElement cartLink = driver.findElement(By.partialLinkText("CART"));
        String cartTextBefore = cartLink.getText();
        
        // Click add to cart
        firstAddButton.click();
        
        // Wait a moment for cart to update
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(cartLink, cartTextBefore)));
        
        // Verify cart count increased
        String cartTextAfter = cartLink.getText();
        Assert.assertNotEquals(cartTextAfter, cartTextBefore,
                "Cart count should increase after adding product");
        Assert.assertTrue(cartTextAfter.contains("CART [1]") || cartTextAfter.contains("CART [2]"),
                "Cart should show at least 1 item");
    }
}
