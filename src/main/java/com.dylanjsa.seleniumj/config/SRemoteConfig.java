package com.dylanjsa.seleniumj.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Dylan Janeke on 2017/02/18.
 */
public class SRemoteConfig extends SConfig {
    private String hubUrl;
    private BrowserType browserType;

    public String getHubUrl() {
        return hubUrl;
    }

    public SRemoteConfig setHubUrl(String hubUrl) {
        this.hubUrl = hubUrl;
        return this;
    }

    public BrowserType getBrowserType() {
        return browserType;
    }

    public SRemoteConfig setBrowserType(BrowserType browserType) {
        this.browserType = browserType;
        return this;
    }

    @Override
    public WebDriver createDriver() {
        DesiredCapabilities capabilities;
        if (BrowserType.CHROME.equals(browserType)) {
            capabilities = DesiredCapabilities.chrome();
        } else {
            throw new RuntimeException(String.format ("Browser of type: %s not found, please call setBrowserType() with a supported driver!", browserType));
        }
        if (hubUrl == null) {
            throw new RuntimeException("A HUB Url needs to be specified, call setHubURL with the full url to your HUB.");
        }
        try {
            return new RemoteWebDriver(new URL(hubUrl), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(String.format("There is an error in the hubURL that you specified: %s", hubUrl));
        }
    }
}