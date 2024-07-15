package testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import bootcamp3pom.pages.AccountsPage;
import bootcamp3pom.pages.BaseClass;
import bootcamp3pom.pages.DashboardPage;
import bootcamp3pom.pages.HomePage;
import bootcamp3pom.pages.LoginPage;
import bootcamp3pom.pages.SellerHomePage;

public class TC004_CreateAccount extends BaseClass {
	
	@BeforeTest
	public void setData() {
		fileName1= "DataSheet"; 
	}
	
	@Test(priority = 1 )
	public void runLogin() {
		new LoginPage().enterUserName().enterPassword().clickLogin().verifyLoginIsSuccess();
	}
	
	
	
	@Test(priority = 2)
	public void navigateToSalesPage() {
		new HomePage().clickToggle().verifyandclickViewAll().AppLaucherOption("sales");
		}

	@Test(priority = 3)
	public void navigateToCreateAccountPage() {
		new DashboardPage().clickAccounts();
		}
	
	@Test(priority = 4)
	public void openCreateNewAccountPopup() {
		new SellerHomePage().clickNewButton().verifyCreateNewAccountPopupIsOpened();
		}
	
	@Test(priority = 5 , dataProvider = "getdata1")
	public void fillAccountDetailsAndClickSave(String AccountName, String AccountType) {
		new SellerHomePage().enterAccountDetailsInCreateNewAccountPopup(AccountName , AccountType).clickSaveButton();
		}
	
	@Test(priority = 6, dataProvider = "getdata1")
	public void verifyAccountCreationSuccesMessage(String AccountName) {
		new AccountsPage().verifyAccountCreationSuccessMessage(AccountName);
		}
	
	@Test(priority = 7 , dataProvider = "getdata1")
	public void searchForNewlyAddedAccountInTable(String AccountName) {
//		new SellerHomePage(getDriver()).searchForNewAccountInTable();
		new DashboardPage().clickAccounts().searchForNewAccountInTable(AccountName);
		}
	
	@Test(priority = 8,  dataProvider = "getdata1")
	public void verifyNewlyAddedAccountInAccountsTable(String AccountName) {
		new SellerHomePage().valideAddedAccountInTable(AccountName);
		}
}
