package testcases;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.Test;

import bootcamp3pom.pages.BaseClass;
import bootcamp3pom.pages.FilesPage;
import bootcamp3pom.pages.HomePage;
import bootcamp3pom.pages.LoginPage;

public class TC002_FileUpload extends BaseClass {

	@Test(priority = 1 )
	public void runLogin() {
		new LoginPage(driver).enterUserName().enterPassword().clickLogin().verifyLoginIsSuccess();	
	}
	
	
	@Test(priority = 2)
	public void navigateToFilesPage() {
		new HomePage(driver).clickToggle().verifyandclickViewAll().AppLaucherOption("service console");
		}
	
	
	@Test(priority = 3)
	public void verifyIsFileAlreadyUploaded() {
		new FilesPage(driver).isFileAlreadyUploaded();
	}
	
	@Test(priority = 4)
	public void uploadFileAndVerify() throws AWTException, IOException {
		new FilesPage(driver).uploadFile().verifyFileDownload();
	}
	
}
