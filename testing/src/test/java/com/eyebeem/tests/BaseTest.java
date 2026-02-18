package com.eyebeem.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * BaseTest sets up and tears down the WebDriver instance for all UI tests.
 * It assumes the frontend is running (for example via `npm run dev` or `npm run preview`).
 */
public abstract class BaseTest {

    protected WebDriver driver;
    protected String baseUrl = System.getProperty("baseUrl", "http://localhost:5173");

    @BeforeClass
    public void setUpDriver() {
        // Let WebDriverManager download and configure the appropriate ChromeDriver binary
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}

