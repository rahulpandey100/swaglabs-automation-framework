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
	
	@Test
	public void loginWithEmptyFields() {
		
		LoginPage lp = new LoginPage(driver);
		lp.login("", "");
		String userNameRequired = lp.getErrorMessage();
		Assert.assertEquals(userNameRequired, "Epic sadface: Username is required");
		
	}
	
	@Test
	public void loginWithLockedOutUser() {
		LoginPage lp = new LoginPage(driver);
		String userName = JsonReader.getData("lockedUser", "username");
		String password = JsonReader.getData("lockedUser", "password");
		
		lp.login(userName, password);
		
		String userLockedOut = lp.getErrorMessage();
		Assert.assertEquals(userLockedOut,"Epic sadface: Sorry, this user has been locked out.");
	}
	
	@Test
	public void verifyLoginWithSQLInjection() {
		LoginPage lp = new LoginPage(driver);
		String userName = JsonReader.getData("login_with_sqlInjection", "username");
		String password = JsonReader.getData("login_with_sqlInjection", "password");
		
		lp.login(userName, password);
		
		String userLockedOut = lp.getErrorMessage();
		Assert.assertEquals(userLockedOut,"Epic sadface: Username and password do not match any user in this service", "Application is vulnerable to SQL Injection!"); 
	}

}
