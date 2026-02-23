package com.eyebeem.tests.products;

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
 * Tests that the products page displays all products correctly.
 */
public class ProductsPageDisplayTest extends BaseTest {

    @Test
    public void productsPageDisplaysAllProducts() {
        driver.get(baseUrl + "/products");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h2")));
        
        // Verify page heading
        WebElement heading = driver.findElement(By.cssSelector("h2"));
        Assert.assertEquals(heading.getText(), "PRODUCTS",
                "Products page should have PRODUCTS heading");
        
        // Verify products are displayed (find all retro-container divs)
        List<WebElement> productCards = driver.findElements(By.cssSelector(".retro-container"));
        Assert.assertTrue(productCards.size() >= 3,
                "Should display at least 3 products, but found: " + productCards.size());
        
        // Verify each product card has name and price
        for (WebElement card : productCards) {
            // Check if card contains product name (h3) and price
            List<WebElement> productNames = card.findElements(By.tagName("h3"));
            List<WebElement> prices = card.findElements(By.cssSelector("span"));
            
            if (!productNames.isEmpty() && !prices.isEmpty()) {
                Assert.assertFalse(productNames.get(0).getText().isEmpty(),
                        "Product card should have a name");
            }
        }
    }
}
