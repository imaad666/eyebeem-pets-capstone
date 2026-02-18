package com.eyebeem.tests.home;

import com.eyebeem.tests.BaseTest;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

/**
 * Tests that the Home page title is correct and contains the app name.
 */
public class HomePageTitleTest extends BaseTest {

    @Test
    public void homePageTitleContainsAppName() {
        driver.get(baseUrl + "/");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.titleContains("EYE BEE M Pets"));
        
        String title = driver.getTitle();
        Assert.assertNotNull(title, "Page title should not be null");
        Assert.assertTrue(title.contains("EYE BEE M Pets"),
                "Expected title to contain 'EYE BEE M Pets' but was: " + title);
    }
}
