package stepDefinitions;

import io.cucumber.java.*;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;
import pageObjects.LoginPage;
import utils.ConfigReader;
import utils.TestContextSetup;

import java.io.File;
import java.time.Duration;

public class Hooks {
    private TestContextSetup testContextSetup;
    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger(Hooks.class.getName());
    public Hooks(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
        this.driver = testContextSetup.driver;  // 将 driver 初始化为 testContextSetup.driver which can simply use "driver" instead of "testContextSetup.driver"
    }
    public class Log {
        private static final Logger logger = LogManager.getLogger(Log.class);
        public static void info(String message) {
            logger.info(message);
        }
        public static void error(String message) {
            logger.error(message);
        }
        public static void warn(String message) {
            logger.warn(message);
        }
        public static void debug(String message) {
            logger.debug(message);
        }
    }

    public class ReporterLogger {
        private static final Logger logger = LogManager.getLogger(ReporterLogger.class);

        public static void log(String message) {
            // Log to TestNG report
            Reporter.log(message, true);  // The second parameter (true) prints the message in the console as well

            // Log to Log4j2
            logger.info(message);
        }
    }

      @Before(value = "@setup", order = 0)
      public void featureInitAndLogin() {
            // 1) if no Driver, initial WebDriver driver
            if (TestContextSetup.driver == null) {
                // Trigger TestContextSetup constructor, A browser instance will be created
                this.testContextSetup = new TestContextSetup();
            }
            this.driver = TestContextSetup.driver;

            // 2) 打开登录页并登录
            ConfigReader cfg = new ConfigReader();
            String qaUrl = cfg.getProperty("QAUrl");           // e.g. https://login.salesforce.com
            String adminUser = cfg.getProperty("AdminUserName");
            String adminPass = cfg.getProperty("AdminPassword");

            driver.get(qaUrl);

            // Wait for username and password fields to be visible
            new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
            new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));

            //Use PageObject to finish login
            LoginPage login = new LoginPage(driver);
            login.InputUsername(adminUser);
            login.InputPassword(adminPass);
            login.clickLoginButton();

            // Optional：Wait to login HomePage to ensure page finished loading -- error, so disable it
         //   new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(LoginPage.WaitElement_AccountButton()));
        }

        @After(value = "@teardown", order = 0)
        public void featureTeardown() {
            try {
                if (driver != null) {
                    driver.quit();
                }
            } finally {
                // 避免“旧引用”被后续场景误用
                TestContextSetup.driver = null;
                this.driver = null;
            }

        logger.info("Log4j is initialized in Hooks");
        Log.info("This is a test log message from Hooks to confirm Log4j is working.");

    }

    @AfterStep
    public void AddScreenshot (Scenario scenario) throws Exception {
        //  tackTakeScreenshot when step is failed
        if (scenario.isFailed()) {
            //catch failed screenshot
            File sourcePath=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            byte[] fileContent=FileUtils.readFileToByteArray(sourcePath);
            scenario.attach(fileContent,"image/jpg", "image");
        }
    }
    @After
    public void tearDown(io.cucumber.java.Scenario scenario){
        if (driver != null) {
            try {
                driver.manage().deleteAllCookies(); // 可选
            } catch (Exception ignored) {}
        }
        //  driver.quit();
        Reporter.log("hooks - all : Clear the entries for all databases");
        ReporterLogger.log("hooks ReporterLogger - all : Clear the entries for all databases");
    }

    @AfterAll
    public static void globalTeardown() {
        if (TestContextSetup.driver != null) {
            try {
                TestContextSetup.driver.quit();
            } finally {
                TestContextSetup.driver = null;
            }
        }
    }
}

