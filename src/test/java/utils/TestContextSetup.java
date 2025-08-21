package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.LoginPage;

import java.time.Duration;
import java.util.Arrays;

public class TestContextSetup {
    public static WebDriver driver;
    public TestContextSetup() {
        if (driver != null) { return; }

        ConfigReader configReader = new ConfigReader();
        String browser = configReader.getProperty("browser");  // 从 global.properties 获取浏览器类型

        switch (browser.toLowerCase()) {
            case "chrome":
                // Accept Https Certificates
                ChromeOptions chromeOptions = new ChromeOptions();
                //Block pop=up windows
                chromeOptions.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
                System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\ChromeDriver\\chromedriver.exe");
                //ChromeOptions object
                ChromeOptions op = new ChromeOptions();
                op.setPageLoadStrategy(PageLoadStrategy.EAGER);  // "EAGER" allows the browser to return after DOMContentLoaded, without having to wait until all resources (such as images/long links) are downloaded.

                //disable notification parameter
                op.addArguments("--disable-notifications");  // disable notification pop up
                op.addArguments("--remote-allow-origins=*");  // upgrade from Chrome 110 to 111 got error, this is for error fixing.

                // configure options parameter to Chrome driver
                driver = new ChromeDriver(op);

                /* Here you can setup Proxy for the browser
                //Proxy proxy=new Proxy();
                //proxy.setHttpProxy("username","password");
                //options.setCapability("proxy", proxy);  */
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
                System.setProperty("webdriver.gecko.driver", "C:\\Drivers\\GeckoDriver\\geckodriver.exe");
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
                System.setProperty("webdriver.edge.driver", "C:\\Drivers\\EdgeDriver\\msedgedriver.exe");
                driver = new EdgeDriver(edgeOptions);
                break;

            default:
                throw new RuntimeException("Unsupported browser: " + browser);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

    }
        public static void redirectToSalesforceHomepage() {
            String baseUrl = driver.getCurrentUrl();
            driver.get(baseUrl + "/lightning/page/home");
        }

    /* // considering to add setUp() method for user login
    public void userLoginSalesforceHomepage() {
        // No need to initialize the driver here, as it is already provided by TestContextSetup
        // use ConfigReader to read QAUrl
        ConfigReader configReader = new ConfigReader();
        String url = configReader.getProperty("QAUrl");
        driver.get(url);

        String Username = configReader.getProperty("AdminUserName");
        String Password = configReader.getProperty("AdminPassword");

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
    }
     */


}
