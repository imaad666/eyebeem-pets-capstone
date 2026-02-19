package com.eyebeem.tests.home;

import com.eyebeem.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

/** Tests that logo/home link navigates to home page. */
public class HomePageLogoLinkTest extends BaseTest {

    @Test
    public void logoLinkNavigatesToHome() {
        driver.get(baseUrl + "/products");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("nav")));

        WebElement homeLink = driver.findElement(By.cssSelector("nav a[href='/']"));
        Assert.assertTrue(homeLink.isDisplayed(), "Home link should be visible");
        homeLink.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("hero-heading")));
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/") || driver.getCurrentUrl().endsWith("/#"),
                "Clicking logo should navigate to home");
    }
}
