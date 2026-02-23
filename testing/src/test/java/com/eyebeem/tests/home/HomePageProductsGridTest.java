package com.eyebeem.tests.home;

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

/** Tests that home page displays product grid with add-to-cart buttons. */
@DataDependent
public class HomePageProductsGridTest extends BaseTest {

    @Test
    public void homePageShowsProductCardsWithAddToCart() {
        driver.get(baseUrl + "/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("hero-heading")));

        List<WebElement> addButtons = driver.findElements(By.cssSelector("button"));
        long addToCartCount = addButtons.stream().filter(b -> b.getText().contains("ADD TO CART")).count();
        Assert.assertTrue(addToCartCount >= 3, "Home page should show ADD TO CART for each product");

        List<WebElement> productNames = driver.findElements(By.cssSelector(".retro-container h3"));
        Assert.assertTrue(productNames.size() >= 3, "Should display at least 3 product names");
    }
}
