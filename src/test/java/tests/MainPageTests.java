package tests;
import datafortest.Urls;
import org.junit.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.After;
import org.junit.Test;
import pageobject.HomePage;
import io.qameta.allure.junit4.*;

public class MainPageTests {
    private WebDriver driver;

    @Before
    public void initialize(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(Urls.URL);
    }

    @Test
    @DisplayName("Click on Bun's section header on the main page and check for the header in the menu")
    public void clickOnBunSectionTest() {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnHomePageLocator(homePage.saucesSection);
        homePage.clickOnHomePageLocator(homePage.bunsSection);
        homePage.checkSection(homePage.bunsHeaderSection);
    }

    @Test
    @DisplayName("Click on Sauce's section header on the main page and check for the header in the menu")
    public void clickOnSauceSectionTest() {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnHomePageLocator(homePage.saucesSection);
        homePage.checkSection(homePage.saucesHeaderSection);
    }

    @Test
    @DisplayName("Click on Filler's section header on the main page and check for the header in the menu")
    public void clickOnFillersSectionTest() {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnHomePageLocator(homePage.fillersSection);
        homePage.checkSection(homePage.fillersHeaderSection);
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
