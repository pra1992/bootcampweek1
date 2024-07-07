package bootcamp3pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BaseClass {
	

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
   WebElement txtUserName = driver.findElement(By.xpath("//*[@id='username']"));

	WebElement txtPassword = driver.findElement(By.xpath("//*[@id='password']"));

	WebElement btnLogin = driver.findElement(By.xpath("//*[@id='Login']"));
	
	
	// Entering the UserName, Password and click on Login Button
	public LoginPage enterUserName() {
		u.enterValueInTextbox(txtUserName, property.getProperty("username"));
		return this;
	}
	
	public LoginPage enterPassword() {
		u.enterValueInTextbox(txtPassword, property.getProperty("password"));
		return this;
	}
	
	public HomePage clickLogin() {
		u.verifyandclickOnElement(btnLogin, "Login Button");
		return new HomePage(driver);
	}
	
   
	



}
	

