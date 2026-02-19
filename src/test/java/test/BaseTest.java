package test;

import config.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.Properties;

public class BaseTest {

    protected WebDriver driver;
    protected Properties prop;

    protected static final Logger log =
            LogManager.getLogger(BaseTest.class);

    @BeforeMethod
    public void setup() {
    	
    	
    	ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new"); // Selenium 4 recommended
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-gpu");
    	
        prop = ConfigReader.initProperties();

        String browser = prop.getProperty("browser").toLowerCase();

        if (browser.equalsIgnoreCase("chrome")) {

            driver = new ChromeDriver(options);
            log.info("Launching Chrome browser");

        } else if (browser.equalsIgnoreCase("firefox")) {

            driver = new FirefoxDriver();
            log.info("Launching Firefox browser");

        } else if (browser.equalsIgnoreCase("edge")) {

            driver = new EdgeDriver();
            log.info("Launching Edge browser");

        } else {
            throw new RuntimeException(
                    "Invalid browser value in config.properties: " + browser
            );
        }

        driver.manage().window().maximize();
        driver.get(prop.getProperty("url"));

        log.info("Navigated to URL: {}", prop.getProperty("url"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        if (driver != null) {
            driver.quit();
            log.info("Browser closed");
        }
    }
}
