package com.saucedemo.selenium.testng;

import com.saucelabs.saucebindings.testng.SauceBaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Failing tests for running a single browser in parallel.
 */
public class ParallelSingleBrowserTest extends SauceBaseTest {
    @Test
    public void testCase1() {
        getDriver().executeScript("sauce:job-name=Load Page (A Purposely Failing Test)");
        getDriver().executeScript("sauce:job-build=build321");
        getDriver().navigate().to("https://www.saucedemo.com");
        getDriver().findElement(By.id("Backpack")).isDisplayed();
    }
    @Test
    public void testCase2() {
        getDriver().executeScript("sauce:job-name=Load Page (A Purposely Failing Test)");
        getDriver().executeScript("sauce:job-build=build321");
        getDriver().navigate().to("https://www.saucedemo.com");
        getDriver().findElement(By.id("Backpack")).isDisplayed();
    }


}
