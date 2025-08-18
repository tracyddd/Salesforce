package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    public WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    By InputUserName =By.xpath("//input[@id='username']");
    By InputPassword=By.xpath("//input[@id='password']");
    By clickLoginButton = By.xpath("//input[@id='Login']");

    private static By waitingAccountbutton = By.xpath("//*[@title='Sample Flow Report: Screen Flows']");

    public static By WaitElement_AccountButton() {
        return waitingAccountbutton;
    }


    public void InputUsername(String username){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(InputUserName))
                .sendKeys(username);
    }

    public void InputPassword(String password) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(InputPassword))
                .sendKeys(password);
    }


    public void clickLoginButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(clickLoginButton))
                .click();
    }


}
