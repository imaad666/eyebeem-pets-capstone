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
 * Tests that the product search functionality works correctly.
 */
public class ProductsPageSearchTest extends BaseTest {

    @Test
    public void productSearchFiltersResults() {
        driver.get(baseUrl + "/products");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("product-search")));
        
        // Get initial product count
        List<WebElement> initialProducts = driver.findElements(By.cssSelector(".retro-container"));
        int initialCount = initialProducts.size();
        Assert.assertTrue(initialCount >= 3, "Should have at least 3 products initially");
        
        // Enter search term
        WebElement searchInput = driver.findElement(By.id("product-search"));
        searchInput.clear();
        searchInput.sendKeys("e-CAT");
        
        // Wait for filtered results
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".retro-container")));
        
        // Verify filtered results
        List<WebElement> filteredProducts = driver.findElements(By.cssSelector(".retro-container"));
        Assert.assertTrue(filteredProducts.size() <= initialCount,
                "Filtered results should be less than or equal to initial count");
        
        // Verify at least one product matches search
        boolean foundMatch = false;
        for (WebElement card : filteredProducts) {
            List<WebElement> names = card.findElements(By.tagName("h3"));
            if (!names.isEmpty() && names.get(0).getText().contains("e-CAT")) {
                foundMatch = true;
                break;
            }
        }
        Assert.assertTrue(foundMatch, "Should find at least one product matching 'e-CAT'");
    }
}
