package selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestLogin {

    ChromeDriver chromeDriver;

    @BeforeMethod
    public void Setup(){
        WebDriverManager.chromedriver().setup();
        chromeDriver = new ChromeDriver();
    }
    @Test
    public void Run() throws InterruptedException {
        System.out.println("Test login");

        chromeDriver.get("http://sidoc-dev.demo2.siten.vn:43803/auth/login");
        WebElement username=chromeDriver.findElement(By.id("username"));
        WebElement password=chromeDriver.findElement(By.id("password-field"));
        WebElement login=chromeDriver.findElement(By.xpath("//*[@id=\"root\"]/app-manage/app-auth-main/div/div[2]/div/div[2]/div/div/app-login/form/div[4]/button"));
        username.sendKeys("0110210190_ben");
        password.sendKeys("1234");
        login.click();
        Thread.sleep(5000);
        String actualUrl="http://sidoc-dev.demo2.siten.vn:43803/enterprise/dashboard";
        String expectedUrl= chromeDriver.getCurrentUrl();
        Assert.assertEquals(expectedUrl,actualUrl);
    }

    @AfterMethod
    public void Cleanup(){
        System.out.println("Clean up");
//        chromeDriver.quit();
    }
}
