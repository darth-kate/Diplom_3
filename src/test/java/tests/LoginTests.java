package tests;
import datafortest.Register;
import datafortest.Urls;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.After;
import org.junit.Test;
import pageobject.ForgotPasswordPage;
import pageobject.HomePage;
import pageobject.LoginPage;
import pageobject.RegisterPage;

import java.util.UUID;

public class LoginTests {
    private static WebDriver driver;
    private static String email;

    @BeforeClass
    public static void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(Urls.URL);

        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        homePage.clickOnHomePageLocator(homePage.profileButton);
        loginPage.clickOnLoginPageLocator(loginPage.registerButton);
        email = UUID.randomUUID() + "@yandex.ru";
        registerPage.sendKeysToRegisterForm(Register.NAME,  email, Register.PASSWORD);
        registerPage.clickOnRegisterPageLocator(registerPage.registerButton);
        loginPage.checkForUrl(Urls.URL + Urls.LOGIN);
        driver.quit();
    }

    @Before
    public void initialize(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(Urls.URL);
    }

    @Test
    @DisplayName("Login with button Войти в аккаунт")
    public void loginWithLoginButtonTest() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);

        homePage.clickOnHomePageLocator(homePage.loginButton);
        loginPage.checkForUrl(Urls.URL + Urls.LOGIN);
        loginPage.sendKeysToLoginForm(email, Register.PASSWORD);
        loginPage.clickOnLoginPageLocator(loginPage.loginButton);
        loginPage.checkForUrl(Urls.URL);
    }

    @Test
    @DisplayName("Login with button Личный Кабинет")
    public void loginWithProfileButtonTest() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);

        homePage.clickOnHomePageLocator(homePage.profileButton);
        loginPage.checkForUrl(Urls.URL + Urls.LOGIN);
        loginPage.sendKeysToLoginForm(email, Register.PASSWORD);
        loginPage.clickOnLoginPageLocator(loginPage.loginButton);
        loginPage.checkForUrl(Urls.URL);
    }

    @Test
    @DisplayName("Login with button Войти from register page")
    public void loginFromRegisterPageTest() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        homePage.clickOnHomePageLocator(homePage.profileButton);
        loginPage.clickOnLoginPageLocator(loginPage.registerButton);
        registerPage.clickOnRegisterPageLocator(registerPage.loginButtonOnRegisterPage);
        loginPage.checkForUrl(Urls.URL + Urls.LOGIN);
        loginPage.sendKeysToLoginForm(email, Register.PASSWORD);
        loginPage.clickOnLoginPageLocator(loginPage.loginButton);
        loginPage.checkForUrl(Urls.URL);
    }

    @Test
    @DisplayName("Login with button Войти from forgot-password page")
    public void loginFromForgotPasswordPageTest() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);

        homePage.clickOnHomePageLocator(homePage.profileButton);
        loginPage.clickOnLoginPageLocator(loginPage.forgotPasswordButton);
        forgotPasswordPage.clickOnForgotPasswordLocator(forgotPasswordPage.ForgotPasswordLoginButton);
        loginPage.checkForUrl(Urls.URL + Urls.LOGIN);
        loginPage.sendKeysToLoginForm(email, Register.PASSWORD);
        loginPage.clickOnLoginPageLocator(loginPage.loginButton);
        loginPage.checkForUrl(Urls.URL);
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
