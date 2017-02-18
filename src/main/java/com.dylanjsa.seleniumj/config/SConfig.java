package com.dylanjsa.seleniumj.config;

import org.openqa.selenium.WebDriver;

import java.io.File;

/**
 * Created by Dylan Janeke on 2017/02/18.
 */
public abstract class SConfig {
    private Integer defaultWaitTimeoutInSeconds = 1;
    private Integer defaultRetries = 3;
    private File defaultScreenshotDir;

    public abstract WebDriver createDriver();

    public Integer getDefaultWaitTimeoutInSeconds() {
        return defaultWaitTimeoutInSeconds;
    }

    public SConfig setDefaultWaitTimeoutInSeconds(Integer defaultWaitTimeoutInSeconds) {
        this.defaultWaitTimeoutInSeconds = defaultWaitTimeoutInSeconds;
        return this;
    }

    public Integer getDefaultRetries() {
        return defaultRetries;
    }

    public SConfig setDefaultRetries(Integer defaultRetries) {
        this.defaultRetries = defaultRetries;
        return this;
    }

    public File getDefaultScreenshotDir() {
        return defaultScreenshotDir;
    }

    public SConfig setDefaultScreenshotDir(File defaultScreenshotDir) {
        this.defaultScreenshotDir = defaultScreenshotDir;
        return this;
    }
}