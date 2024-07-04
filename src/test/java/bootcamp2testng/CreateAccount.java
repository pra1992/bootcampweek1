package bootcamp2testng;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateAccount extends BaseClass {
	
	@Test(dataProvider = "getdata1")
	public void addAccount(String AccountName, String AccountType) {

		// Capturing the web elements of HomePage
		// Toggle Button
		WebElement btnToggle = driver.findElement(By.xpath("//*[@title='App Launcher']"));
		// Clicking the Toggle Button in Home Page
		w.waitforVisibilityofElement(btnToggle, "Toggle Button");
		u.moveToElement(btnToggle, "Toggle Button");
		u.clickElementUsingJavascript(btnToggle, "Toggle Button");
		// ViewAll Button
		WebElement btnViewAll = driver.findElement(By.xpath("//*[@kx-type='underline']"));
		// Clicking the View All Button
		u.clickElementUsingJavascript(btnViewAll, "View All Button");
		// Switching to the App Launcher Window
		u.checkWindowHandles(OpenedWindows);
		// Identifying the Sales Locator and clicking on Sales
		WebElement btnSales = driver.findElement(By.xpath(
				"//*[@data-name='Sales']//*[contains(text(),'Manage your sales process with accounts, leads, opportunities, and more')]"));
		actions.moveToElement(btnSales).click().build().perform();
		u.clickElementUsingActions(btnSales, "Sales Button");

		w.waitforTitle("Home | Salesforce");

		// Checking for the Window Handles
		u.checkWindowHandles(OpenedWindows);

		// Navigating through the sales option and clicking on Accounts
		List<WebElement> tabOptions = driver
				.findElements(By.xpath("//*[@role='navigation']//*[@role='list']//one-app-nav-bar-item-root//a"));
		for (int i = 0; i <= (tabOptions.size()) - 1; i++) {
			String TabName = tabOptions.get(i).getAttribute("title").trim();
			if (TabName.equals("Accounts")) {
				u.clickElementUsingJavascript(tabOptions.get(i), "Accounts");
				break;
			}
		}

		// Identifying and Click on New Button

		WebElement btnNew = driver.findElement(By.xpath("//*[contains(@class,'forceActionLink')]//*[@title='New']"));
		u.clickElementUsingActions(btnNew, "Create Account BUtton");

		// Waiting for the New Account window to be opened

		WebElement titleNewAccount = driver.findElement(By.xpath("//h2[contains(text(),'New Account')]"));
		w.waitforVisibilityofElement(titleNewAccount, "New Account Window");

		// Checking for the Window Handles

		u.checkWindowHandles(OpenedWindows);

		// Identifying and entering the value in Account Name

		WebElement txtAccountName = driver.findElement(By.xpath("//*[@field-label='Account Name']//input"));

		w.waitforVisibilityofElement(txtAccountName, "Account Name Text Box");
		u.moveToElement(txtAccountName, "Account Name Text Box");
		u.enterValueInTextbox(txtAccountName, AccountName);

		// Identifying and Entering the value in Ownership
		WebElement ddOwnership = driver.findElement(By.xpath("//button[contains(@aria-label,'Ownership')]"));
		u.clickElementUsingJavascript(ddOwnership, "Ownership dropdown");

		List<WebElement> OwnerShipOptions = driver
				.findElements(By.xpath("//lightning-base-combobox-item[@role='option']//*[@class='slds-media__body']"));

		for (int i = 0; i <= (OwnerShipOptions.size()) - 1; i++) {
			String Option = OwnerShipOptions.get(i).getText().trim();
			if (Option.equals("Public")) {
				u.clickElementUsingJavascript(OwnerShipOptions.get(i), "Select DRopdown Option as Public");
				break;
			}
		}
		// Clicking on Save Button
		WebElement btnSave = driver.findElement(By.xpath("//*[@name='SaveEdit']"));
		u.clickElementUsingActions(btnSave, "Save Account");

		// Verifying the Success Toaster Message

		WebElement txtMessage = driver.findElement(By.xpath("//*[@data-aura-class='forceActionsText']//a"));
		if (txtMessage.getText().trim().contains(AccountName)) {
			Assert.assertTrue(true, "Account is added successful");
		}

		// Opening the Accounts Tab Table to verify the Added Account

		// Checking for the Window Handles

		u.checkWindowHandles(OpenedWindows);
		// Navigating through the sales option and clicking on Accounts
		List<WebElement> tabOption = driver
				.findElements(By.xpath("//*[@role='navigation']//*[@role='list']//one-app-nav-bar-item-root//a"));
		for (int i = 0; i <= (tabOption.size()) - 1; i++) {
			String TabName = tabOption.get(i).getAttribute("title").trim();
			if (TabName.equals("Accounts")) {
				u.clickElementUsingJavascript(tabOption.get(i), "Select Tab Name as Accounts");
				break;
			}
		}

		// Checking for the added element in the table

		// Searching the Account in the table

		WebElement txtSearchAccount = driver.findElement(By.xpath("//*[@placeholder='Search this list...']"));

		txtSearchAccount.sendKeys(AccountName);
		txtSearchAccount.sendKeys(Keys.ENTER);

		// Validating the added Account in the row

		for (int i = 0; i <= (driver.findElements(By.xpath("//table/tbody/tr"))).size() - 1; i++) {
			try {
				w.waitforVisibilityofAllElements(driver.findElements(By.xpath("//table/tbody/tr")),
						"Newly added Account in the row");
				if ((driver.findElements(By.xpath("//table/tbody/tr"))).get(i).getText().trim().contains(AccountName)) {
					Assert.assertTrue(true, "Account Name is verified in the Table");
				}
			} catch (StaleElementReferenceException e) {
				w.waitforStalenessofElement(driver.findElements(By.xpath("//table/tbody/tr")).get(i),
						"Newly added Account in the ro");
				driver.findElements(By.xpath("//table/tbody/tr"));
				if ((driver.findElements(By.xpath("//table/tbody/tr"))).get(i).getText().trim().contains(AccountName)) {
					Assert.assertTrue(true, "Account Name is verified in the Table");
				}
			} catch (TimeoutException e) {
				txtSearchAccount.clear();
				txtSearchAccount.sendKeys(AccountName);
				txtSearchAccount.sendKeys(Keys.ENTER);
				w.waitforVisibilityofAllElements(driver.findElements(By.xpath("//table/tbody/tr")), "Account Table");
				if ((driver.findElements(By.xpath("//table/tbody/tr"))).get(i).getText().trim().contains(AccountName)) {
					Assert.assertTrue(true, "Account Name is verified in the Table");
				}
			}

		}

	}


}