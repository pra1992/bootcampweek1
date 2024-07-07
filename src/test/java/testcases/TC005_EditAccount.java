package testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import bootcamp3pom.pages.BaseClass;
import bootcamp3pom.pages.DashboardPage;
import bootcamp3pom.pages.HomePage;
import bootcamp3pom.pages.LoginPage;
import bootcamp3pom.pages.SellerHomePage;

public class TC005_EditAccount extends BaseClass {

	
	@Test(priority = 1 )
	public void runLogin() {
		new LoginPage(driver).enterUserName().enterPassword().clickLogin().verifyLoginIsSuccess();
	}
	
	
	
	@Test(priority = 2)
	public void navigateToSalesPage() {
		new HomePage(driver).clickToggle().verifyandclickViewAll().AppLaucherOption("sales");
		}

	@Test(priority = 3 )
	public void navigateToCreateAccountPage() {
		new DashboardPage(driver).clickAccounts();
		}
	

	@Test(priority = 4 , dataProvider = "getdata1")
	public void searchForTheNewlyAddedAccount(String AccountName, String AccountType) {
		new DashboardPage(driver).clickAccounts().searchForNewAccountInTable(AccountName, AccountType);
		}
	
	@Test(priority = 5 )
	public void selectActionFromAccountGrid(String EditAccount)
{
		new SellerHomePage(driver).clickAction(EditAccount).clickEdit();
		}
	
	@Test(priority = 6, dataProvider = "getdata2" )
	public void editAccountDetailsAndSave(String PhoneNo, String AccountType, String Industry, String BillingAddress1, String BillingAddress2, String BillingAddress3,
			String BillingAddress4, String ShippingAddress1, String ShippingAddress2, String ShippingAddress3,
			String ShippingAddress4, String Active)
{
		new SellerHomePage(driver).
		}
	
	
	
	

}
