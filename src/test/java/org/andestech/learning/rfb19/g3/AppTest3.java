package org.andestech.learning.rfb19.g3;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class AppTest3
{
    private WebDriver wd = null;
    private InternetExplorerOptions options = null;

    @BeforeClass
    public void initData(){

    System.setProperty("webdriver.ie.driver",
            "E:\\drivers\\selenium\\IEDriverServer.exe");
        System.out.println("+++ Class: " + this);

     options = new InternetExplorerOptions();
     options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
     options.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING,true);

    }

    @Test
    public void testCaseIE01()
    {

        wd = new InternetExplorerDriver(options);
        wd.get("http://andestech.org/learning/rfb18/");

        wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        WebElement el = wd.findElement(By.tagName("footer"));
        System.out.println(el.getCssValue("background-color"));

        el = wd.findElement(By.linkText("Home"));
        System.out.println("bc: " + el.getCssValue("background-color"));
        System.out.println("c: " + el.getCssValue("color"));
        System.out.println("fs: " + el.getCssValue("font-size"));

        assertTrue( true );
    }


    @AfterClass
    public void tearDown()
    {
        if(wd != null) wd.quit();
        System.out.println("--- Class: " + this);
    }

}
