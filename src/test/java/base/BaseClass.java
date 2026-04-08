package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import utils.ConfigReader;
import utils.DriverFactory;

public class BaseClass {
	
	public WebDriver driver;
	ConfigReader config;
	
	@BeforeMethod
	@Parameters("browser")
	public void setUp(String browser) {
		config = new ConfigReader();
		
		driver = DriverFactory.initDriver(config.getBrowser());
		driver.get(config.getUrl());
		
	}
	
	@AfterMethod
	public void tearDown() {
		if(driver!=null)
		driver.quit();
	}
	
	
	
	
	

}
