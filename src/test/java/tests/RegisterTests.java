package tests;
import datafortest.Register;
import datafortest.Urls;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.After;
import org.junit.Test;
import pageobject.HomePage;
import pageobject.LoginPage;
import pageobject.RegisterPage;

import java.util.UUID;

public class RegisterTests {
    private WebDriver driver;
    private String email = UUID.randomUUID() + "@yandex.ru";


    @Before
    public void initialize(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(Urls.URL);
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        homePage.clickOnHomePageLocator(homePage.loginButton);
        loginPage.clickOnLoginPageLocator(loginPage.registerButton);
    }

    @Test
    @DisplayName("Registration with valid credentials - check, if the url will change")
    public void validRegistrationTest() {
        RegisterPage registerPage = new RegisterPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        registerPage.sendKeysToRegisterForm(Register.NAME,  email, Register.PASSWORD);
        registerPage.clickOnRegisterPageLocator(registerPage.registerButton);
        loginPage.checkForUrl(Urls.URL + Urls.LOGIN);
    }

    @Test
    @DisplayName("Registration with invalid password - less than 6 symbols")
    public void invalidPasswordRegistrationTest() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.sendKeysToRegisterForm(Register.NAME,  email, Register.INVALID_PASSWORD);
        registerPage.clickOnRegisterPageLocator(registerPage.registerButton);
        registerPage.checkMessageForInvalidPassword();
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
