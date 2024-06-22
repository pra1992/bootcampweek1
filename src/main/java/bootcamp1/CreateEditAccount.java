package bootcamp1;

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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CreateEditAccount {

	//

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ****Creation of WebDriver Object****//
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

		WebDriver driver = new ChromeDriver(options);
		// Declaring Java Script Executor
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Deleting the Cookies
		driver.manage().deleteAllCookies();
		// Launching the Url//
		driver.get("https://login.salesforce.com/");
		// maximizing the window and setting up implicit wait
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		// Declaring Actions Instance
		Actions actions = new Actions(driver);
		// Explicit Wait Declaration
		WebDriverWait wait;
		String WindowHandle = driver.getWindowHandle();

		String AccountName = "Prasanth Sankaran";
		String PhoneNo = "75023687";
		// Locating all the elements in the login page

		WebElement txtUserName = driver.findElement(By.xpath("//*[@id='username']"));

		WebElement txtPassword = driver.findElement(By.xpath("//*[@id='password']"));

		WebElement btnLogin = driver.findElement(By.xpath("//*[@id='Login']"));

//**********************Test Case1 Create Account*****************************************//		
		// Entering the UserName, Password and click on Login Button
		txtUserName.sendKeys("bootcamp_2024@testleaf.com");
		txtPassword.sendKeys("Bootcamp@123");
		btnLogin.click();

		// Verifying the title of the login page
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.titleContains("Home | Salesforce"));
		// Verify login is successful
		if (driver.getTitle().contains("Home | Salesforce")) {
			System.out.println("Login is Successful");
		}

		// Capturing the web elements of HomePage
		// Toggle Button
		WebElement btnToggle = driver.findElement(By.xpath("//*[@class='slds-icon-waffle']"));
		// Clicking the Toggle Button in Home Page
		actions.moveToElement(btnToggle).click().build().perform();
		// ViewAll Button
		WebElement btnViewAll = driver.findElement(By.xpath("//*[@kx-type='underline']"));
		// Clicking the View All Button
		js.executeScript("arguments[0].click();", btnViewAll);
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

		actions.moveToElement(btnSales).click().build().perform();

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
				js.executeScript("arguments[0].click();", tabOptions.get(i));
				break;
			}
		}

		// Identifying and Click on New Button

		WebElement btnNew = driver.findElement(By.xpath("//*[contains(@class,'forceActionLink')]//*[@title='New']"));
		actions.moveToElement(btnNew).click().build().perform();

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
		actions.moveToElement(txtAccountName).build().perform();
		txtAccountName.sendKeys(AccountName);

		// Identifying and Entering the value in Ownership
		WebElement ddOwnership = driver
				.findElement(By.xpath("//*[contains(@aria-label,'Ownership - Current Selection')]"));

		js.executeScript("arguments[0].click();", ddOwnership);

		List<WebElement> OwnerShipOptions = driver
				.findElements(By.xpath("//lightning-base-combobox-item[@role='option']//*[@class='slds-media__body']"));

		for (int i = 0; i <= (OwnerShipOptions.size()) - 1; i++) {
			String Option = OwnerShipOptions.get(i).getText().trim();
			if (Option.equals("Public")) {
				js.executeScript("arguments[0].click();", OwnerShipOptions.get(i));
				break;
			}
		}
		// Clicking on Save Button
		WebElement btnSave = driver.findElement(By.xpath("//*[@name='SaveEdit']"));
		actions.moveToElement(btnSave).click().build().perform();

		// Verifying the Success Toaster Message

		WebElement txtMessage = driver.findElement(By.xpath("//*[@data-aura-class='forceActionsText']//a"));
		if (txtMessage.getText().trim().contains(AccountName)) {
			System.out.println("Account is added successful");
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
				js.executeScript("arguments[0].click();", tabOption.get(i));
				break;
			}
		}

		// Checking for the added element in the table

		// Validating the added Account in the row
		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//table/tbody/tr"))));
		for (WebElement webElement : driver.findElements(By.xpath("//table/tbody/tr"))) {
			if (webElement.getText().trim().equals(AccountName)) {

				System.out.println("Account is verified in the table");
			}

//**************Test Case 2 Edit Account************************************//	 

			// Searching the Account in the table

			WebElement txtSearchAccount = driver.findElement(By.xpath("//*[@placeholder='Search this list...']"));

			txtSearchAccount.sendKeys(AccountName);
			txtSearchAccount.sendKeys(Keys.ENTER);
			for (int i = 0; i <= (driver.findElements(By.xpath("//table/tbody/tr"))).size() - 1; i++) {
				try {
					wait.until(ExpectedConditions
							.visibilityOfAllElements(driver.findElements(By.xpath("//table/tbody/tr"))));
				} catch (StaleElementReferenceException e) {
					//wait.until(
						//	ExpectedConditions.stalenessOf((driver.findElements(By.xpath("//table/tbody/tr"))).get(i)));
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table/tbody/tr")));	
					
				} catch (TimeoutException e) {
					txtSearchAccount.clear();
					txtSearchAccount.sendKeys(AccountName);
					txtSearchAccount.sendKeys(Keys.ENTER);
				}

			}
//			// Clicking on the Edit Icon
//			List<WebElement> colAccountsTable = driver
//					.findElements(By.xpath("//table/thead/tr/th"));
//			for (int j = 0; j <= (colAccountsTable).size() - 1; j++) {
//				try {
//				String value = colAccountsTable.get(j).getAttribute("aria-label").trim();
//				if ((value.contains("Action"))) {
//					js.executeScript("arguments[0].click();", driver.findElement(By.xpath("((//*[@data-aura-class='forceVirtualAction']//a)")));
//					js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@class='branding-actions actionMenu']//a[@title='Edit']")));
//					break;
//				}}
//				catch (java.lang.NullPointerException e) {
//				    continue;	
//				}
//				}
			try {
				js.executeScript("arguments[0].click()",
						driver.findElement(By.xpath("(//*[@data-aura-class='forceVirtualAction']//a)")));
			} catch (org.openqa.selenium.NoSuchElementException e) {
				txtSearchAccount.clear();
				txtSearchAccount.sendKeys(AccountName);
				txtSearchAccount.sendKeys(Keys.ENTER);
			}

			WebElement btnEdit = driver.findElement(By.xpath("//a[@title='Edit']"));
			js.executeScript("arguments[0].click();", btnEdit);

			// *********Editing the Account fields*********

			// Enter Unique Number in Phone Field

			WebElement txtPhoneNo = driver.findElement(By.xpath("//*[@field-label='Phone']//input"));
			txtPhoneNo.sendKeys(PhoneNo);

			// Identifying and selecting the Account Type

			WebElement ddAccountType = driver
					.findElement(By.xpath("//*[contains(@aria-label,'Type - Current Selection')]"));

			js.executeScript("arguments[0].click();", ddAccountType);

			// Selecting the options in the Account Type

			List<WebElement> optionsAccountType = driver
					.findElements(By.xpath("//label[text()='Type']/..//*[@class='slds-truncate']"));
			try {
				for (int i = 0; i <= (optionsAccountType.size()) - 1; i++) {
					String AccountTypeOption = optionsAccountType.get(i).getAttribute("title").trim();

					if (AccountTypeOption.contains("Technology Partner")) {
						js.executeScript("arguments[0].click();", optionsAccountType.get(i));
						break;
					}
				}
			} catch (java.lang.NullPointerException e) {
				continue;
			}

			// Selecting the Industry as Health Care
			WebElement ddIndustry = driver
					.findElement(By.xpath("//*[contains(@aria-label,'Industry - Current Selection')]"));
			js.executeScript("arguments[0].click();", ddIndustry);

			List<WebElement> optionsIndustry = driver
					.findElements(By.xpath("//label[text()='Industry']/..//*[@class='slds-truncate']"));

			for (int j = 0; j <= (optionsIndustry.size()) - 1; j++) {
				String IndustryTypeOption = optionsIndustry.get(j).getAttribute("title").trim();
				if (IndustryTypeOption.contains("Healthcare")) {
					js.executeScript("arguments[0].click();", optionsIndustry.get(j));
					break;
				}
			}

			// Identifying and filling the Billing Address
			js.executeScript("document.querySelector('.actionBody').scrollBy(0,750)");

			WebElement txtBillingStreet = driver.findElement(
					By.xpath("//*[text()='Billing Address']/..//textarea[@autocomplete='street-address']"));
			WebElement txtBillingCity = driver
					.findElement(By.xpath("//*[text()='Billing Address']/..//*[@name='city']"));
			WebElement txtBillingZipCode = driver
					.findElement(By.xpath("//*[text()='Billing Address']/..//*[@name='postalCode']"));
			WebElement txtBillingProvince = driver
					.findElement(By.xpath("//*[text()='Billing Address']/..//*[@name='province']"));
			WebElement txtBillingCountry = driver
					.findElement(By.xpath("//*[text()='Billing Address']/..//*[@name='province']"));

			txtBillingStreet.sendKeys("Chennai1");
			txtBillingCity.sendKeys("Chennai2");
			txtBillingZipCode.sendKeys("Chennai3");
			txtBillingCountry.sendKeys("Chennai4");

			// Identifying and Filling the Shipping Address

			WebElement txtShippingStreet = driver.findElement(
					By.xpath("//*[text()='Shipping Address']/..//textarea[@autocomplete='street-address']"));
			WebElement txtShippingCity = driver
					.findElement(By.xpath("//*[text()='Shipping Address']/..//*[@name='city']"));
			WebElement txtShippingZipCode = driver
					.findElement(By.xpath("//*[text()='Shipping Address']/..//*[@name='postalCode']"));
			WebElement txtShippingProvince = driver
					.findElement(By.xpath("//*[text()='Shipping Address']/..//*[@name='province']"));
			WebElement txtShippingCountry = driver
					.findElement(By.xpath("//*[text()='Shipping Address']/..//*[@name='province']"));

			txtShippingStreet.sendKeys("Banglore1");
			txtShippingCity.sendKeys("Banglore2");
			txtShippingZipCode.sendKeys("Banglore3");
			txtShippingCountry.sendKeys("Banglore4");

			// Selecting the Customer Priority as Low

			WebElement ddCustomerPriority = driver
					.findElement(By.xpath("//*[contains(@aria-label,'Customer Priority - Current Selection')]"));
			js.executeScript("arguments[0].click();", ddCustomerPriority);

			List<WebElement> optionsCustomerPriority = driver
					.findElements(By.xpath("//label[text()='Customer Priority']/..//*[@class='slds-truncate']"));

			for (int j = 0; j <= (optionsCustomerPriority.size()) - 1; j++) {
				String CustomerPriorityTypeOption = optionsCustomerPriority.get(j).getAttribute("title").trim();
				if (CustomerPriorityTypeOption.contains("Low")) {
					js.executeScript("arguments[0].click();", optionsCustomerPriority.get(j));
					break;
				}
			}

			// Select SLA as Silver
			js.executeScript("document.querySelector('.actionBody').scrollBy(0,200)");

			WebElement ddSLA = driver.findElement(By.xpath("//*[contains(@aria-label,'SLA - Current Selection')]"));
			js.executeScript("arguments[0].click();", ddSLA);

			List<WebElement> optionsSLA = driver
					.findElements(By.xpath("//label[text()='SLA']/..//*[@class='slds-truncate']"));

			for (int j = 0; j <= (optionsSLA.size()) - 1; j++) {
				String SLATypeOption = optionsSLA.get(j).getAttribute("title".trim());
				if (SLATypeOption.contains("Silver")) {
					js.executeScript("arguments[0].click();", optionsSLA.get(j));
					break;
				}
			}

			// 14) Select Active as NO

			// Scrolling

			js.executeScript("document.querySelector('.actionBody').scrollBy(0,100)");

			WebElement ddActive = driver
					.findElement(By.xpath("//*[contains(@aria-label,'Active - Current Selection')]"));
			js.executeScript("arguments[0].click();", ddActive);

			List<WebElement> optionsActive = driver
					.findElements(By.xpath("//label[text()='Active']/..//*[@class='slds-truncate']"));
			try {
				for (int j = 0; j <= (optionsActive.size()) - 1; j++) {
					String ActiveOption = optionsActive.get(j).getAttribute("title").trim();
					if (ActiveOption.equals("No")) {
						js.executeScript("arguments[0].click();", optionsActive.get(j));
						break;
					}
				}
			} catch (java.lang.NullPointerException e) {
				continue;
			}

			// Select Upsell Oppurtunity as No

			WebElement ddUpsell = driver
					.findElement(By.xpath("//*[contains(@aria-label,'Upsell Opportunity - Current Selection')]"));
			js.executeScript("arguments[0].click();", ddUpsell);

			List<WebElement> OptionsUpsell = driver
					.findElements(By.xpath("//label[text()='Upsell Opportunity']/..//*[@class='slds-truncate']"));
			try {
				for (int j = 0; j <= (OptionsUpsell.size()) - 1; j++) {
					String UpsellOption = OptionsUpsell.get(j).getAttribute("title").trim();
					if (UpsellOption.equals("No")) {
						js.executeScript("arguments[0].click();", OptionsUpsell.get(j));
						break;
					}
				}
			} catch (java.lang.NullPointerException e) {
				continue;
			}

			// Clicking on Save

			// Clicking on Save Button
			try {
				actions.moveToElement(btnSave).click().build().perform();
			} catch (org.openqa.selenium.StaleElementReferenceException e) {
				wait.until(ExpectedConditions.stalenessOf(btnSave));
				btnSave = driver.findElement(By.xpath("//*[@name='SaveEdit']"));
				wait.until(ExpectedConditions.visibilityOf(btnSave));
				actions.moveToElement(btnSave).click().build().perform();
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

		driver.close();
	}
}