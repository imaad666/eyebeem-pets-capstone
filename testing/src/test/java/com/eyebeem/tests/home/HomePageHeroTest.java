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
 * Tests that the hero section displays correctly with heading and tagline.
 */
public class HomePageHeroTest extends BaseTest {

    @Test
    public void heroSectionDisplaysHeadingAndTagline() {
        driver.get(baseUrl + "/");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        
        // Wait for hero heading by ID
        WebElement heroHeading = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("hero-heading"))
        );
        Assert.assertTrue(heroHeading.isDisplayed(), "Hero heading should be visible");
        Assert.assertEquals(heroHeading.getText(), "ADOPT YOUR ROBOT COMPANION",
                "Hero heading text should match");
        
        // Verify hero section contains tagline (find paragraph within section with aria-labelledby)
        WebElement heroSection = driver.findElement(By.cssSelector("section[aria-labelledby='hero-heading']"));
        Assert.assertTrue(heroSection.isDisplayed(), "Hero section should be visible");
        
        // Find tagline paragraph within hero section
        WebElement tagline = heroSection.findElement(By.tagName("p"));
        Assert.assertTrue(tagline.getText().contains("100% ELECTRIC"),
                "Tagline should contain '100% ELECTRIC'");
    }
}
