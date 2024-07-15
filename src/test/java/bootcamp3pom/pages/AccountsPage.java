package bootcamp3pom.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountsPage extends BaseClass{
	
	
	public AccountsPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	
	@FindBy( xpath = "//*[@data-aura-class='forceActionsText']//a")
	WebElement txtMessage;
	
	@FindBy(xpath = "//*[@role='navigation']//*[@role='list']//one-app-nav-bar-item-root//a")
	List<WebElement> tabOptions;
	
	public AccountsPage verifyAccountCreationSuccessMessage(String AccountName) {
		w.waitforVisibilityofElement(txtMessage , "Account Creation Success Message");
		if (txtMessage.getText().trim().contains(AccountName)) {
			Assertion.assertTrue(true, "Account is added successful");
		}
		return this;
	}
	
	
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
