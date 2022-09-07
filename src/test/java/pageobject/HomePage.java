package pageobject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

import io.qameta.allure.Step;

public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public By profileButton = By.xpath(".//nav/a/p[text()='Личный Кабинет']");
    public By loginButton = By.xpath(".//div/button[text()='Войти в аккаунт']");
    public By bunsSection = By.xpath(".//section[1]/div[1]/div[1]/span");
    public By saucesSection = By.xpath(".//section[1]/div[1]/div[2]/span");
    public By fillersSection = By.xpath(".//section[1]/div[1]/div[3]/span");
    public By bunsHeaderSection = By.xpath(".//section[1]/div[2]/h2[1]");
    public By saucesHeaderSection = By.xpath(".//section[1]/div[2]/h2[2]");
    public By fillersHeaderSection = By.xpath(".//section[1]/div[2]/h2[3]");



    @Step("Click to {locator}")
    public void clickOnHomePageLocator(By locator){
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).click();
    }

    @Step("Check visibility of the section's header for {header}")
    public void checkSection(By header){
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(header));
        assertTrue("Ожидается появление заголовка для секции с ингредиентами", driver.findElement(header).isDisplayed());
    }


}
