package com.dylanjsa.seleniumj;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by Dylan Janeke on 2017/02/18.
 */
public class BasicTest extends SimpleSeleniumTestCase {
    @Test
    public void testTitle() {
        s.go("http://www.google.com");
        WebElement element = s.awaitElement(By.cssSelector("input"));
    }
}