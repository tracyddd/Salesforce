package pageObjects;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AccountPage {
    public  WebDriver driver;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }
    By AccountButton =By.linkText("Account");
    public void ClickAccountButton() {
        driver.findElement(AccountButton).click(); //Click on Account Button
    }

    // 1) 点击“New”按钮
    @When("User click on New button")
    public void userClickOnNewButton() {
        By newBtn = By.xpath("//button[@title='New' or @name='New' or normalize-space()='New']"
                + " | //a[@title='New' or @name='New' or normalize-space()='New']");
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(newBtn)).click();
    }

    // 2) Select Record Type = Client，then click on Next
    @Then("Select Client Radio and Next button")
    public void selectClientRadioAndNextButtonForNewAccount() {
        //     By clientRadio = By.xpath("//span[normalize-space()='Client']/preceding::input[@type='radio'][1]"
        //     + "|//label[normalize-space()='Client']/preceding-sibling::input[@type='radio']");
        //     WebElement ClientRadioElement= new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(clientRadio));
        //     ClientRadioElement.click();

        By nextBtn = By.xpath("//button[@title='Next' or @name='Next' or normalize-space()='Next']");
        WebElement NextButtonElement=new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(nextBtn));
        NextButtonElement.click();
    }

    // 3) 输入 Account Name
    @Then("Input AccountName {string} to create a new account")
    public void inputAccountNameToCreateANewAccount(String accountName) {
        By nameInput = By.xpath("//input[@name='Name' or @data-field='Name' or @aria-label='Account Name']");
        WebElement box = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(nameInput));
        box.clear();
        box.sendKeys(accountName);
    }

}
