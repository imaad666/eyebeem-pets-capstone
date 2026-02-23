package com.eyebeem.tests.images;

import com.eyebeem.tests.BaseTest;
import com.eyebeem.tests.DataDependent;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

/** Tests that product images load with valid dimensions. */
@DataDependent
public class ProductImagesLoadTest extends BaseTest {

    @Test
    public void productImagesLoadWithValidDimensions() {
        driver.get(baseUrl + "/products");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("product-search")));

        List<WebElement> productImages = driver.findElements(By.cssSelector(".retro-container img"));
        Assert.assertTrue(productImages.size() >= 3, "Should have at least 3 product images");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        int loadedCount = 0;
        for (WebElement img : productImages) {
            String src = img.getAttribute("src");
            Assert.assertNotNull(src, "Image should have src attribute");
            Assert.assertFalse(src.isEmpty(), "Image src should not be empty");
            Boolean complete = (Boolean) js.executeScript("return arguments[0].complete && arguments[0].naturalWidth > 0", img);
            if (Boolean.TRUE.equals(complete)) {
                loadedCount++;
            }
        }
        Assert.assertTrue(loadedCount >= 3, "All product images should load (complete with naturalWidth > 0), loaded: " + loadedCount);
    }
}
