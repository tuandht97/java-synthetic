package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    public final WebDriverWait wait;
    protected ChromeDriver chromeDriver;

    public BasePage(ChromeDriver driver) {
        this.chromeDriver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
}