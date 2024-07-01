package bootcamp3pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import bootcamp2testng.BaseClass;

public class HomePage extends BaseClass {
	private WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver= driver;
	}
   
	WebElement btnToggle = driver.findElement(By.xpath("//*[@title='App Launcher']"));
	
	WebElement btnViewAll = driver.findElement(By.xpath("//*[@kx-type='underline']"));
	
	WebElement btnServiceConsole = driver.findElement(By.xpath(
			"//*[@data-name='Service Console']//*[contains(text(),'(Lightning Experience) Lets support agents work with multiple records across customer service channels on one screen')]"));
	
	public void clickToggle() {
		w.waitforVisibilityofElement(btnToggle, "Toggle Button");
		u.moveToElement(btnToggle, "Toggle Button");
		u.clickElementUsingJavascript(btnToggle, "Toggle Button");
	}
		
	
	public void verifyandclickViewAll() {
		u.clickElementUsingJavascript(btnViewAll, "View All Button");
	}
	
	//4. Click Service Console from App Launcher
	public void selectServiceConsole() {
		u.checkWindowHandles(OpenedWindows);
		u.clickElementUsingActions(btnServiceConsole, "Sales Button");
	}
}
