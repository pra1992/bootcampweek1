package bootcamp3pom.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import bootcamp3pom.utils.Utilities;

public class FilesPage extends BaseClass {


	public static String FileName = null;

	public static List<String> dateStrings = null;
	DateTime mostRecentDate = null;

	private static String DownloadedFileExtension = null;

	private static String DownloadedFileName = null;

	Robot robot;

	public FilesPage() {
		dateStrings = new ArrayList<String>();
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//*[@aria-label = 'Show Navigation Menu']")
	WebElement ddNavigationMenu;
	
	@FindBy(xpath = "//*[@aria-label = 'Show Navigation Menu']/following-sibling::section//li")
	List<WebElement> optionsSeviceConsole;
	
	@FindBy(xpath = "//table[@data-aura-class='uiVirtualDataTable']//thead//th[@title='Last Modified Date']")
	WebElement tblHeaderLastModified;
	
	@FindBy(xpath = "//table[@data-aura-class='uiVirtualDataTable']//thead//th[@title='Last Modified Date']/../../following-sibling::tbody//*[@class='slds-truncate uiOutputDateTime']")
	List<WebElement> dateLastModifiedDate;
	
	@FindBy(xpath = "//table[@data-aura-class='uiVirtualDataTable']//thead//th[@title='Title']/../../following-sibling::tbody//*[@class='itemTitle desktop outputTextOverride uiOutputText']")
	List<WebElement> colTitle;
	
	@FindBy(xpath = "//table[@data-aura-class='uiVirtualDataTable']//tbody//tr")
	List<WebElement> tblFiles;
	
	@FindBy(xpath = "(//*[@class='itemTitle desktop outputTextOverride uiOutputText'])")
	List<WebElement> tblFilesColumn;
	
	@FindBy(xpath = "//*[text()='Public Link']")
	WebElement linkPublic;
	
	@FindBy(xpath = "//*[text()='Expiration date']/..//*[@part='indicator']")
	WebElement toggleExpirationDate;
	
	@FindBy(xpath = "//*[@title='Create Link']")
	WebElement btnCreateLink;
	
	@FindBy(xpath = "//*[@title='Create']")
	WebElement btnCreate;
	
	@FindBy(xpath = "//*[@title='Copy Link']")
	WebElement btnCopyLink;
	
	@FindBy(xpath = "//lightning-input[contains(@class,'recordViewUrl ')]//input")
	WebElement txtReadOnlyPublicUrl;
	
	@FindBy(xpath = "//*[@title='Close this window']")
	WebElement btnClosePopup;
	
	@FindBy(xpath = "//*[text()='Download']")
	WebElement btnDownload;
	
	@FindBy(xpath = "//*[text()='Share']")
	WebElement btnShare;
	
	@FindBy(xpath= "//*[@title='Search People']")
	WebElement txtSearchPeople;
	
	@FindBy(xpath = "//h1[contains(text(),'People Results')]")
	WebElement modalPeopleResults;
	
	@FindBy(xpath = "//*[@data-aura-class='']//tbody//td")
	List<WebElement> tblpeopleResults;
	
	@FindBy(xpath = "//*[@class='form-element__help']")
	WebElement errorMessageCannotShare;
	
	@FindBy(xpath = "//*[@class='deleteIcon']")
	WebElement deleteShareWith;
	
	@FindBy(xpath = "//*[@placeholder='Add a message...']")
	WebElement txtAddMessage;
	
	@FindBy(xpath = "//*[@class='toastMsg uiOutputText forceActionsText']")
	WebElement successMessageShare;
	
	@FindBy(xpath = "//a[@title='Upload Files']")
	WebElement btnUpload;
	
	@FindBy(xpath= "//*[@data-aura-class='forceToastMessage']")
	WebElement messageSuccessUpload;
	
	@FindBy(xpath = "//*[@data-aura-class='forceContentPanelFooter']")
	WebElement messageFileUpload;
	
	@FindBy(xpath = "//*[text()='Done']")
	WebElement btnDone;
	
	@FindBy(xpath = "//table[@data-aura-class='uiVirtualDataTable']//thead//th[@title='Title']/../../following-sibling::tbody//a[@role='button']")
	List<WebElement> ddAction;
	
	@FindBy(xpath = "//*[contains(@class,'visible positioned')]//li")
	List<WebElement> listActionOptions;
	
	@FindBy(xpath = "//*[contains(@class,'entityNameTitle ')]/following-sibling::div")
	WebElement labelFileName;
	
	@FindBy(xpath = "//span[@title='File Extension']/following-sibling::div")
	WebElement lblFileExtension;
	
	@FindBy(xpath = "//button[contains(@title,\" + \"Close\" + FilesPage.FileName + \" )]")
	WebElement btnCloseTestCase;
	
	@FindBy(xpath = "//h1[text()='Delete File?']")
	WebElement hdrDeletePopup;
	
	@FindBy(xpath = "//button[@title='Delete']")
	WebElement btnDelete;
	
	@FindBy(xpath = "//*[@data-aura-class='forceActionsText']")
	WebElement messageSuccessDelete;
	

	public static String getDownloadedFileName() {
		return DownloadedFileName;

	}

	public static String getDownloadedFileExtension() {
		return DownloadedFileExtension;

	}
	// Select service console Option

	public FilesPage selectServiceConsoleOption() {
	
			u.clickElementUsingJavascript(ddNavigationMenu, "Service Console Dropdown");
			u.selectValueFromDropdown(optionsSeviceConsole, "Service Console", "Files");
		return this;
	}

	public int identifyRecentDate() {
		int rowCount = 0;
		try {
			for (int i = 0; i <= (dateLastModifiedDate.size()) - 1; i++) {
				dateStrings.add(dateLastModifiedDate.get(i).getText().trim());
			}
			for (int j = 0; j <= (dateStrings.size()) - 1; j++) {
				DateTime date = u.convertDateToSpecifiedFormat(dateStrings.get(j));
				Utilities.dates.add(date);
			}
			for (int i = 0; i <= (Utilities.dates.size()) - 1; i++) {
				if (mostRecentDate == null || Utilities.dates.get(i).isAfter(mostRecentDate)) {
					mostRecentDate = Utilities.dates.get(i);
					rowCount = i;
				}
			}

		} catch (java.lang.IndexOutOfBoundsException e) {
			Assertion.assertFail("Failed due to Index Out of bound" + e);
		} catch (Exception e) {
			Assertion.assertFail("Failed due to " + e);
		}
		return rowCount;

	}

	// Select the recent File and download

	public FilesPage openRecentFile(String Title) {
		int value = this.identifyRecentDate();
		try {
			for (int i = 0; i <= (colTitle.size()) - 1; i++) {
				if (colTitle.get(i).getText().trim().equals(Title)) {

					for (int j = 0; j <= (tblFiles.size()) - 1; j++) {
						if (j == value) {
							WebElement FileLink = getDriver()
									.findElement(By.xpath("//table[@data-aura-class='uiVirtualDataTable']//tbody//tr["
											+ j + "]//th[" + i + "]//a"));
							DownloadedFileExtension = getDriver()
									.findElement(By.xpath("//table[@data-aura-class='uiVirtualDataTable']//tbody//tr["
											+ j + "]//th[" + i + "]//a//span[@class='slds-assistive-text']"))
									.getText().trim();
							DownloadedFileName = getDriver().findElement(By.xpath(
									"(//*[@class='itemTitle desktop outputTextOverride uiOutputText'])[" + j + "]"))
									.getText().trim();
							u.clickElementUsingJavascript(FileLink, "File To be downloaded");

						}

					}
				}

			}
		} catch (Exception e) {
			Assertion.assertFail("Unable to download the file due to " + e);
		}
		return this;
	}
	
	public FilesPage downloadFile() {
		w.waitforVisibilityofElement(btnDownload, "Download File");
		u.clickElementUsingJavascript(btnDownload, "Download Button");
		return this;
	}

	// verify the file is downloaded in the project

	public FilesPage verifyFileDownload() {
		try {
			File file = new File(downloadDir, FilesPage.DownloadedFileName);
			if (file.exists() && file.isFile()) {
				Assertion.assertTrue(true, "File is successfully downloaded");
				reportStep("File is successfully downloaded", "pass");
			}
		} catch (Exception e) {
			Assertion.assertFail("File is not downloaded due to " + e);
			reportStep("File is not downloaded due to", "fail");
		}
		return this;
	}

	public boolean isFileAlreadyUploaded() {
		boolean flag = false;
		try {
			for (int i = 0; i <= (tblFilesColumn.size()) - 1; i++) {
				if (tblFilesColumn.get(i).getText().equals(property.getProperty("FileName"))) {
					flag = true;
					break;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	// Upload File

	public FilesPage uploadFile() throws AWTException, IOException {
		try {
			robot = new Robot();
			int Suffix = 1;
			String ModifiedFileName = null;
			String Path = property.getProperty("UploadFolder");
			if (Path != null && Path.isEmpty()) { // Check if UploadFolder is not empty or null
				u.clickElementUsingJavascript(btnUpload, "Upload New File");
				robot.delay(5000);
				String DesiredUploadFileName = property.getProperty("FileName");
				String UploadFilePath = BaseClass.ProjectRoot + File.separator + Path + File.separator
						+ DesiredUploadFileName;
				File file = new File(UploadFilePath, DesiredUploadFileName);
				while (file.exists() && this.isFileAlreadyUploaded() == true) {
					ModifiedFileName = DesiredUploadFileName + "_" + Suffix;
					UploadFilePath = BaseClass.ProjectRoot + File.separator + Path + File.separator + ModifiedFileName;
					file = new File(UploadFilePath);
					Suffix++;
				}
				StringSelection selection = new StringSelection(UploadFilePath);
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
			} else {
				// Handle the case where UploadFolder is empty or null
			}
		} catch (Exception e) {
			Assertion.assertFail("Unable to upload the file due to" + e);
		}
		return this;
	}
	
	public FilesPage verifyUploadIsSuccess() {
		try {
		u.verifyElementIsDisplayed(messageSuccessUpload, "Success Toaster Message");
		reportStep("Success Toaster Message", "pass");
		}catch(Exception e) {
	reportStep("Success Toaster Message is not displayed", "fail");		
		}
		return this;
	}

	// Clicking on Public Link
	public FilesPage clickPublicLink() {
		try {
			w.waitforVisibilityofElement(linkPublic, "Public Link");
			u.clickElementUsingJavascript(linkPublic, "Public Link");
		} catch (Exception e) {
			Assertion.assertFail("Unable to click pn Public Link due to " + e);
		}
			return this;
	}

	// Disable the toggle button
	public FilesPage disableToggleButton() {
		try {
			u.clickElementUsingJavascript(toggleExpirationDate, "Click on Toggle Button");
		} catch (Exception e) {
			Assertion.assertFail("Unable to select the Toggle Button due to " + e);
		}
		return this;
	}

	// verify the link is generated
	public FilesPage verifyLinkIsGenerated() {
		try {
			u.verifyElementIsDisplayed(txtReadOnlyPublicUrl, "Url is dislayed");
		} catch (Exception e) {
			Assertion.assertFail("Element is not displayed due to" + e);
		}
		
		u.clickElementUsingJavascript(btnClosePopup, "Create Public Link Popup");
		return this;
	}

	// Clicking on Sharing
	public FilesPage clickShare() {
		try {
			u.verifyElementIsDisplayed(btnShare, "Share Link");
			u.clickElementUsingJavascript(btnShare, "Share Link");
		} catch (Exception e) {
			Assertion.assertFail("Unable to click on Share due to " + e);
		}
		return this;
	}

	// Verify the error message
	public FilesPage veriFyErrorMessageWhileSharing() {
		try {
			u.enterValueInTextbox(txtSearchPeople, property.getProperty("InvalidSearchNameInSharing"));
			u.pressEnterInTextbox(txtSearchPeople);
			u.checkWindowHandles(OpenedWindows);
			w.waitforVisibilityofElement(modalPeopleResults, "Modal People Results");
			for (int i = 0; i <= (tblpeopleResults.size()) - 1; i++) {
				u.clickElementUsingJavascript(tblpeopleResults.get(i), "People Results");
				u.switchDefaultContent();
				w.waitforVisibilityofElement(errorMessageCannotShare, "Error Message");
				u.verifyExpectedTest(errorMessageCannotShare, "Error Message", "Can't share file with the file owner.");
				u.verifyandclickOnElement(btnClosePopup, DownloadedFileExtension);
			}
		} catch (Exception e) {
			Assertion.assertFail("Unable to search for Invalid User due to " + e);
		}
		
		return this;
	}

}
