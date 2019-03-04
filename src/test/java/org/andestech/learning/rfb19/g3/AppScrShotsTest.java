package org.andestech.learning.rfb19.g3;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
//import org.testng.reporters.Files;


import java.nio.file.Files;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


import static org.testng.Assert.assertTrue;

public class AppScrShotsTest
{
    private WebDriver wd = null;

    private static void saveScreenShot(WebDriver wd1){

        File f1 = ((TakesScreenshot)wd1).getScreenshotAs(OutputType.FILE);

        File fd = new File("E:\\datas\\screens\\screen_" +
                System.currentTimeMillis() + ".png");

        //TestNG
//        try {
//            org.testng.reporters.Files.copyFile(new FileInputStream(f1), fd);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        // java.nio.file.Files
        try {
            String file = "E:\\datas\\screens\\screen_" +
                    System.currentTimeMillis() + ".png";
            Files.copy(new FileInputStream(f1), Paths.get(file), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


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
        saveScreenShot(wd);

        wd.findElement(By.linkText("New customer")).click();

        saveScreenShot(wd);

        WebElement selector = wd.findElement(By.id("group_selector"));
        Select select = new Select(selector);

        select.selectByVisibleText("безработный");
        Thread.sleep(200);

        select.selectByValue("stu");
        Thread.sleep(200);

        select.selectByIndex(0);
        Thread.sleep(200);

        wd.findElement(By.linkText("Home")).click();

        WebElement slider = wd.findElement(By.id("price"));

        Actions actions = new Actions(wd);

        actions.moveToElement(slider,0,0).click();

//        Action act =
//        actions.sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT).
//                sendKeys(Keys.ARROW_RIGHT).
//                sendKeys(Keys.ARROW_RIGHT).build();
//
//        act.perform();

        int i;
        for (i = 0; i<20; i++)
        {
            actions.sendKeys(Keys.ARROW_RIGHT).build().perform();
            Thread.sleep(100);
        }

        for (; i>0; i--)
        {
            actions.sendKeys(Keys.ARROW_LEFT).build().perform();
            Thread.sleep(100);
        }


        Thread.sleep(3000);






    }


    @AfterClass
    public void tearDown()
    {
      if(wd != null) wd.quit();
      System.out.println("--- Class: " + this);
    }

}
