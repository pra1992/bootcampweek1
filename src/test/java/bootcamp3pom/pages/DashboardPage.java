package bootcamp3pom.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardPage extends BaseClass {
	
	public DashboardPage(WebDriver driver) {
		this.driver = driver;
	}
	
	
	List<WebElement> tabOptions = driver
			.findElements(By.xpath("//*[@role='navigation']//*[@role='list']//one-app-nav-bar-item-root//a"));

	
	
	
	public SellerHomePage clickAccounts() {
		u.checkWindowHandles(OpenedWindows);
		for (int i = 0; i <= (tabOptions.size()) - 1; i++) {
			String TabName = tabOptions.get(i).getAttribute("title").trim();
			if (TabName.equals("Accounts")) {
				u.clickElementUsingJavascript(tabOptions.get(i), "Accounts");
				break;
			}
			
	}
		return new SellerHomePage(driver);
		
}

}
