package pageobject;
import datafortest.Urls;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import static org.junit.Assert.assertEquals;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public By registerButton = By.xpath(".//a[@href=\"/register\"]");
    public By emailField = By.xpath(".//input[@name = \"name\"]");
    public By passwordField = By.xpath(".//input[@name = \"Пароль\"]");
    public By loginButton = By.xpath(".//form/button[text()=\"Войти\"]");
    public By forgotPasswordButton = By.xpath(".//a[text()='Восстановить пароль']");

    @Step("Click to {locator} on login page")
    public void clickOnLoginPageLocator(By locator){
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).click();
    }

    @Step("Check for {url}")
    public  void checkForUrl(String url){
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.urlToBe(url));
        String currentUrl = driver.getCurrentUrl();
        assertEquals(String.format("Адрес страницы должен быть %s", url), url, currentUrl);
    }

    @Step("Fill the fields for login")
    public void sendKeysToLoginForm(String email, String password){
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
    }
}
