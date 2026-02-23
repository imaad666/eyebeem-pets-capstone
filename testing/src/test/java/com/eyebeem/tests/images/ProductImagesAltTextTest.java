package com.eyebeem.tests.images;

import com.eyebeem.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/** Tests that product images have correct alt text for accessibility. */
public class ProductImagesAltTextTest extends BaseTest {

    @Test
    public void productImagesHaveAltText() {
        driver.get(baseUrl + "/products");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("product-search")));

        List<WebElement> productImages = driver.findElements(By.cssSelector(".retro-container img"));
        Assert.assertTrue(productImages.size() >= 3, "Should have at least 3 product images");

        Set<String> expectedNames = Set.of("e-CAT", "e-DOG", "e-HAMSTER");
        Set<String> altTexts = productImages.stream()
                .map(img -> img.getAttribute("alt"))
                .filter(alt -> alt != null && !alt.isEmpty())
                .collect(Collectors.toSet());

        Assert.assertTrue(altTexts.size() >= 3, "All product images should have non-empty alt text");
        for (String expected : expectedNames) {
            Assert.assertTrue(altTexts.contains(expected),
                    "Expected alt text for " + expected + ", found: " + altTexts);
        }
    }
}
