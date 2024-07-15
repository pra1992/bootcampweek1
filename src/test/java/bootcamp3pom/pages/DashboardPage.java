package bootcamp3pom.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage extends BaseClass {
	
      public DashboardPage() {
    	  PageFactory.initElements(getDriver(), this);
      }
	
      @FindBy(xpath = "//*[@role='navigation']//*[@role='list']//one-app-nav-bar-item-root//a")
	List<WebElement> tabOptions;

	public SellerHomePage clickAccounts() {
		u.checkWindowHandles(OpenedWindows);
		for (int i = 0; i <= (tabOptions.size()) - 1; i++) {
			String TabName = tabOptions.get(i).getAttribute("title").trim();
			if (TabName.equals("Accounts")) {
				u.clickElementUsingJavascript(tabOptions.get(i), "Accounts");
				break;
			}
			
	}
		return new SellerHomePage();
		
}

}
