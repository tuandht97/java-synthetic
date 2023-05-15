package selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestInputDisable {

    ChromeDriver chromeDriver;

    @BeforeMethod
    public void Setup() {
        WebDriverManager.chromedriver().setup();
        chromeDriver = new ChromeDriver();
    }

    @Test
    public void Run() {
        System.out.println("TestButton");
        chromeDriver.get("https://auto.fresher.dev/lessons/lession7/index.html");

        WebElement txtInputDisable = chromeDriver.findElement(By.id("txtInput2"));

        removeAttribute(txtInputDisable, "disabled");

        String newValue = "New";
        txtInputDisable.clear();
        txtInputDisable.sendKeys(newValue);

        setAttribute(txtInputDisable, "disabled");

    }

    public void removeAttribute(WebElement element, String attribute) {
        ((JavascriptExecutor) chromeDriver).executeScript("arguments[0].removeAttribute('" + attribute + "')", element);
    }

    public void setAttribute(WebElement element, String attribute) {
        ((JavascriptExecutor) chromeDriver).executeScript("arguments[0].setAttribute('" + attribute + "', '')", element);
    }

    @AfterMethod
    public void Cleanup() {
        System.out.println("Clean up");
//        chromeDriver.quit();
    }
}
