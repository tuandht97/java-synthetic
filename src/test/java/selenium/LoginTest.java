package selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest {
    private ChromeDriver chromeDriver;

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        chromeDriver = new ChromeDriver();
    }


    @Test
    public void shouldOpen() throws InterruptedException {
        LoginPage loginPage = new LoginPage(chromeDriver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
    }

    @Test//T1
    public void canNotLoginWithInvalidPassword() throws InterruptedException, UnsupportedEncodingException {
        LoginPage loginPage = new LoginPage(chromeDriver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
        loginPage.login("0110210190_ben", "wrong_password_test");
        String errorMessage = loginPage.getErrorMessage();
        String a = "Tài khoản không hợp lệ";
        byte[] bytes = a.getBytes(StandardCharsets.ISO_8859_1);
        String utf8String = new String(bytes, StandardCharsets.UTF_8);
        assertEquals(errorMessage, utf8String);
    }

    String decodeText(String input, Charset charset,
                      CodingErrorAction codingErrorAction) throws IOException {
        CharsetDecoder charsetDecoder = charset.newDecoder();
        charsetDecoder.onMalformedInput(codingErrorAction);
        return new BufferedReader(
                new InputStreamReader(
                        new ByteArrayInputStream(input.getBytes(charset)), charsetDecoder)).readLine();
    }
    @Test//T2
    public void canNotLoginWithoutUserName() throws InterruptedException {
        LoginPage loginPage = new LoginPage(chromeDriver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
        loginPage.login("", "");
        String requiredMessage = loginPage.getRequiredMessage("//*[@id=\"root\"]/app-manage/app-auth-main/div/div[2]/div/div[2]/div/div/app-login/form/div[1]/div[1]/div/small");
        assertEquals(requiredMessage, "Tên đăng nhập là bắt buộc nhập");
    }

    @Test//T3
    public void shouldLogin() throws InterruptedException {
        LoginPage loginPage = new LoginPage(chromeDriver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
        loginPage.login("0110210190_ben", "1234");
        assertTrue(new DashboardPage(chromeDriver).isLoaded());
    }

    @AfterTest
    public void teardown() {
        chromeDriver.quit();
    }
}
