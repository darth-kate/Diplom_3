package pageobject;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class RegisterPage {
    private WebDriver driver;

    public RegisterPage(WebDriver driver){
        this.driver = driver;
    }

    public By nameField = By.xpath(".//fieldset[1]/div/div/input");
    public By emailField = By.xpath(".//fieldset[2]/div/div/input");
    public By passwordField = By.xpath(".//fieldset[3]/div/div/input");
    public By registerButton = By.xpath(".//button[text()=\"Зарегистрироваться\"]");
    public By loginButtonOnRegisterPage = By.xpath(".//a[@href=\"/login\"]");
    public By invalidPasswordMessage = By.xpath(".//fieldset[3]/div/p");

    @Step("Fill the register form")
    public void sendKeysToRegisterForm(String name, String email, String password){
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(nameField));
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Click on {locator} button on register page")
    public void clickOnRegisterPageLocator(By locator){
        driver.findElement(locator).click();
    }

    @Step("Check the message for invalid password")
    public void checkMessageForInvalidPassword(){
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(invalidPasswordMessage));
        assertEquals("Ожидается появления сообщения о некорректном пароле", "Некорректный пароль", driver.findElement(invalidPasswordMessage).getText());
    }
}
