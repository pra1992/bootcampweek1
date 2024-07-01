package bootcamp3pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import bootcamp2testng.Assertion;
import bootcamp2testng.BaseClass;

public class LoginPage extends BaseClass {
	
	private WebDriver driver;
	
   public LoginPage(WebDriver driver) {
	   this.driver = driver;
   }
   
   WebElement txtUserName = driver.findElement(By.xpath("//*[@id='username']"));

	WebElement txtPassword = driver.findElement(By.xpath("//*[@id='password']"));

	WebElement btnLogin = driver.findElement(By.xpath("//*[@id='Login']"));
	
	
	// Entering the UserName, Password and click on Login Button
	public void enterUserName() {
		u.enterValueInTextbox(txtUserName, "bootcamp_2024@testleaf.com");
	}
	
	public void enterPassword() {
		u.enterValueInTextbox(txtPassword, "Bootcamp@123");
	}
	
	public void clickLogin() {
		u.verifyandclickOnElement(btnLogin, "Login Button");
	}
	
    //Verify user logged in successfully
	
	public void verifyLoginIsSuccess() {
		w.waitforTitle("Home | Salesforce");
		u.verifyTitleOfPage(true, "Home | Salesforce");
	}
	



}
	

