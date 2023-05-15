package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.nio.charset.Charset;

public class LoginPage extends BasePage {

    public LoginPage(ChromeDriver driver) {
        super(driver);
    }

    public void open() {
        chromeDriver.get("http://sidoc-dev.demo2.siten.vn:43803/auth/login");
    }

    public boolean isLoaded() throws InterruptedException {
        Thread.sleep(1000);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).isDisplayed();
    }

    public void login(String user, String password) throws InterruptedException {
        chromeDriver.findElement(By.id("username")).clear();
        chromeDriver.findElement(By.id("username")).sendKeys(user);
        Thread.sleep(1000);
        chromeDriver.findElement(By.id("password-field")).sendKeys(password);
        Thread.sleep(1000);
        chromeDriver.findElement(By.xpath("//*[@id=\"root\"]/app-manage/app-auth-main/div/div[2]/div/div[2]/div/div/app-login/form/div[4]/button")).click();
        Thread.sleep(1000);
    }

    public String getErrorMessage() {
        WebElement errorPage = chromeDriver.findElement(By.xpath("//*[@id=\"cdk-overlay-0\"]/nz-notification-container/div[2]/nz-notification/div/div/div/div/div[1]"));
        return errorPage.getText();
    }

    public String getRequiredMessage(String xpath) {
        WebElement errorPage = chromeDriver.findElement(By.xpath(xpath));
        return errorPage.getText();
    }
}