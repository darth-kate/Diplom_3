package pageobject;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {

    private WebDriver driver;

    public ForgotPasswordPage(WebDriver driver){
        this.driver = driver;
    }

    public By ForgotPasswordLoginButton = By.xpath(".//a[text()='Войти']");

    @Step("Click on {locator} on forgot-password page")
    public void clickOnForgotPasswordLocator(By locator){
        driver.findElement(locator).click();
    }
}
