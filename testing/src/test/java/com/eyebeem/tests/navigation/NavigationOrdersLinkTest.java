package com.eyebeem.tests.navigation;

import com.eyebeem.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

/** Tests that ORDERS nav link navigates to orders page. */
public class NavigationOrdersLinkTest extends BaseTest {

    @Test
    public void ordersLinkNavigatesToOrdersPage() {
        driver.get(baseUrl + "/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href='/orders']")));
        driver.findElement(By.cssSelector("a[href='/orders']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h2")));
        Assert.assertTrue(driver.getCurrentUrl().contains("/orders"));
        Assert.assertEquals(driver.findElement(By.cssSelector("h2")).getText(), "ORDER HISTORY");
    }
}
