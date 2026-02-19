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

/** Tests that products page has type filter dropdown. */
public class ProductsPageFilterDropdownTest extends BaseTest {

    @Test
    public void productsPageHasTypeFilterDropdown() {
        driver.get(baseUrl + "/products");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement filter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("product-type")));
        Assert.assertTrue(filter.isDisplayed(), "Type filter should be visible");

        List<WebElement> options = filter.findElements(By.tagName("option"));
        Assert.assertTrue(options.size() >= 2, "Filter should have at least All + one type option");
    }
}
