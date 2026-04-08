package base;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.LoginPage;
import utils.JsonReader;

public class LoginTest extends BaseClass {
	
	@Test
	public void verifyLogin() {
		
		LoginPage lp = new LoginPage(driver);
		String userName = JsonReader.getData("validUser","username");
		String password = JsonReader.getData("validUser", "password");
		lp.login(userName, password);	
		
		String currentUrl = driver.getCurrentUrl();
	    Assert.assertTrue(currentUrl.contains("inventory"), "Login Failed!");
	}
	
	@Test
	public void verifyInvalidLogin() {
		
		LoginPage lp = new LoginPage(driver);
		String userName = JsonReader.getData("invalidUser","username");
		String password = JsonReader.getData("invalidUser", "password");
		lp.login(userName, password);	
		
		String invalidCredentials = lp.getErrorMessage();
		Assert.assertEquals(invalidCredentials, "Epic sadface: Username and password do not match any user in this service", "Error message mismatch!");
	   
	}

}
