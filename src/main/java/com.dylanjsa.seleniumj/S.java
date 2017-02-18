package com.dylanjsa.seleniumj;

import com.dylanjsa.seleniumj.config.SConfig;
import com.google.common.base.Function;
import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Dylan Janeke on 2017/02/18.
 */
public class S {
    private SConfig config;
    private WebDriver driver;

    public S (SConfig sconfig) {
        this.config = sconfig;
    }

    public SConfig getConfig() {
        return config;
    }

    public void createNewDriver() {
        if (driver != null) {
            close();
        }
        driver = config.createDriver();
    }

    public WebDriver getDriver() {
        if (driver == null) {
            createNewDriver();
        }
        return driver;
    }

    public byte[] takeScreenshot () {
        TakesScreenshot camera = (TakesScreenshot) driver;
        return camera.getScreenshotAs(OutputType.BYTES);
    }

    public S takeScreenshot(File output) {
        byte[] sshot = takeScreenshot();
        try {
            Files.write(sshot, output);
            return this;
        } catch (IOException e) {
            throw new RuntimeException("Error saving screenshot to disk...", e);
        }
    }

    public WebElement awaitElement(By by) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(config.getDefaultWaitTimeoutInSeconds(), TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS)
                .ignoring(org.openqa.selenium.NoSuchElementException.class);
        WebElement it = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                WebElement e = (WebElement)driver.findElement(by);
                return e;
            }
        });
        return it;
    }

    public WebElement awaitElement(String cssSelector) {
        return awaitElement(By.cssSelector(cssSelector));
    }

    public List<WebElement> awaitElements(By by) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(config.getDefaultWaitTimeoutInSeconds(), TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS)
                .ignoring(org.openqa.selenium.NoSuchElementException.class);
        List<WebElement> them = wait.until(new Function<WebDriver, List<WebElement>>() {
            public List<WebElement> apply(WebDriver driver) {
                List<WebElement> e = (List<WebElement>)driver.findElements(by);
                return e;
            }
        });
        return them;
    }

    public List<WebElement> awaitElements(String cssSelector) {
        return awaitElements(By.cssSelector(cssSelector));
    }

    public S go (String url) {
        getDriver().get(url);
        return this;
    }

    public void close() {
        getDriver().close();
    }
}