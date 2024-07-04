package bootcamp3pom;

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
import org.testng.Assert;

import bootcamp2testng.Assertion;
import bootcamp2testng.BaseClass;
import bootcamp2testng.Utilities;

public class FilesPage extends BaseClass {

	private WebDriver driver;

	public static String FileName = null;

	public static List<String> dateStrings = null;
	DateTime mostRecentDate = null;

	private static String DownloadedFileExtension = null;

	private static String DownloadedFileName = null;

	Robot robot;

	public FilesPage(WebDriver driver) {
		this.driver = driver;
		dateStrings = new ArrayList<String>();
	}

	WebElement ddNavigationMenu = driver.findElement(By.xpath("//*[@aria-label = 'Show Navigation Menu']"));

	WebElement tblHeaderLastModified = driver.findElement(
			By.xpath("//table[@data-aura-class='uiVirtualDataTable']//thead//th[@title='Last Modified Date']"));

	List<WebElement> dateLastModifiedDate = driver.findElements(By.xpath(
			"//table[@data-aura-class='uiVirtualDataTable']//thead//th[@title='Last Modified Date']/../../following-sibling::tbody//*[@class='slds-truncate uiOutputDateTime']"));

	List<WebElement> colTitle = driver.findElements(By.xpath(
			"//table[@data-aura-class='uiVirtualDataTable']//thead//th[@title='Title']/../../following-sibling::tbody//*[@class='itemTitle desktop outputTextOverride uiOutputText']"));

	List<WebElement> tblFiles = driver
			.findElements(By.xpath("//table[@data-aura-class='uiVirtualDataTable']//tbody//tr"));

	List<WebElement> tblFilesColumn = driver
			.findElements(By.xpath("(//*[@class='itemTitle desktop outputTextOverride uiOutputText'])"));
	WebElement linkPublic = driver.findElement(By.xpath("//*[text()='Public Link']"));

	WebElement toggleExpirationDate = driver
			.findElement(By.xpath("//*[text()='Expiration date']/..//*[@part='indicator']"));

	WebElement btnCreateLink = driver.findElement(By.xpath("//*[@title='Create Link']"));

	WebElement btnCreate = driver.findElement(By.xpath("//*[@title='Create']"));

	WebElement btnCopyLink = driver.findElement(By.xpath("//*[@title='Copy Link']"));

	WebElement txtReadOnlyPublicUrl = driver
			.findElement(By.xpath("//lightning-input[contains(@class,'recordViewUrl ')]//input"));

	WebElement btnClosePopup = driver.findElement(By.xpath("//*[@title='Close this window']"));

	WebElement btnDownload = driver.findElement(By.xpath("//*[text()='Download']"));

	WebElement btnShare = driver.findElement(By.xpath("//*[text()='Share']"));

	WebElement txtSearchPeople = driver.findElement(By.xpath("//*[@title='Search People']"));

	WebElement modalPeopleResults = driver.findElement(By.xpath("//h1[contains(text(),'People Results')]"));
	List<WebElement> tblpeopleResults = driver.findElements(By.xpath("//*[@data-aura-class='']//tbody//td"));

	WebElement errorMessageCannotShare = driver.findElement(By.xpath("//*[@class='form-element__help']"));

	WebElement deleteShareWith = driver.findElement(By.xpath("//*[@class='deleteIcon']"));

	WebElement txtAddMessage = driver.findElement(By.xpath("//*[@placeholder='Add a message...']"));

	WebElement successMessageShare = driver
			.findElement(By.xpath("//*[@class='toastMsg uiOutputText forceActionsText']"));

	WebElement btnUpload = driver.findElement(By.xpath("//a[@title='Upload Files']"));

	WebElement messageSuccessUpload = driver.findElement(By.xpath("//*[@data-aura-class='forceToastMessage']"));

	WebElement messageFileUpload = driver.findElement(By.xpath("//*[@data-aura-class='forceContentPanelFooter']"));

	WebElement btnDone = driver.findElement(By.xpath("//*[text()='Done']"));

	List<WebElement> ddAction = driver.findElements(By.xpath(
			"//table[@data-aura-class='uiVirtualDataTable']//thead//th[@title='Title']/../../following-sibling::tbody//a[@role='button']"));

	List<WebElement> listActionOptions = driver
			.findElements(By.xpath("//*[contains(@class,'visible positioned')]//li"));

