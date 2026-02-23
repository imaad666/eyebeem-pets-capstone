package com.eyebeem.tests.images;

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

/** Tests that product images are displayed on the products page. */
@DataDependent
public class ProductImagesProductsPageTest extends BaseTest {

    @Test
    public void productsPageDisplaysProductImages() {
        driver.get(baseUrl + "/products");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("product-search")));

        List<WebElement> productImages = driver.findElements(By.cssSelector(".retro-container img"));
        Assert.assertTrue(productImages.size() >= 3,
                "Products page should display product images for each product, found: " + productImages.size());

        for (WebElement img : productImages) {
            Assert.assertTrue(img.isDisplayed(), "Product image should be visible");
        }
    }
}
