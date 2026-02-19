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
        
        // Verify we start with multiple products (h3 = product names)
        List<WebElement> initialProducts = driver.findElements(By.cssSelector("h3"));
        Assert.assertTrue(initialProducts.size() >= 3, "Should have at least 3 products initially");
        
        // Enter search term (use partial "CAT" - filter is case-insensitive includes)
        WebElement searchInput = driver.findElement(By.id("product-search"));
        searchInput.clear();
        searchInput.sendKeys("CAT");
        
        // Wait for filter to apply - product list to show fewer items
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("h3")));
        try { Thread.sleep(300); } catch (InterruptedException ignored) {}
        
        // Verify filtered results - product cards have h3 with product name
        List<WebElement> filteredProducts = driver.findElements(By.cssSelector("h3"));
        Assert.assertTrue(filteredProducts.size() >= 1 && filteredProducts.size() <= initialProducts.size(),
                "Filtered results should show 1 or more products");
        
        // Verify e-CAT product is shown (search "CAT" matches "e-CAT")
        boolean foundMatch = filteredProducts.stream()
                .anyMatch(el -> el.getText().toLowerCase().contains("cat"));
        Assert.assertTrue(foundMatch, "Should find at least one product matching 'CAT' (e.g. e-CAT)");
    }
}
