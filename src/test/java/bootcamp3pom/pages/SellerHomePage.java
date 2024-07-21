package bootcamp3pom.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SellerHomePage extends BaseClass {
	public SellerHomePage() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//*[@role='navigation']//*[@role='list']//one-app-nav-bar-item-root//a")
	List<WebElement> tabOptions;

	@FindBy(xpath = "//*[contains(@class,'forceActionLink')]//*[@title='New']")
	WebElement btnNew;

	@FindBy(xpath = "//h2[contains(text(),'New Account')]")
	WebElement titleNewAccount;

	@FindBy(xpath = "//*[@field-label='Account Name']//input")
	WebElement txtAccountName;

	@FindBy(xpath = "//button[contains(@aria-label,'Ownership')]")
	WebElement ddOwnership;

	@FindBy(xpath = "//lightning-base-combobox-item[@role='option']//*[@class='slds-media__body']")
	List<WebElement> OwnerShipOptions;
	@FindBy(xpath = "//*[@name='SaveEdit']")
	WebElement btnSave;

	@FindBy(xpath = "//*[@role='navigation']//*[@role='list']//one-app-nav-bar-item-root//a")
	List<WebElement> tabOption;

	@FindBy(xpath = "//*[@placeholder='Search this list...']")
	WebElement txtSearchAccount;

	@FindBy(xpath = "//*[@data-aura-class='forceActionsText']//a")
	WebElement txtMessage;
	@FindBy(xpath = "//*[@data-aura-class='forceVirtualAction']//a")
	WebElement ddActionDropdown;

	@FindBy(xpath = "//a[@title='Edit']")
	WebElement btnEdit;

	@FindBy(css = "div[data-aura-class = 'oneRecordActionWrapper']")
	WebElement popUpEditAccount;

	@FindBy(xpath = "//*[@field-label='Phone']//input")
	WebElement txtPhoneNo;
	@FindBy(xpath = "//button[contains(@aria-label,'Type')]")
	WebElement ddAccountType;

	@FindBy(xpath = "//label[text()='Type']/..//*[@class='slds-truncate']")
	List<WebElement> optionsAccountType;

	@FindBy(xpath = "//*[contains(@aria-label,'Industry')]")
	WebElement ddIndustry;

	@FindBy(xpath = "//label[text()='Industry']/..//*[@class='slds-truncate']")
	List<WebElement> optionsIndustry;
	@FindBy(xpath = "//*[text()='Billing Address']/..//textarea[@autocomplete='street-address']")
	WebElement txtBillingStreet;

	@FindBy(xpath = "//*[text()='Billing Address']/..//*[@name='city']")
	WebElement txtBillingCity;
	@FindBy(xpath = "//*[text()='Billing Address']/..//*[@name='postalCode']")
	WebElement txtBillingZipCode;
	@FindBy(xpath = "//*[text()='Billing Address']/..//*[@name='province']")
	WebElement txtBillingProvince;
	@FindBy(xpath = "//*[text()='Billing Address']/..//*[@name='province']")
	WebElement txtBillingCountry;
	@FindBy(xpath = "//*[text()='Shipping Address']/..//textarea[@autocomplete='street-address']")
	WebElement txtShippingStreet;
	@FindBy(xpath = "//*[text()='Shipping Address']/..//*[@name='city']")
	WebElement txtShippingCity;
	@FindBy(xpath = "//*[text()='Shipping Address']/..//*[@name='postalCode']")
	WebElement txtShippingZipCode;
	@FindBy(xpath = "//*[text()='Shipping Address']/..//*[@name='province']")
	WebElement txtShippingProvince;
	@FindBy(xpath = "//*[text()='Shipping Address']/..//*[@name='province']")
	WebElement txtShippingCountry;
	@FindBy(xpath = "//*[contains(@aria-label,'Customer Priority')]")
	WebElement ddCustomerPriority;

	@FindBy(xpath = "//*[contains(@aria-label,'SLA')]")
	WebElement ddSLA;

	@FindBy(xpath = "//label[text()='Customer Priority']/..//*[@class='slds-truncate']")
	List<WebElement> optionsCustomerPriority;
	@FindBy(xpath = "//label[text()='SLA']/..//*[@class='slds-truncate']")
	List<WebElement> optionsSLA;
	@FindBy(xpath = "//*[contains(@aria-label,'Active')]")
	WebElement ddActive;
	@FindBy(xpath = "\"//label[text()='Active']/..//*[@class='slds-truncate']")
	List<WebElement> optionsActive;
	@FindBy(xpath = "//*[contains(@aria-label,'Upsell Opportunity')]")
	WebElement ddUpsell;
	@FindBy(xpath = "//label[text()='Upsell Opportunity']/..//*[@class='slds-truncate']")
	List<WebElement> OptionsUpsell;

	@FindBy(xpath = "//*[contains(@class,'forceOutputPhone')]")
	WebElement gridPhoneNoValue;

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

	public AccountsPage verifyAccountCreationSuccessMessage(String AccountName) {
		w.waitforVisibilityofElement(txtMessage, "Account Creation Success Message");
		try {
		if (txtMessage.getText().trim().contains(AccountName)) {
			Assertion.assertTrue(true, "Account is added successful");
			reportStep("Account is added successfully", "pass");
		}}catch (Exception e) {
			reportStep("Account is not added", "fail");
		}
		return new AccountsPage();
	}

	public SellerHomePage searchForNewAccountInTable(String AccountName) {
		w.waitforVisibilityofElement(txtSearchAccount, "Search Account Text Box");
		txtSearchAccount.sendKeys(AccountName);
		txtSearchAccount.sendKeys(Keys.ENTER);
		for (int i = 0; i <= (getDriver().findElements(By.xpath("//table/tbody/tr"))).size() - 1; i++) {
			try {
				w.waitforVisibilityofAllElements(getDriver().findElements(By.xpath("//table/tbody/tr")),
						"Accounts Table");
			} catch (StaleElementReferenceException e) {
//				wait.until(ExpectedConditions.stalenessOf((getDriver().findElements(By.xpath("//table/tbody/tr"))).get(i)));
//				getDriver().findElements(By.xpath("//table/tbody/tr"));
//				wait.until(ExpectedConditions.visibilityOf((getDriver().findElements(By.xpath("//table/tbody/tr"))).get(i)));
				w.waitforPresenceofElement("//table/tbody/tr", "Accounts Table");

			} catch (org.openqa.selenium.TimeoutException e) {
				txtSearchAccount.clear();
				txtSearchAccount.sendKeys(AccountName);
				txtSearchAccount.sendKeys(Keys.ENTER);
			}

		}
		return this;
	}

	public SellerHomePage valideAddedAccountInTable(String AccountName) {
		for (int i = 0; i <= (getDriver().findElements(By.xpath("//table/tbody/tr"))).size() - 1; i++) {
			try {
				w.waitforVisibilityofAllElements(getDriver().findElements(By.xpath("//table/tbody/tr")),
						"Newly added Account in the row");
				if ((getDriver().findElements(By.xpath("//table/tbody/tr"))).get(i).getText().trim()
						.contains(AccountName)) {
					Assertion.assertTrue(true, "Account Name is verified in the Table");
					reportStep("Account name is verified in the Table", "pass");
				}
			} catch (StaleElementReferenceException e) {
				w.waitforStalenessofElement(getDriver().findElements(By.xpath("//table/tbody/tr")).get(i),
						"Newly added Account in the ro");
				getDriver().findElements(By.xpath("//table/tbody/tr"));
				if ((getDriver().findElements(By.xpath("//table/tbody/tr"))).get(i).getText().trim()
						.contains(AccountName)) {
					Assertion.assertTrue(true, "Account Name is verified in the Table");
				}
			} catch (TimeoutException e) {
				txtSearchAccount.clear();
				txtSearchAccount.sendKeys(AccountName);
				txtSearchAccount.sendKeys(Keys.ENTER);
				w.waitforVisibilityofAllElements(getDriver().findElements(By.xpath("//table/tbody/tr")),
						"Account Table");
				if ((getDriver().findElements(By.xpath("//table/tbody/tr"))).get(i).getText().trim()
						.contains(AccountName)) {
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

	public SellerHomePage editAccountDetailsPhoneNumber(String PhoneNo) {
		w.waitforVisibilityofElement(popUpEditAccount, "Edit Account Modal");
		u.enterValueInTextbox(txtPhoneNo, PhoneNo);
		return this;
	}

	public SellerHomePage editAccountType(String AccountType) {
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
		return this;
	}

	public SellerHomePage selectIndustry(String Industry) {
		u.clickElementUsingJavascript(ddIndustry, Industry);

		for (int j = 0; j <= (optionsIndustry.size()) - 1; j++) {
			String IndustryTypeOption = optionsIndustry.get(j).getText().trim();
			if (IndustryTypeOption.contains(Industry)) {
				u.clickElementUsingJavascript(optionsIndustry.get(j), "Select Industry Option as Health Care");
				break;
			}
		}
		return this;

	}

	public SellerHomePage enterBillingDetails(String BillingAddress1, String BillingAddress2, String BillingAddress3,
			String BillingAddress4) {
		u.scrollVertically(popUpEditAccount, 750);

		u.enterValueInTextbox(txtBillingStreet, BillingAddress1);
		u.enterValueInTextbox(txtBillingCity, BillingAddress2);
		u.enterValueInTextbox(txtBillingZipCode, BillingAddress3);
		u.enterValueInTextbox(txtBillingCountry, BillingAddress4);
		return this;
	}

	public SellerHomePage enterShippingDetails(String ShippingAddress1, String ShippingAddress2,
			String ShippingAddress3, String ShippingAddress4) {
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
		return this;
	}

	public SellerHomePage selectSLA(String SLA) {
		u.scrollVertically(popUpEditAccount, 200);
		u.clickElementUsingJavascript(ddSLA, "Select SLA");
		for (int j = 0; j <= (optionsSLA.size()) - 1; j++) {
			String SLATypeOption = optionsSLA.get(j).getText().trim();
			if (SLATypeOption.contains("Silver")) {
				u.clickElementUsingJavascript(optionsSLA.get(j), "Select SLA Option as Silver");
				break;
			}
		}
		return this;
	}

	// 14) Select Active as NO
	public SellerHomePage selectActiveStatus(String Active) {
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
		return this;
	}

	public SellerHomePage selectUpsell() {
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
		return this;
	}

	// Clicking on Save Button
	public SellerHomePage clickSave() {
				try {
					u.clickElementUsingActions(btnSave, "Save Button");
				} catch (org.openqa.selenium.StaleElementReferenceException e) {

					w.waitforStalenessofElement(btnSave, "Save Account Button");
					w.waitforVisibilityofElement(btnSave, "Save Account Button");
					btnSave = getDriver().findElement(By.xpath("//*[@name='SaveEdit']"));
					w.waitforVisibilityofElement(btnSave, "Save Account Button");
					u.clickElementUsingActions(btnSave, "Save Button");
				}
		return this;
	}

	// Verify the Success Toaster message

	public SellerHomePage verifyEditSuccessToasterMessage() {
		try {
		if (getDriver().findElement(By.xpath("//*[@data-aura-class='forceActionsText']")).getText().trim()
				.contains("saved")) {
			System.out.println("Account is saved successful");
			Assertion.assertSuccessMessage(true, "Account is saved successful");
			reportStep("Account Edit message is displayed", "pass");
		}
		
	}catch (Exception e) {
		reportStep("Account Edit Step is not displayed", "fail");}
	return this;
	}

	// Verifying the added Phone Number in the Grid
	public SellerHomePage verifyTheEditedPhoneNoInTheGrid(String PhoneNo) {
		try {
		if (gridPhoneNoValue.getText().trim().equals(PhoneNo)) {
			System.out.println("Phone Number is verified Successfully");
			Assertion.assertSuccessMessage(true, "Phone Number is verified Successfully");
			reportStep("Phone Number is verified Successfully", "pass");
		}
		
	}catch(Exception e) {
		reportStep("Phone Number is not verified", "fail");
	}
	return this;
}
	
	
	
	
	
}
