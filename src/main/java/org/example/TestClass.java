package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.webdriver.WebDriverBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestClass {

    ChromeDriver chromeDriver;

    @BeforeMethod
    public void Setup(){
        WebDriverManager.chromedriver().setup();
        chromeDriver = new ChromeDriver();
    }
    @Test
    public void Run(){
        System.out.println("TestButton");
        chromeDriver.get("https://auto.fresher.dev/lessons/lession7/index.html");
        WebElement button1 = chromeDriver.findElement(By.id("btnExample1"));

        Actions actions = new Actions(chromeDriver);

        actions.moveToElement(button1);
        actions.click(button1).build().perform();

        WebElement lbStatusButton = chromeDriver.findElement(By.id("lbStatusButton"));
        String value = lbStatusButton.getText();

        Assert.assertEquals(value, "Click on Button 1");

        WebElement txtInput1 = chromeDriver.findElement(By.id("txtInput1"));
        String inputValue = txtInput1.getAttribute("value");
        Assert.assertEquals(inputValue, "Default value of input");

        String newValue = "New";
        txtInput1.clear();
        txtInput1.sendKeys(newValue);

        String newInputValue = txtInput1.getAttribute("value");
        Assert.assertEquals(newInputValue, newValue);
    }

    @AfterMethod
    public void Cleanup(){
        System.out.println("Clean up");
//        chromeDriver.quit();
    }
}
