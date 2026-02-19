package com.eyebeem.tests.navigation;

import com.eyebeem.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

/** Tests that CART nav link navigates to cart page. */
public class NavigationCartLinkTest extends BaseTest {

    @Test
    public void cartLinkNavigatesToCartPage() {
        driver.get(baseUrl + "/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href='/cart']")));
        driver.findElement(By.cssSelector("a[href='/cart']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h2")));
        Assert.assertTrue(driver.getCurrentUrl().contains("/cart"));
        Assert.assertEquals(driver.findElement(By.cssSelector("h2")).getText(), "CART");
    }
}
