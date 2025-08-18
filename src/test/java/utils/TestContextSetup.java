package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.PageLoadStrategy;
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

                /* setup Proxy
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

}
