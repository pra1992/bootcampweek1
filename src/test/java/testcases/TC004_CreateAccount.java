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
		new LoginPage(driver).enterUserName().enterPassword().clickLogin().verifyLoginIsSuccess();
	}
	
	
	
	@Test(priority = 2)
	public void navigateToSalesPage() {
		new HomePage(driver).clickToggle().verifyandclickViewAll().AppLaucherOption("sales");
		}

	@Test(priority = 3)
	public void navigateToCreateAccountPage() {
		new DashboardPage(driver).clickAccounts();
		}
	
	@Test(priority = 4)
	public void openCreateNewAccountPopup() {
		new SellerHomePage(driver).clickNewButton().verifyCreateNewAccountPopupIsOpened();
		}
	
	@Test(priority = 5 , dataProvider = "getdata1")
	public void fillAccountDetailsAndClickSave(String AccountName, String AccountType) {
		new SellerHomePage(driver).enterAccountDetailsInCreateNewAccountPopup(AccountName , AccountType).clickSaveButton();
		}
	
	@Test(priority = 6)
	public void verifyAccountCreationSuccesMessage() {
		new AccountsPage(driver).verifyAccountCreationSuccessMessage();
		}
	
	@Test(priority = 7 , dataProvider = "getdata1")
	public void searchForNewlyAddedAccountInTable(String AccountName, String AccountType) {
//		new SellerHomePage(driver).searchForNewAccountInTable();
		new DashboardPage(driver).clickAccounts().searchForNewAccountInTable(AccountName, AccountType);
		}
	
	@Test(priority = 8,  dataProvider = "getdata1")
	public void verifyNewlyAddedAccountInAccountsTable(String AccountName, String AccountType) {
		new SellerHomePage(driver).valideAddedAccountInTable(AccountName, AccountType );
		}
}
