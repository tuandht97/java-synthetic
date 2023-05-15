package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage extends BasePage{

    public DashboardPage(ChromeDriver driver) {
        super(driver);
    }

    public boolean isLoaded() throws InterruptedException {
        Thread.sleep(12000);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/app-layout/div/div[2]/div[2]/div[2]/app-manage/app-home-enterprises/app-dashboard/div/div/div[1]")))
                .isDisplayed();
    }
}
