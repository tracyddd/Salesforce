package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pageObjects.AccountPage;
import utils.ConfigReader;
import utils.TestContextSetup;

// Constructor to inject the driver from TestContextSetup
public class AccountPageStep extends utils.Utilities {

    public WebDriver driver;
    // Constructor to inject the driver from TestContextSetup
    public TestContextSetup testContextSetup;

    // Constructor to inject the driver from TestContextSetup
    public AccountPageStep(TestContextSetup testContextSetup) {
        super(testContextSetup);
        this.testContextSetup = testContextSetup;
        this.driver = testContextSetup.driver;  // Use the driver from TestContextSetup
    }

    @Given("User click on Account button from top menu bar")
    public void userClickOnAccountButtonFromTopMenuBar() throws InterruptedException {

        Thread.sleep(5000);
        String baseUrl = new ConfigReader().getProperty("BaseUrl");
        String allaccountlink= baseUrl + "/lightning/o/Account/list?filterName=RecentlyViewedAccounts";
        System.out.println("this is the full link: "+allaccountlink);
        driver.get(allaccountlink);


        Thread.sleep(5000);

        //    new AccountPage(driver).clickAccountButton();

//        // 1) wait entire page loading is ready
//        new WebDriverWait(driver, Duration.ofSeconds(15))
//                .until(d -> ((JavascriptExecutor) d)
//                        .executeScript("return document.readyState").equals("complete"));
//
//        // 2) wait "Sample Flow Report: Screen Flows" is clickable
//        WebElement AccountButton = new WebDriverWait(driver, Duration.ofSeconds(5))
//                .until(ExpectedConditions.visibilityOfElementLocated(
//                        LoginPage.WaitElement_AccountButton()));
//        AccountButton.click();


    }

    @When("User click on New button")
    public void userClickOnNewButton() {
        new AccountPage(driver).userClickOnNewButton();
    }

    @Then("Select Client Radio and Next button")
    public void selectClientRadioAndNextButton() {
        new AccountPage(driver).selectClientRadioAndNextButtonForNewAccount();
    }

    @And("Input AccountName {string} to create a new account")
    public void inputAccountNameAccountNameToCreateANewAccount(String accountName) {
        new AccountPage(driver).inputAccountNameToCreateANewAccount(accountName);
    }


}
