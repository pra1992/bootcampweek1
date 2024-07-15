package bootcamp3pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseClass {
	
	public LoginPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
   @FindBy(xpath = "//*[@id='username']")
   WebElement txtUserName;
   
	@FindBy(xpath = "//*[@id='password']")
	WebElement txtPassword;

	@FindBy(xpath = "//*[@id='Login']")
	WebElement btnLogin;
	
	
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
		return new HomePage();
	}
	
   
	



}
	

