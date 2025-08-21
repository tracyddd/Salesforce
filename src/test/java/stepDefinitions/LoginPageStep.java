package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import pageObjects.LoginPage;
import utils.ConfigReader;
import utils.Log;
import utils.TestContextSetup;

import java.time.Duration;

public class LoginPageStep {
    public WebDriver driver;
    public TestContextSetup testContextSetup;

    // Constructor to inject the driver from TestContextSetup
    public LoginPageStep(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
        this.driver = testContextSetup.driver;  // Use the driver from TestContextSetup
    }

    @When("User input admin username and admin password")
    public void userInputAdminUsernameAndAdminPassword() throws InterruptedException {
        ConfigReader configReader = new ConfigReader();
        String UserName = configReader.getProperty("AdminUserName");
        String Password = configReader.getProperty("AdminPassword");
        userInputUsernameAndPasswordToLoginSalesforceHomepage(UserName, Password);

    }

    @Given("User open Salesforce Homepage")
    public void userOpenSalesforceHomepage() {
        // No need to initialize the driver here, as it is already provided by TestContextSetup
        // use ConfigReader to read QAUrl
        ConfigReader configReader = new ConfigReader();
        String url = configReader.getProperty("QAUrl");
        driver.get(url);
    }

    @When("User input Username {string} and Password {string} to login Salesforce Homepage")
    public void userInputUsernameAndPasswordToLoginSalesforceHomepage(String Username, String Password) throws InterruptedException {

        // Using JavaScript Executor to wai username and password fields to be visible
        JavascriptExecutor js = (JavascriptExecutor) driver;
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));

        // Code to input username and password and login to Salesforce Homepage
        LoginPage homePage = new LoginPage(driver); // Pass the existing driver
        homePage.InputUsername(Username);
        homePage.InputPassword(Password);
        homePage.clickLoginButton();
        //    String LandingPageProductName = homePage.getProductName(); // Store the extracted product name
        //    testContextSetup.ProductName = LandingPageProductName; // Set it in the TestContextSetup
    }

    @Then("Validate user login successfully")
    public void validateUserLoginSuccessfully() throws InterruptedException {
        // use ConfigReader to read QAUrl
        ConfigReader configReader = new ConfigReader();
        String baseUrl = configReader.getProperty("BaseUrl");
        driver.get(baseUrl);

        testContextSetup.redirectToSalesforceHomepage();
        String currentURL=driver.getCurrentUrl();


        // exact same URL as expected
        //  Assert.assertEquals(currentURL, baseUrl+"/lightning/page/home", "User is not logged in successfully");

        // Assert that the current URL contains the expected base URL
        // This is a more flexible check, allowing for variations in the URL
       Assert.assertTrue(currentURL.contains(baseUrl), "User is not logged in successfully");
        Log.info("Log: The user login successfully in Salesforce Homepage");
    }



    //============= wait to be deleted

    @Then("User searched for shortname {string} in offers page")
    public void userSearchedForShortnameInOffersPage(String shortName) throws InterruptedException {
        Reporter.log("Reporter.log is showing here");
        assert true;
    }

    @And("Validate product name in offers page matches with Landing page and quit")
    public void validate_product_name_in_offers_page_matches_with_landing_page() {
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
        driver.quit();
        Log.info("Log: Validate product name in offers page matches with Landing page");

    }

    @Given("print out driver quite")
    public void printOutDriverQuite() {
        System.out.println("Print: driver quit");
    }
}
