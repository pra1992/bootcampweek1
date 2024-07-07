package bootcamp3pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BaseClass {

	
	public HomePage(WebDriver driver) {
		this.driver= driver;
	}
   
	WebElement btnToggle = driver.findElement(By.xpath("//*[@title='App Launcher']"));
	
	WebElement btnViewAll = driver.findElement(By.xpath("//*[@kx-type='underline']"));
	
	WebElement btnServiceConsole = driver.findElement(By.xpath(
			"//*[@data-name='Service Console']//*[contains(text(),'(Lightning Experience) Lets support agents work with multiple records across customer service channels on one screen')]"));
	
	WebElement btnSales = driver.findElement(By.xpath(
			"//*[@data-name='Sales']//*[contains(text(),'Manage your sales process with accounts, leads, opportunities, and more')]"));
	
	public HomePage clickToggle() {
		w.waitforVisibilityofElement(btnToggle, "Toggle Button");
		u.moveToElement(btnToggle, "Toggle Button");
		u.clickElementUsingJavascript(btnToggle, "Toggle Button");
		return this;
	}
		
	
	public HomePage verifyandclickViewAll() {
		u.clickElementUsingJavascript(btnViewAll, "View All Button");
		return this;
	}
	
	//4. Click Option from App Launcher
	public DashboardPage AppLaucherOption(String AppLauncherOption) {
		u.checkWindowHandles(OpenedWindows);
		u.clickElementUsingActions(btnServiceConsole, "Servic eConsole Button");
		switch (AppLauncherOption.toLowerCase()) {	
		case  "sales":
			u.clickElementUsingActions(btnSales, "Sales Option");
		   break;
		case "service console":
			u.clickElementUsingActions(btnServiceConsole, "Service Console Option");
			break;
		}
		return new DashboardPage(driver);
		
	}


	 //Verify user logged in successfully
	
		public HomePage verifyLoginIsSuccess() {
			w.waitforTitle("Home | Salesforce");
			u.verifyTitleOfPage(true, "Home | Salesforce");
			return this;
		}
	
}
