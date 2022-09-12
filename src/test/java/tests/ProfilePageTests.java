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
import pageobject.HomePage;
import pageobject.LoginPage;
import pageobject.ProfilePage;
import pageobject.RegisterPage;

import java.util.UUID;

public class ProfilePageTests {
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

        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);

        homePage.clickOnHomePageLocator(homePage.loginButton);
        loginPage.checkForUrl(Urls.URL + Urls.LOGIN);
        loginPage.sendKeysToLoginForm(email, Register.PASSWORD);
        loginPage.clickOnLoginPageLocator(loginPage.loginButton);
        loginPage.checkForUrl(Urls.URL);
    }

    @Test
    @DisplayName("Click on link Личный кабинет changes url to /account/profile")
    public void clickOnProfileLinkTest(){
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        homePage.clickOnHomePageLocator(homePage.profileButton);
        loginPage.checkForUrl(Urls.URL + Urls.PROFILE);
        profilePage.clickOnProfilePageLocator(profilePage.constructorLink);
        loginPage.checkForUrl(Urls.URL);
    }

    @Test
    @DisplayName("Click on link Конструктор from profile page changes url to home page")
    public void clickOnConstructorLinkTest(){
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        homePage.clickOnHomePageLocator(homePage.profileButton);
        loginPage.checkForUrl(Urls.URL + Urls.PROFILE);
        profilePage.clickOnProfilePageLocator(profilePage.constructorLink);
        loginPage.checkForUrl(Urls.URL);
    }

    @Test
    @DisplayName("Click on logo from profile page changes url to home page")
    public void clickOnLogoLinkTest(){
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        homePage.clickOnHomePageLocator(homePage.profileButton);
        loginPage.checkForUrl(Urls.URL + Urls.PROFILE);
        profilePage.clickOnProfilePageLocator(profilePage.logoLink);
        loginPage.checkForUrl(Urls.URL);
    }

    @Test
    @DisplayName("Click on logout button change url to /login")
    public void clickOnLogoutButtonTest(){
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        homePage.clickOnHomePageLocator(homePage.profileButton);
        loginPage.checkForUrl(Urls.URL + Urls.PROFILE);
        profilePage.clickOnProfilePageLocator(profilePage.logoutButton);
        loginPage.checkForUrl(Urls.URL + Urls.LOGIN);
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
