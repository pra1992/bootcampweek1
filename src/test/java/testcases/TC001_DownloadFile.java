package testcases;

import org.testng.annotations.Test;

import bootcamp3pom.pages.BaseClass;
import bootcamp3pom.pages.FilesPage;
import bootcamp3pom.pages.HomePage;
import bootcamp3pom.pages.LoginPage;

public class TC001_DownloadFile extends BaseClass {
	
	@Test(priority = 1 )
	public void runLogin() {
		new LoginPage(driver).enterUserName().enterPassword().clickLogin().verifyLoginIsSuccess();
	}
	
	@Test(priority = 2)
	public void navigateToFilesPage() {
		new HomePage(driver).clickToggle().verifyandclickViewAll().AppLaucherOption("service console");
		}
	
	@Test(priority=3)
	public void identifyLatestUploadedFile() {
		new FilesPage(driver).selectServiceConsoleOption().identifyRecentDate();
	}

	@Test(priority=4)
	public void downloadFile() {
		new FilesPage(driver).openRecentFile("Title").downloadFile().verifyFileDownload();
	}
}
