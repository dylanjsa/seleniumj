package com.dylanjsa.seleniumj.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by Dylan Janeke on 2017/02/18.
 */
public class SLocalConfig extends SConfig {
    private BrowserType browserType;
    private boolean useBuiltInChromeDriver = true;

    public BrowserType getBrowserType() {
        return browserType;
    }

    public SLocalConfig setBrowserType(BrowserType browserType) {
        this.browserType = browserType;
        return this;
    }

    @Override
    public WebDriver createDriver() {
        if (BrowserType.CHROME.equals(browserType)) {
            return new ChromeDriver();
        }
        throw new RuntimeException(String.format ("Browser of type: %s not found, please call setBrowserType() with a supported driver!", browserType));
    }

    public SLocalConfig setChromeDriverLocation(String path) {
        System.setProperty("webdriver.chrome.driver", path);
        return this;
    }

    public SLocalConfig setUseBuiltInChromeDriver(boolean useBuiltInChromeDriver) {
        this.useBuiltInChromeDriver = useBuiltInChromeDriver;
        //TODO:Would be nice to download/save to temp file automatically!
        setChromeDriverLocation(String.format("src/main/resources/%s", getChromeDriverFilenameForOs()));
        return this;
    }

    private String getChromeDriverFilenameForOs () {
        String osName = System.getProperty("os.name");
        String osNameMatch = osName.toLowerCase();
        if(osNameMatch.contains("linux")) {
            return "chromedriver-lin";
        }else if(osNameMatch.contains("windows")) {
            return "chromedriver-win.exe";
        }else if(osNameMatch.contains("mac os") || osNameMatch.contains("macos") || osNameMatch.contains("darwin")) {
            // the only one i tested...
            return "chromedriver";
        }
        return null;
    }
}