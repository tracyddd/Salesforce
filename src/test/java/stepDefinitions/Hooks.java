package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import pageObjects.LoginPage;
import utils.TestContextSetup;

import java.io.File;

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

    @Before(order = 0)
    public void initializeLogger() {
        logger.info("Log4j is initialized in Hooks");
        Log.info("This is a test log message from Hooks to confirm Log4j is working.");
    }

    @After
    public void tearDown(){
        //  driver.quit();
        Reporter.log("hooks - all : Clear the entries for all databases");
        ReporterLogger.log("hooks ReporterLogger - all : Clear the entries for all databases");
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
}

