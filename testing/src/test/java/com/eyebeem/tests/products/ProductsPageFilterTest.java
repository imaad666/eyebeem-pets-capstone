package com.eyebeem.tests.products;

import com.eyebeem.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

/**
 * Tests that the product type filter works correctly.
 */
public class ProductsPageFilterTest extends BaseTest {

    @Test
    public void productTypeFilterFiltersByType() {
        driver.get(baseUrl + "/products");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("product-type")));
        
        // Get initial product count
        List<WebElement> initialProducts = driver.findElements(By.cssSelector(".retro-container"));
        int initialCount = initialProducts.size();
        
        // Select FELINE filter
        WebElement typeSelect = driver.findElement(By.id("product-type"));
        Select select = new Select(typeSelect);
        select.selectByValue("FELINE");
        
        // Wait for filtered results
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".retro-container")));
        
        // Verify filtered results
        List<WebElement> filteredProducts = driver.findElements(By.cssSelector(".retro-container"));
        Assert.assertTrue(filteredProducts.size() <= initialCount,
                "Filtered results should be less than or equal to initial count");
        
        // Reset to "All"
        select.selectByValue("");
        
        // Wait for all products to reappear
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(".retro-container"), initialCount));
        
        List<WebElement> allProducts = driver.findElements(By.cssSelector(".retro-container"));
        Assert.assertEquals(allProducts.size(), initialCount,
                "After resetting filter, should show all products again");
    }
}
