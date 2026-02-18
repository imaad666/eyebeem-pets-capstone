package com.eyebeem.tests.home;

import com.eyebeem.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

/**
 * Tests that all navbar links are present and clickable.
 */
public class HomePageNavbarTest extends BaseTest {

    @Test
    public void navbarContainsAllNavigationLinks() {
        driver.get(baseUrl + "/");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("header")));
        
        // Find navbar by aria-label
        WebElement navbar = driver.findElement(By.cssSelector("nav[aria-label='Main navigation']"));
        Assert.assertTrue(navbar.isDisplayed(), "Navbar should be visible");
        
        // Verify logo/home link
        WebElement homeLink = navbar.findElement(By.cssSelector("a[href='/']"));
        Assert.assertTrue(homeLink.isDisplayed(), "Home link should be visible");
        
        // Verify PRODUCTS link
        WebElement productsLink = driver.findElement(By.linkText("PRODUCTS"));
        Assert.assertTrue(productsLink.isDisplayed(), "PRODUCTS link should be visible");
        Assert.assertTrue(productsLink.getAttribute("href").contains("/products"),
                "PRODUCTS link should point to /products");
        
        // Verify USER LOGIN / SIGN UP link (partial text match)
        WebElement userLink = driver.findElement(By.partialLinkText("USER"));
        Assert.assertTrue(userLink.isDisplayed(), "USER link should be visible");
        Assert.assertTrue(userLink.getAttribute("href").contains("/login"),
                "USER link should point to /login");
        
        // Verify CART link
        WebElement cartLink = driver.findElement(By.partialLinkText("CART"));
        Assert.assertTrue(cartLink.isDisplayed(), "CART link should be visible");
        Assert.assertTrue(cartLink.getAttribute("href").contains("/cart"),
                "CART link should point to /cart");
        
        // Verify CHECKOUT link
        WebElement checkoutLink = driver.findElement(By.linkText("CHECKOUT"));
        Assert.assertTrue(checkoutLink.isDisplayed(), "CHECKOUT link should be visible");
        Assert.assertTrue(checkoutLink.getAttribute("href").contains("/checkout"),
                "CHECKOUT link should point to /checkout");
        
        // Verify ORDERS link
        WebElement ordersLink = driver.findElement(By.partialLinkText("ORDERS"));
        Assert.assertTrue(ordersLink.isDisplayed(), "ORDERS link should be visible");
        Assert.assertTrue(ordersLink.getAttribute("href").contains("/orders"),
                "ORDERS link should point to /orders");
    }
}
