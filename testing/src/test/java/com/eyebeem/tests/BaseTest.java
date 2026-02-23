package com.eyebeem.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * BaseTest sets up and tears down the WebDriver instance for all UI tests.
 * It assumes the frontend is running (for example via `npm run dev` or `npm run preview`).
 * In CI (e.g. GitHub Actions), Chrome runs in headless mode.
 */
public abstract class BaseTest {

    protected WebDriver driver;
    protected String baseUrl = System.getProperty("baseUrl", "http://localhost:5173");

    @BeforeClass
    public void setUpDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        if (isCi()) {
            options.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage",
                "--disable-gpu", "--window-size=1920,1080");
        }
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(java.time.Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(5));
    }

    private static boolean isCi() {
        return "true".equalsIgnoreCase(System.getenv("CI"))
            || "true".equalsIgnoreCase(System.getProperty("ci"));
    }

    @AfterClass(alwaysRun = true)
    public void tearDownDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}

