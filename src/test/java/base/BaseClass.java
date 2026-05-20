package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utils.ConfigReader;
import utils.DriverFactory;

public class BaseClass {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();  //  declare

    ConfigReader config;

    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
        config = new ConfigReader();
        driver.set(DriverFactory.initDriver(browser));  //  .set()
        getDriver().get(config.getUrl());               //  getDriver()
    }

    public static WebDriver getDriver() {               //  unwraps ThreadLocal
        return driver.get();
    }

    @AfterMethod
    public void tearDown() {
        if (getDriver() != null) {                      //  getDriver()
            getDriver().quit();
            driver.remove();                            //  prevents memory leak
        }
    }
}