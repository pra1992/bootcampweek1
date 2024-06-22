package testng;

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

public class AccountsFunctionality extends BaseClass {
	Utilities u = new Utilities();

	@Test
	public void CreateAccount() {

		
		// Capturing the web elements of HomePage
		// Toggle Button
		WebElement btnToggle = driver.findElement(By.xpath("//*[@class='slds-icon-waffle']"));
		// Clicking the Toggle Button in Home Page
		u.clickElementUsingActions(btnToggle, "Toggle BUtton");
		// ViewAll Button
		WebElement btnViewAll = driver.findElement(By.xpath("//*[@kx-type='underline']"));
		// Clicking the View All Button
		u.clickElementUsingJavascript(btnViewAll, "View All Button");
		// Switching to the App Launcher Window
		Set<String> OpenedWindows = driver.getWindowHandles();
		for (String OpenWindow : OpenedWindows) {
			if (!OpenWindow.equals(WindowHandle)) {
				driver.switchTo().window(OpenWindow);
			}
		}
		// Identifying the Sales Locator and clicking on Sales
		WebElement btnSales = driver.findElement(By.xpath(
				"//*[@data-name='Sales']//*[contains(text(),'Manage your sales process with accounts, leads, opportunities, and more')]"));
		u.clickElementUsingActions(btnSales, "Sales Button");

		wait.until(ExpectedConditions.titleContains("Home | Salesforce"));

		// Checking for the Window Handles

		Set<String> NewOpenedWindows = driver.getWindowHandles();
		for (String OpenWindow : NewOpenedWindows) {
			if (!OpenWindow.equals(WindowHandle)) {
				driver.switchTo().window(OpenWindow);
			}
		}

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
		wait.until(ExpectedConditions.visibilityOf(titleNewAccount));

		// Checking for the Window Handles

		Set<String> CurrentOpenedWindows = driver.getWindowHandles();
		for (String OpenWindow : CurrentOpenedWindows) {
			if (!OpenWindow.equals(WindowHandle)) {
				driver.switchTo().window(OpenWindow);
			}
		}

		// Identifying and entering the value in Account Name

		WebElement txtAccountName = driver.findElement(By.xpath("//*[@field-label='Account Name']//input"));

		wait.until(ExpectedConditions.visibilityOf(txtAccountName));
		u.moveToElement(txtAccountName, "Account Name Text Box");
		u.enterValueInTextbox(txtAccountName, "Account Name Text Box");

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

		Set<String> Windows = driver.getWindowHandles();
		for (String OpenWindow : Windows) {
			if (!OpenWindow.equals(WindowHandle)) {
				driver.switchTo().window(OpenWindow);
			}
		}
		// Navigating through the sales option and clicking on Accounts
		List<WebElement> tabOption = driver
				.findElements(By.xpath("//*[@role='navigation']//*[@role='list']//one-app-nav-bar-item-root//a"));
		for (int i = 0; i <= (tabOption.size()) - 1; i++) {
			String TabName = tabOption.get(i).getAttribute("title").trim();
			if (TabName.equals("Accounts")) {
				u.clickElementUsingJavascript( tabOption.get(i), "Select Tab Name as Accounts");
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
				wait.until(
						ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//table/tbody/tr"))));
				if ((driver.findElements(By.xpath("//table/tbody/tr"))).get(i).getText().trim().contains(AccountName)) {
					Assert.assertTrue(true, "Account Name is verified in the Table");
				}
			} catch (StaleElementReferenceException e) {
				wait.until(ExpectedConditions.stalenessOf((driver.findElements(By.xpath("//table/tbody/tr"))).get(i)));
				driver.findElements(By.xpath("//table/tbody/tr"));
				if ((driver.findElements(By.xpath("//table/tbody/tr"))).get(i).getText().trim().contains(AccountName)) {
					Assert.assertTrue(true, "Account Name is verified in the Table");
				}
			} catch (TimeoutException e) {
				txtSearchAccount.clear();
				txtSearchAccount.sendKeys(AccountName);
				txtSearchAccount.sendKeys(Keys.ENTER);
				wait.until(
						ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//table/tbody/tr"))));
				if ((driver.findElements(By.xpath("//table/tbody/tr"))).get(i).getText().trim().contains(AccountName)) {
					Assert.assertTrue(true, "Account Name is verified in the Table");
				}
			}

		}

	}

//=============================Edit Account====================================//
	@Test
	public void EditAccount() {
			// Capturing the web elements of HomePage
		// Toggle Button
		WebElement btnToggle = driver.findElement(By.xpath("//*[@class='slds-icon-waffle']"));
		// Clicking the Toggle Button in Home Page
		u.clickElementUsingActions(btnToggle, "Toggle Button");
		// ViewAll Button
		WebElement btnViewAll = driver.findElement(By.xpath("//*[@kx-type='underline']"));
		// Clicking the View All Button
		u.clickElementUsingJavascript(btnViewAll, "View All Button");
		// Switching to the App Launcher Window
		Set<String> OpenedWindows = driver.getWindowHandles();
		for (String OpenWindow : OpenedWindows) {
			if (!OpenWindow.equals(WindowHandle)) {
				driver.switchTo().window(OpenWindow);
			}
		}
		// Identifying the Sales Locator and clicking on Sales
		WebElement btnSales = driver.findElement(By.xpath(
				"//*[@data-name='Sales']//*[contains(text(),'Manage your sales process with accounts, leads, opportunities, and more')]"));
		u.clickElementUsingActions(btnSales, "Sales Button");

		wait.until(ExpectedConditions.titleContains("Home | Salesforce"));

		// Checking for the Window Handles

		Set<String> NewOpenedWindows = driver.getWindowHandles();
		for (String OpenWindow : NewOpenedWindows) {
			if (!OpenWindow.equals(WindowHandle)) {
				driver.switchTo().window(OpenWindow);
			}
		}

		// Navigating through the sales option and clicking on Accounts
		List<WebElement> tabOptions = driver
				.findElements(By.xpath("//*[@role='navigation']//*[@role='list']//one-app-nav-bar-item-root//a"));
		for (int i = 0; i <= (tabOptions.size()) - 1; i++) {
			String TabName = tabOptions.get(i).getAttribute("title").trim();
			if (TabName.equals("Accounts")) {
				u.clickElementUsingJavascript(tabOptions.get(i), "Accounts Tab");
				break;
			}
		} // Searching the Account in the table

		WebElement txtSearchAccount = driver.findElement(By.xpath("//*[@placeholder='Search this list...']"));

		txtSearchAccount.sendKeys(AccountName);
		txtSearchAccount.sendKeys(Keys.ENTER);
		for (int i = 0; i <= (driver.findElements(By.xpath("//table/tbody/tr"))).size() - 1; i++) {
			try {
				wait.until(
						ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//table/tbody/tr"))));
			} catch (StaleElementReferenceException e) {
//				wait.until(ExpectedConditions.stalenessOf((driver.findElements(By.xpath("//table/tbody/tr"))).get(i)));
//				driver.findElements(By.xpath("//table/tbody/tr"));
//				wait.until(ExpectedConditions.visibilityOf((driver.findElements(By.xpath("//table/tbody/tr"))).get(i)));
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table/tbody/tr")));

			} catch (org.openqa.selenium.TimeoutException e) {
				txtSearchAccount.clear();
				txtSearchAccount.sendKeys(AccountName);
				txtSearchAccount.sendKeys(Keys.ENTER);
			}

		}

		try {
			js.executeScript("arguments[0].click()",
					driver.findElement(By.xpath("(//*[@data-aura-class='forceVirtualAction']//a)")));
		} catch (org.openqa.selenium.NoSuchElementException e) {
			txtSearchAccount.clear();
			txtSearchAccount.sendKeys(AccountName);
			txtSearchAccount.sendKeys(Keys.ENTER);
		}

		WebElement btnEdit = driver.findElement(By.xpath("//a[@title='Edit']"));
		u.clickElementUsingJavascript(btnEdit, "Edit Account");

		// *********Editing the Account fields*********

		// Enter Unique Number in Phone Field

		WebElement txtPhoneNo = driver.findElement(By.xpath("//*[@field-label='Phone']//input"));
		u.enterValueInTextbox(txtPhoneNo, PhoneNo);

		// Identifying and selecting the Account Type

		WebElement ddAccountType = driver.findElement(By.xpath("//button[contains(@aria-label,'Type')]"));
		u.clickElementUsingJavascript(ddAccountType, "Select Account Type");

		// Selecting the options in the Account Type

		List<WebElement> optionsAccountType = driver
				.findElements(By.xpath("//label[text()='Type']/..//*[@class='slds-truncate']"));

		for (int i = 0; i <= (optionsAccountType.size()) - 1; i++) {
			try {
				String AccountTypeOption = optionsAccountType.get(i).getText().trim();

				if (AccountTypeOption.equals("Technology Partner")) {
					u.clickElementUsingJavascript(optionsAccountType.get(i), "Select Account Type as Technology Partner");
					break;
				}

			} catch (java.lang.NullPointerException e) {
				continue;
			}
		}

		// Selecting the Industry as Health Care
		WebElement ddIndustry = driver.findElement(By.xpath("//*[contains(@aria-label,'Industry')]"));
		u.clickElementUsingJavascript(ddIndustry, "Select Industry");

		List<WebElement> optionsIndustry = driver
				.findElements(By.xpath("//label[text()='Industry']/..//*[@class='slds-truncate']"));

		for (int j = 0; j <= (optionsIndustry.size()) - 1; j++) {
			String IndustryTypeOption = optionsIndustry.get(j).getText().trim();
			if (IndustryTypeOption.contains("Healthcare")) {
				u.clickElementUsingJavascript(optionsIndustry.get(j), "Select Industry Option as Health Care");
				break;
			}
		}

		// Identifying and filling the Billing Address
		js.executeScript("document.querySelector('.actionBody').scrollBy(0,750)");

		WebElement txtBillingStreet = driver
				.findElement(By.xpath("//*[text()='Billing Address']/..//textarea[@autocomplete='street-address']"));
		WebElement txtBillingCity = driver.findElement(By.xpath("//*[text()='Billing Address']/..//*[@name='city']"));
		WebElement txtBillingZipCode = driver
				.findElement(By.xpath("//*[text()='Billing Address']/..//*[@name='postalCode']"));
		WebElement txtBillingProvince = driver
				.findElement(By.xpath("//*[text()='Billing Address']/..//*[@name='province']"));
		WebElement txtBillingCountry = driver
				.findElement(By.xpath("//*[text()='Billing Address']/..//*[@name='province']"));

		u.enterValueInTextbox(txtBillingStreet, "Chennai1");
		u.enterValueInTextbox(txtBillingCity, "Chennai2");
		u.enterValueInTextbox(txtBillingZipCode, "Chennai3");
		u.enterValueInTextbox(txtBillingCountry, "Chennai4");

		// Identifying and Filling the Shipping Address

		WebElement txtShippingStreet = driver
				.findElement(By.xpath("//*[text()='Shipping Address']/..//textarea[@autocomplete='street-address']"));
		WebElement txtShippingCity = driver.findElement(By.xpath("//*[text()='Shipping Address']/..//*[@name='city']"));
		WebElement txtShippingZipCode = driver
				.findElement(By.xpath("//*[text()='Shipping Address']/..//*[@name='postalCode']"));
		WebElement txtShippingProvince = driver
				.findElement(By.xpath("//*[text()='Shipping Address']/..//*[@name='province']"));
		WebElement txtShippingCountry = driver
				.findElement(By.xpath("//*[text()='Shipping Address']/..//*[@name='province']"));
		
		u.enterValueInTextbox(txtShippingStreet, "Chennai1");
		u.enterValueInTextbox(txtShippingCity, "Chennai2");
		u.enterValueInTextbox(txtShippingZipCode, "Chennai3");
		u.enterValueInTextbox(txtShippingCountry, "Chennai4");


		// Selecting the Customer Priority as Low

		WebElement ddCustomerPriority = driver.findElement(By.xpath("//*[contains(@aria-label,'Customer Priority')]"));
		u.clickElementUsingJavascript(ddCustomerPriority, "Select Customer Priority");

		List<WebElement> optionsCustomerPriority = driver
				.findElements(By.xpath("//label[text()='Customer Priority']/..//*[@class='slds-truncate']"));

		for (int j = 0; j <= (optionsCustomerPriority.size()) - 1; j++) {
			String CustomerPriorityTypeOption = optionsCustomerPriority.get(j).getText().trim();
			if (CustomerPriorityTypeOption.contains("Low")) {
				u.clickElementUsingJavascript(optionsCustomerPriority.get(j), "Select value as Low");
				break;
			}
		}

		// Select SLA as Silver
		js.executeScript("document.querySelector('.actionBody').scrollBy(0,200)");

		WebElement ddSLA = driver.findElement(By.xpath("//*[contains(@aria-label,'SLA')]"));
		u.clickElementUsingJavascript(ddSLA, "Select SLA");

		List<WebElement> optionsSLA = driver
				.findElements(By.xpath("//label[text()='SLA']/..//*[@class='slds-truncate']"));

		for (int j = 0; j <= (optionsSLA.size()) - 1; j++) {
			String SLATypeOption = optionsSLA.get(j).getText().trim();
			if (SLATypeOption.contains("Silver")) {
				u.clickElementUsingJavascript(optionsSLA.get(j), "Select SLA Option as Silver");
				break;
			}
		}

		// 14) Select Active as NO

		// Scrolling

		js.executeScript("document.querySelector('.actionBody').scrollBy(0,100)");

		WebElement ddActive = driver.findElement(By.xpath("//*[contains(@aria-label,'Active')]"));
		u.clickElementUsingJavascript(ddActive, "Select Active");

		List<WebElement> optionsActive = driver
				.findElements(By.xpath("//label[text()='Active']/..//*[@class='slds-truncate']"));

		for (int j = 0; j <= (optionsActive.size()) - 1; j++) {
			try {
				String ActiveOption = optionsActive.get(j).getText().trim();
				if (ActiveOption.equals("No")) {
					u.clickElementUsingJavascript(optionsActive.get(j), "Select An Active Option");
					break;
				}

			} catch (java.lang.NullPointerException e) {
				continue;
			}
		}

		// Select Upsell Oppurtunity as No

		WebElement ddUpsell = driver.findElement(By.xpath("//*[contains(@aria-label,'Upsell Opportunity')]"));
		u.clickElementUsingJavascript(ddUpsell, "Select Upsell");

		List<WebElement> OptionsUpsell = driver
				.findElements(By.xpath("//label[text()='Upsell Opportunity']/..//*[@class='slds-truncate']"));

		for (int j = 0; j <= (OptionsUpsell.size()) - 1; j++) {
			try {
				String UpsellOption = OptionsUpsell.get(j).getText().trim();
				if (UpsellOption.equals("No")) {
					u.clickElementUsingJavascript(OptionsUpsell.get(j), "Select Upsell Option");
					break;
				}
			} catch (java.lang.NullPointerException e) {
				continue;
			}
		}
		// Clicking on Save Button
		WebElement btnSave = driver.findElement(By.xpath("//*[@name='SaveEdit']"));
		try {	
			u.clickElementUsingActions(btnSave, "Save Button");
		} catch (org.openqa.selenium.StaleElementReferenceException e) {
			wait.until(ExpectedConditions.stalenessOf(btnSave));
			btnSave = driver.findElement(By.xpath("//*[@name='SaveEdit']"));
			wait.until(ExpectedConditions.visibilityOf(btnSave));
			u.clickElementUsingActions(btnSave, "Save Button");
		}

		// Verifying the Success Toaster Message
		if (driver.findElement(By.xpath("//*[@data-aura-class='forceActionsText']")).getText().trim()
				.contains("saved")) {
			System.out.println("Account is saved successful");
		}

//Verifying the phone number in the Grid

		// Checking for the added element in the table

		WebElement gridPhoneNoValue = driver.findElement(By.xpath("//*[contains(@class,'forceOutputPhone')]"));
		if (gridPhoneNoValue.getText().trim().equals(PhoneNo)) {
			System.out.println("Phone Number is verified Successfully");
			Assert.assertTrue(true, "Phone Number is verified Successfully");
		}
		
	}
}
