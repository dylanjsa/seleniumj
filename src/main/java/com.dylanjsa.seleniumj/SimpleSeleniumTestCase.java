package com.dylanjsa.seleniumj;

import com.dylanjsa.seleniumj.config.BrowserType;
import com.dylanjsa.seleniumj.config.SLocalConfig;
import com.dylanjsa.seleniumj.rules.ErrorScreenshotRule;
import com.dylanjsa.seleniumj.rules.TryAgainOnErrorRule;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;

import java.io.File;

/**
 * Created by Dylan Janeke on 2017/02/18.
 */
public class SimpleSeleniumTestCase {
    protected static S s =
            new S(new SLocalConfig()
                    .setBrowserType(BrowserType.CHROME)
                    .setUseBuiltInChromeDriver(true)
                    .setDefaultRetries(3)
                    .setDefaultScreenshotDir(new File("./error-screenshots"))
                    .setDefaultWaitTimeoutInSeconds(6));

    @Rule public ErrorScreenshotRule screenshotRule = new ErrorScreenshotRule(s);
    @Rule public TryAgainOnErrorRule tryAgainRule = new TryAgainOnErrorRule(s);

    @BeforeClass
    public static void setup(){
        s.createNewDriver();
    }

    @AfterClass
    public static void teardown() {
        s.close();
    }
}