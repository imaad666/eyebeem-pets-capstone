package com.eyebeem.tests.navigation;

import com.eyebeem.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

/** Tests that PRODUCTS nav link navigates to products page. */
public class NavigationProductsLinkTest extends BaseTest {

    @Test
    public void productsLinkNavigatesToProductsPage() {
        driver.get(baseUrl + "/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("PRODUCTS")));
        driver.findElement(By.linkText("PRODUCTS")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("product-search")));
        Assert.assertTrue(driver.getCurrentUrl().contains("/products"));
    }
}
