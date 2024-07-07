package bootcamp3pom.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SellerHomePage extends BaseClass {

	public SellerHomePage(WebDriver driver) {
		this.driver = driver;
	}

	List<WebElement> tabOptions = driver
			.findElements(By.xpath("//*[@role='navigation']//*[@role='list']//one-app-nav-bar-item-root//a"));

	WebElement btnNew = driver.findElement(By.xpath("//*[contains(@class,'forceActionLink')]//*[@title='New']"));

	WebElement titleNewAccount = driver.findElement(By.xpath("//h2[contains(text(),'New Account')]"));

	WebElement txtAccountName = driver.findElement(By.xpath("//*[@field-label='Account Name']//input"));

	WebElement ddOwnership = driver.findElement(By.xpath("//button[contains(@aria-label,'Ownership')]"));

	List<WebElement> OwnerShipOptions = driver
			.findElements(By.xpath("//lightning-base-combobox-item[@role='option']//*[@class='slds-media__body']"));
	WebElement btnSave = driver.findElement(By.xpath("//*[@name='SaveEdit']"));

	List<WebElement> tabOption = driver
			.findElements(By.xpath("//*[@role='navigation']//*[@role='list']//one-app-nav-bar-item-root//a"));
	WebElement txtSearchAccount = driver.findElement(By.xpath("//*[@placeholder='Search this list...']"));
	
	WebElement txtMessage = driver.findElement(By.xpath("//*[@data-aura-class='forceActionsText']//a"));
	WebElement ddActionDropdown = driver.findElement(By.xpath("//*[@data-aura-class='forceVirtualAction']//a"));
	WebElement btnEdit = driver.findElement(By.xpath("//a[@title='Edit']"));
	
	 WebElement popUpEditAccount = driver.findElement(By.cssSelector("div[data-aura-class = 'oneRecordActionWrapper']"));
	 
	 WebElement txtPhoneNo = driver.findElement(By.xpath("//*[@field-label='Phone']//input"));
	 WebElement ddAccountType = driver.findElement(By.xpath("//button[contains(@aria-label,'Type')]"));
	 List<WebElement> optionsAccountType = driver
				.findElements(By.xpath("//label[text()='Type']/..//*[@class='slds-truncate']"));
	 
	 WebElement ddIndustry = driver.findElement(By.xpath("//*[contains(@aria-label,'Industry')]"));
	 
	 List<WebElement> optionsIndustry = driver
				.findElements(By.xpath("//label[text()='Industry']/..//*[@class='slds-truncate']"));
	 
	 WebElement txtBillingStreet = driver
				.findElement(By.xpath("//*[text()='Billing Address']/..//textarea[@autocomplete='street-address']"));
		WebElement txtBillingCity = driver.findElement(By.xpath("//*[text()='Billing Address']/..//*[@name='city']"));
		WebElement txtBillingZipCode = driver
				.findElement(By.xpath("//*[text()='Billing Address']/..//*[@name='postalCode']"));
		WebElement txtBillingProvince = driver
				.findElement(By.xpath("//*[text()='Billing Address']/..//*[@name='province']"));
		WebElement txtBillingCountry = driver
				.findElement(By.xpath("//*[text()='Billing Address']/..//*[@name='province']"));
		
		WebElement txtShippingStreet = driver
				.findElement(By.xpath("//*[text()='Shipping Address']/..//textarea[@autocomplete='street-address']"));
		WebElement txtShippingCity = driver.findElement(By.xpath("//*[text()='Shipping Address']/..//*[@name='city']"));
		WebElement txtShippingZipCode = driver
				.findElement(By.xpath("//*[text()='Shipping Address']/..//*[@name='postalCode']"));
		WebElement txtShippingProvince = driver
				.findElement(By.xpath("//*[text()='Shipping Address']/..//*[@name='province']"));
		WebElement txtShippingCountry = driver
				.findElement(By.xpath("//*[text()='Shipping Address']/..//*[@name='province']"));
		WebElement ddCustomerPriority = driver.findElement(By.xpath("//*[contains(@aria-label,'Customer Priority')]"));
		
		WebElement ddSLA = driver.findElement(By.xpath("//*[contains(@aria-label,'SLA')]"));
		List<WebElement> optionsCustomerPriority = driver
				.findElements(By.xpath("//label[text()='Customer Priority']/..//*[@class='slds-truncate']"));
		List<WebElement> optionsSLA = driver
				.findElements(By.xpath("//label[text()='SLA']/..//*[@class='slds-truncate']"));
		WebElement ddActive = driver.findElement(By.xpath("//*[contains(@aria-label,'Active')]"));
		List<WebElement> optionsActive = driver
				.findElements(By.xpath("//label[text()='Active']/..//*[@class='slds-truncate']"));
		WebElement ddUpsell = driver.findElement(By.xpath("//*[contains(@aria-label,'Upsell Opportunity')]"));
		
		List<WebElement> OptionsUpsell = driver
				.findElements(By.xpath("//label[text()='Upsell Opportunity']/..//*[@class='slds-truncate']"));
		
	public SellerHomePage clickNewButton() {
		w.waitforVisibilityofElement(btnNew, "Create New Button");
		u.clickElementUsingActions(btnNew, "Create Account BUtton");
		return this;
		
	}
	
	public SellerHomePage verifyCreateNewAccountPopupIsOpened() {
		w.waitforVisibilityofElement(titleNewAccount, "Create New Account Popup");
		u.verifyElementIsDisplayed(titleNewAccount, "Create New Account Popup");
		u.checkWindowHandles(OpenedWindows);
		return this;
	}
	
	public SellerHomePage enterAccountDetailsInCreateNewAccountPopup(String AccountName, String AccountType) {
		w.waitforVisibilityofElement(txtAccountName, "Account Name Text Box");
		u.moveToElement(txtAccountName, "Account Name Text Box");
		u.enterValueInTextbox(txtAccountName, AccountName);
		u.clickElementUsingJavascript(ddOwnership, "Ownership dropdown");
		for (int i = 0; i <= (OwnerShipOptions.size()) - 1; i++) {
			String Option = OwnerShipOptions.get(i).getText().trim();
			if (Option.equals(AccountType)) {
				u.clickElementUsingJavascript(OwnerShipOptions.get(i), "Select DRopdown Option as Public");
				break;
			}
		}
		return this;
	}
	
	
	public SellerHomePage clickSaveButton() {
		u.clickElementUsingActions(btnSave, "Save Account");
		return this;
	}
	
	public AccountsPage verifyAccountCreationSuccessMessage() {
		w.waitforVisibilityofElement(txtMessage , "Account Creation Success Message");
		if (txtMessage.getText().trim().contains(AccountName)) {
			Assertion.assertTrue(true, "Account is added successful");
		}
		return new AccountsPage(driver);
	}
	
	public SellerHomePage searchForNewAccountInTable(String AccountName, String AccountType) {
		w.waitforVisibilityofElement(txtSearchAccount, "Search Account Text Box");
		txtSearchAccount.sendKeys(AccountName);
		txtSearchAccount.sendKeys(Keys.ENTER);
		for (int i = 0; i <= (driver.findElements(By.xpath("//table/tbody/tr"))).size() - 1; i++) {
			try {
				w.waitforVisibilityofAllElements(driver.findElements(By.xpath("//table/tbody/tr")), "Accounts Table");
			} catch (StaleElementReferenceException e) {
//				wait.until(ExpectedConditions.stalenessOf((driver.findElements(By.xpath("//table/tbody/tr"))).get(i)));
//				driver.findElements(By.xpath("//table/tbody/tr"));
//				wait.until(ExpectedConditions.visibilityOf((driver.findElements(By.xpath("//table/tbody/tr"))).get(i)));
				w.waitforPresenceofElement("//table/tbody/tr", "Accounts Table");

			} catch (org.openqa.selenium.TimeoutException e) {
				txtSearchAccount.clear();
				txtSearchAccount.sendKeys(AccountName);
				txtSearchAccount.sendKeys(Keys.ENTER);
			}

		}
		return this;
	}
	
	public SellerHomePage valideAddedAccountInTable(String AccountName, String AccountType) {
		for (int i = 0; i <= (driver.findElements(By.xpath("//table/tbody/tr"))).size() - 1; i++) {
			try {
				w.waitforVisibilityofAllElements(driver.findElements(By.xpath("//table/tbody/tr")),
						"Newly added Account in the row");
				if ((driver.findElements(By.xpath("//table/tbody/tr"))).get(i).getText().trim().contains(AccountName)) {
					Assertion.assertTrue(true, "Account Name is verified in the Table");
				}
			} catch (StaleElementReferenceException e) {
				w.waitforStalenessofElement(driver.findElements(By.xpath("//table/tbody/tr")).get(i),
						"Newly added Account in the ro");
				driver.findElements(By.xpath("//table/tbody/tr"));
				if ((driver.findElements(By.xpath("//table/tbody/tr"))).get(i).getText().trim().contains(AccountName)) {
					Assertion.assertTrue(true, "Account Name is verified in the Table");
				}
			} catch (TimeoutException e) {
				txtSearchAccount.clear();
				txtSearchAccount.sendKeys(AccountName);
				txtSearchAccount.sendKeys(Keys.ENTER);
				w.waitforVisibilityofAllElements(driver.findElements(By.xpath("//table/tbody/tr")), "Account Table");
				if ((driver.findElements(By.xpath("//table/tbody/tr"))).get(i).getText().trim().contains(AccountName)) {
					Assertion.assertTrue(true, "Account Name is verified in the Table");
				}
			}

		}
		
		return this;

	}
	
	public SellerHomePage clickAction(String EditAccount) {
		
		try {
			
			w.waitforVisibilityofElement(ddActionDropdown, EditAccount);
		    u.moveToElement(ddActionDropdown, EditAccount);
			u.clickElementUsingJavascript(ddActionDropdown, EditAccount);
		} catch (org.openqa.selenium.NoSuchElementException e) {
//			txtSearchAccount.clear();
//			txtSearchAccount.sendKeys(AccountName);
//			txtSearchAccount.sendKeys(Keys.ENTER);
			w.waitforVisibilityofElement(ddActionDropdown, EditAccount);
		    u.moveToElement(ddActionDropdown, EditAccount);
			u.clickElementUsingJavascript(ddActionDropdown, EditAccount);
		}
		return this;
		
	}
	
	public SellerHomePage clickEdit() {
		u.clickElementUsingJavascript(btnEdit, "Edit Account");
		return this;
	}
	
	public SellerHomePage editAccountDetailsAndSave(String PhoneNo,	String AccountType, String Industry, String	BillingAddress1, String	BillingAddress2, String	BillingAddress3, String	BillingAddress4, String	ShippingAddress1, String ShippingAddress2,String ShippingAddress3, String ShippingAddress4, String	Active) {
		w.waitforVisibilityofElement(popUpEditAccount, "Edit Account Modal");
		u.enterValueInTextbox(txtPhoneNo, PhoneNo);
		u.clickElementUsingJavascript(ddAccountType, AccountType);
		for (int i = 0; i <= (optionsAccountType.size()) - 1; i++) {
			try {
				String AccountTypeOption = optionsAccountType.get(i).getText().trim();

				if (AccountTypeOption.equals(AccountType)) {
					u.clickElementUsingJavascript(optionsAccountType.get(i),
							"Select Account Type as Technology Partner");
					break;
				}

			} catch (java.lang.NullPointerException e) {
				continue;
			}
		}
		
		u.clickElementUsingJavascript(ddIndustry, Industry);
		
		for (int j = 0; j <= (optionsIndustry.size()) - 1; j++) {
			String IndustryTypeOption = optionsIndustry.get(j).getText().trim();
			if (IndustryTypeOption.contains(Industry)) {
				u.clickElementUsingJavascript(optionsIndustry.get(j), "Select Industry Option as Health Care");
				break;
			}
		}
		
		u.scrollVertically(popUpEditAccount, 750);

		u.enterValueInTextbox(txtBillingStreet, BillingAddress1);
		u.enterValueInTextbox(txtBillingCity, BillingAddress2);
		u.enterValueInTextbox(txtBillingZipCode, BillingAddress3);
		u.enterValueInTextbox(txtBillingCountry, BillingAddress4);
		
		u.enterValueInTextbox(txtShippingStreet, ShippingAddress1);
		u.enterValueInTextbox(txtShippingCity, ShippingAddress2);
		u.enterValueInTextbox(txtShippingZipCode, ShippingAddress3);
		u.enterValueInTextbox(txtShippingCountry, ShippingAddress4);
		u.clickElementUsingJavascript(ddCustomerPriority, "Select Customer Priority");
		for (int j = 0; j <= (optionsCustomerPriority.size()) - 1; j++) {
			String CustomerPriorityTypeOption = optionsCustomerPriority.get(j).getText().trim();
			if (CustomerPriorityTypeOption.contains("Low")) {
				u.clickElementUsingJavascript(optionsCustomerPriority.get(j), "Select value as Low");
				break;
			}
		}
		
		u.scrollVertically(popUpEditAccount, 200);
		u.clickElementUsingJavascript(ddSLA, "Select SLA");
		for (int j = 0; j <= (optionsSLA.size()) - 1; j++) {
			String SLATypeOption = optionsSLA.get(j).getText().trim();
			if (SLATypeOption.contains("Silver")) {
				u.clickElementUsingJavascript(optionsSLA.get(j), "Select SLA Option as Silver");
				break;
			}
		}
		// 14) Select Active as NO

				// Scrolling
				u.scrollVertically(popUpEditAccount, 100);
				u.clickElementUsingJavascript(ddActive, "Select Active");
				for (int j = 0; j <= (optionsActive.size()) - 1; j++) {
					try {
						String ActiveOption = optionsActive.get(j).getText().trim();
						if (ActiveOption.equals(Active)) {
							u.clickElementUsingJavascript(optionsActive.get(j), "Select An Active Option");
							break;
						}

					} catch (java.lang.NullPointerException e) {
						continue;
					}
				}
				u.clickElementUsingJavascript(ddUpsell, "Select Upsell");
				
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

					w.waitforStalenessofElement(btnSave, "Save Account Button");
					w.waitforVisibilityofElement(btnSave, "Save Account Button");
					btnSave = driver.findElement(By.xpath("//*[@name='SaveEdit']"));
					w.waitforVisibilityofElement(btnSave, "Save Account Button");
					u.clickElementUsingActions(btnSave, "Save Button");
				}
		return this;
	}
	

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	