	WebElement labelFileName = driver
			.findElement(By.xpath("//*[contains(@class,'entityNameTitle ')]/following-sibling::div"));

	WebElement lblFileExtension = driver
			.findElement(By.xpath("//span[@title='File Extension']/following-sibling::div"));

	WebElement btnCloseTestCase = driver
			.findElement(By.xpath("//button[contains(@title," + "Close" + FilesPage.FileName + " )]"));

	WebElement hdrDeletePopup = driver.findElement(By.xpath("//h1[text()='Delete File?']"));

	WebElement btnDelete = driver.findElement(By.xpath("//button[@title='Delete']"));

	WebElement messageSuccessDelete = driver.findElement(By.xpath("//*[@data-aura-class='forceActionsText']"));

	public static String getDownloadedFileName() {
		return DownloadedFileName;

	}

	public static String getDownloadedFileExtension() {
		return DownloadedFileExtension;

	}
	// Select service console Option

	public void selectServiceConsoleOption(List<WebElement> Options, String Serviceconsole, String Value) {
		switch (Serviceconsole.toLowerCase()) {
		case "file":
			u.clickElementUsingJavascript(ddNavigationMenu, Serviceconsole);
			u.selectValueFromDropdown(Options, Serviceconsole, Value);
			break;
		}
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

	public void downloadRecentFile(String Title) {
		int value = this.identifyRecentDate();
		try {
			for (int i = 0; i <= (colTitle.size()) - 1; i++) {
				if (colTitle.get(i).getText().trim().equals(Title)) {

					for (int j = 0; j <= (tblFiles.size()) - 1; j++) {
						if (j == value) {
							WebElement FileLink = driver
									.findElement(By.xpath("//table[@data-aura-class='uiVirtualDataTable']//tbody//tr["
											+ j + "]//th[" + i + "]//a"));
							DownloadedFileExtension = driver
									.findElement(By.xpath("//table[@data-aura-class='uiVirtualDataTable']//tbody//tr["
											+ j + "]//th[" + i + "]//a//span[@class='slds-assistive-text']"))
									.getText().trim();
							DownloadedFileName = driver.findElement(By.xpath(
									"(//*[@class='itemTitle desktop outputTextOverride uiOutputText'])[" + j + "]"))
									.getText().trim();
							u.clickElementUsingJavascript(FileLink, "File To be downloaded");
							w.waitforVisibilityofElement(btnDownload, "Download File");
							u.clickElementUsingJavascript(btnDownload, "Download Button");

						}

					}
				}

			}
		} catch (Exception e) {
			Assertion.assertFail("Unable to download the file due to " + e);
		}
	}

	// verify the file is downloaded in the project

	public void verifyFileDownload() {
		try {
			File file = new File(downloadDir, FilesPage.DownloadedFileName);
			if (file.exists() && file.isFile()) {
				Assertion.assertTrue(true, "File is successfully downloaded");
			}
		} catch (Exception e) {
			Assertion.assertFail("File is not downloaded sue to " + e);
		}
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

	public void uploadFile() throws AWTException, IOException {
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
	}

	// Clicking on Public Link
	public void clickPublicLink() {
		try {
			u.clickElementUsingJavascript(linkPublic, DownloadedFileExtension);
		} catch (Exception e) {
			Assertion.assertFail("Unable to click pn Public Link due to " + e);
		}
	}

	// Disable the toggle button
	public void disableToggleButton() {
		try {
			u.clickElementUsingJavascript(toggleExpirationDate, "Click on Toggle Button");
		} catch (Exception e) {
			Assertion.assertFail("Unable to select the Toggle Button due to " + e);
		}
	}

	// verify the link is generated
	public void verifyLinkIsGenerated() {
		try {
			u.verifyElementIsDisplayed(txtReadOnlyPublicUrl, "Url is dislayed");
		} catch (Exception e) {
			Assertion.assertFail("Element is not displayed due to" + e);
		}
	}

	// Clicking on Sharing
	public void clickShare() {
		try {
			u.verifyElementIsDisplayed(btnShare, "Share Link");
			u.clickElementUsingJavascript(btnShare, "Share Link");
		} catch (Exception e) {
			Assertion.assertFail("Unable to click on Share due to " + e);
		}
	}

	// Verify the error message
	public void veriFyErrorMessageWhileSharing() {
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
	}

}
