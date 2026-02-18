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
 * Tests that the footer displays correctly with copyright information.
 */
public class HomePageFooterTest extends BaseTest {

    @Test
    public void footerDisplaysCopyrightText() {
        driver.get(baseUrl + "/");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("footer")));
        
        // Find footer element
        WebElement footer = driver.findElement(By.tagName("footer"));
        Assert.assertTrue(footer.isDisplayed(), "Footer should be visible");
        
        // Verify footer contains copyright text
        WebElement footerText = footer.findElement(By.tagName("p"));
        Assert.assertTrue(footerText.getText().contains("EYE BEE M PETS"),
                "Footer should contain 'EYE BEE M PETS'");
        Assert.assertTrue(footerText.getText().contains("2026"),
                "Footer should contain copyright year");
        Assert.assertTrue(footerText.getText().contains("ALL RIGHTS RESERVED"),
                "Footer should contain 'ALL RIGHTS RESERVED'");
    }
}
