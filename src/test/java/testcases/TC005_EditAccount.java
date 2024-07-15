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
		new LoginPage().enterUserName().enterPassword().clickLogin().verifyLoginIsSuccess();
	}
	
	
	
	@Test(priority = 2)
	public void navigateToSalesPage() {
		new HomePage().clickToggle().verifyandclickViewAll().AppLaucherOption("sales");
		}

	@Test(priority = 3 )
	public void navigateToCreateAccountPage() {
		new DashboardPage().clickAccounts();
		}
	

	@Test(priority = 4 , dataProvider = "getdata1")
	public void searchForTheNewlyAddedAccount(String AccountName) {
		new DashboardPage().clickAccounts().searchForNewAccountInTable(AccountName);
		}
	
	@Test(priority = 5 )
	public void selectActionFromAccountGrid(String EditAccount)
{
		new SellerHomePage().clickAction(EditAccount).clickEdit();
		}
	
	@Test(priority = 6, dataProvider = "getdata2" )
	public void editPhoneNo(String PhoneNo) {
		new SellerHomePage().editAccountDetailsPhoneNumber(PhoneNo);
	}
	
	@Test(priority = 7, dataProvider = "getdata2" )
	public void editAccountType(String AccountType) {
		new SellerHomePage().editAccountType(AccountType);
	}
	
	@Test(priority = 8, dataProvider = "getdata2" )
	public void selectIndustry(String Industry) {
		new SellerHomePage().selectIndustry(Industry);
	}
	
	@Test(priority = 9	, dataProvider = "getdata2" )
	public void enterBillingDetails(String BillingAddress1, String BillingAddress2, String BillingAddress3,String BillingAddress4) {
		new SellerHomePage().enterBillingDetails(BillingAddress1, BillingAddress2, BillingAddress3, BillingAddress4);
		
	}
	
	@Test(priority = 10	, dataProvider = "getdata2" )
	public void enterShippingDetails(String ShippingAddress1, String ShippingAddress2, String ShippingAddress3, String ShippingAddress4) {
		new SellerHomePage().enterBillingDetails(ShippingAddress1, ShippingAddress2, ShippingAddress3, ShippingAddress4);
		
	}
	@Test(priority = 11, dataProvider = "getdata2" )
	public void selectSLA(String SLA) {
		new SellerHomePage().selectSLA(SLA);
	}
	
	@Test(priority = 12, dataProvider = "getdata2" )
	public void selectActiveStatus(String Active) {
		new SellerHomePage().selectActiveStatus(Active);
	}
	
	@Test(priority = 13, dataProvider = "getdata2" )
	public void selectUpsell(String Upsell) {
		new SellerHomePage().selectActiveStatus(Upsell);
	}
	
	@Test(priority = 14 )
	public void clickSaveAndVerifySuccessMessage() {
		new SellerHomePage().clickSave().verifyEditSuccessToasterMessage();
	}
	
	@Test(priority = 15 , dataProvider = "getdata2" )
	public void verifyPhoneNoInTheGrid(String PhoneNo) {
		new SellerHomePage().verifyTheEditedPhoneNoInTheGrid(PhoneNo);
	}
	
	
	

}
