package testcases;

import org.testng.annotations.Test;

import bootcamp3pom.pages.BaseClass;
import bootcamp3pom.pages.FilesPage;
import bootcamp3pom.pages.HomePage;
import bootcamp3pom.pages.LoginPage;

public class TC003_OtherFileOperations extends BaseClass{

	@Test(priority = 1 )
	public void runLogin() {
		new LoginPage().enterUserName().enterPassword().clickLogin().verifyLoginIsSuccess();	
	}
	
	
	@Test(priority = 2)
	public void navigateToFilesPage() {
		new HomePage().clickToggle().verifyandclickViewAll().AppLaucherOption("service console");
		}
	@Test(priority=3)
	public void openRecentFile() {
		new FilesPage().openRecentFile("Title");
	}
	
	@Test(priority=4)
	public void generateAndVerifyUrl() {
		new FilesPage().openRecentFile("Title").clickPublicLink().disableToggleButton().verifyLinkIsGenerated();
	}
	
	@Test(priority=5)
	public void shareFile() {
		new FilesPage().openRecentFile("Title").clickShare().veriFyErrorMessageWhileSharing();
	}
}
