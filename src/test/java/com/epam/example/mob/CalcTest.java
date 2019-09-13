package com.epam.example.mob;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import static java.util.concurrent.TimeUnit.SECONDS;

public class CalcTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appPackage", "com.android.calculator2");
        capabilities.setCapability("appActivity", "com.android.calculator2.Calculator");
        capabilities.setCapability("version", "7.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Nexus 5X API 24");
        capabilities.setCapability("newCommandTimeout", "500");

        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(20, SECONDS);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private static final By BUTTON_1 = By.id("digit_1");
    private static final By BUTTON_3 = By.id("digit_3");
    private static final By BUTTON_PLUS = By.id("op_add");
    private static final By BUTTON_EQ = By.id("eq");
    private static final By INPUT_FIELD = By.id("result");

    @Test
    public void testSum() throws InterruptedException {
        driver.findElement(BUTTON_1).click();
        driver.findElement(BUTTON_PLUS).click();
        driver.findElement(BUTTON_3).click();
        driver.findElement(BUTTON_EQ).click();

        String result = driver.findElement(INPUT_FIELD).getText();

        Assert.assertEquals("4", result);

        Thread.sleep(10000);
    }
}
