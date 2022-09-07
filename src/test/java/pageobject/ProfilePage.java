package pageobject;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    private WebDriver driver;

    public ProfilePage(WebDriver driver){
        this.driver = driver;
    }

    public By constructorLink = By.xpath(".//p[text()='Конструктор']");
    public By logoLink = By.xpath(".//div/a[@href='/']");
    public By logoutButton = By.xpath("//button[text()='Выход']");


    @Step("Click to {locator}")
    public void clickOnProfilePageLocator(By locator){
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).click();
    }
}
