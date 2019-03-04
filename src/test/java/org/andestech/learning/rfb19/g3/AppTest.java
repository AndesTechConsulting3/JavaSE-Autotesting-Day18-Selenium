package org.andestech.learning.rfb19.g3;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class AppTest
{
    private WebDriver wd = null;

    @BeforeClass
    public void initData(){
    System.setProperty("webdriver.chrome.driver",
            "E:\\drivers\\selenium\\chromedriver.exe");
    System.out.println("+++ Class: " + this);

    }

    @Test
    public void testCaseChrome01() throws InterruptedException
    {
        wd = new ChromeDriver();
        wd.get("http://andestech.org/learning/rfb18/");

        wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        WebElement el = wd.findElement(By.tagName("footer"));
        System.out.println(el.getCssValue("background-color"));

        el = wd.findElement(By.linkText("Home"));
        System.out.println("bc: " + el.getCssValue("background-color"));
        System.out.println("c: " + el.getCssValue("color"));
        System.out.println("fs: " + el.getCssValue("font-size"));

        assertTrue( true );

        String w1 = wd.getWindowHandle();

        Set<String> windows1 = wd.getWindowHandles();

        windows1.forEach(x -> System.out.println(x));
        System.out.println("----------------------------------------");

        ((JavascriptExecutor)wd).executeScript("window.open()");

        Set<String> windows2 = wd.getWindowHandles();
        windows2.forEach(x -> System.out.println(x));

        System.out.println("----------------------------------------");
        System.out.println(wd.getWindowHandle());

        windows2.removeAll(windows1);

        List<String> windows3 =  new ArrayList<String>();
        windows3.addAll(windows2);
        String w2 = windows3.get(0);

        wd.switchTo().window(w2);
        wd.get("http://google.com");

        WebElement webElement =
        wd.findElement(By.name("q"));
        webElement.sendKeys("mars");
        webElement.submit();


        Thread.sleep(2000);

        //wd.close();
        ((JavascriptExecutor)wd).executeScript("window.close()");

        wd.switchTo().window(w1);


        System.out.println("----------------------------------------");
        System.out.println(wd.getWindowHandle());

        wd.navigate().to("http://google.com");
        webElement =
                wd.findElement(By.name("q"));
        webElement.sendKeys("venus");
        webElement.submit();

        wd.navigate().back();
        wd.navigate().back();

        Thread.sleep(3000);

       // wd.switchTo().window();


    }


    @AfterClass
    public void tearDown()
    {
      if(wd != null) wd.quit();
      System.out.println("--- Class: " + this);
    }

}
