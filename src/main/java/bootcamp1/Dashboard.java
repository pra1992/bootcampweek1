package bootcamp1;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Dashboard {

	public static void main(String[] args) {
		// **********Launching the Browser******************//
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		WebDriver driver = new ChromeDriver(options);
		// Declaring Java Script Executor
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Deleting the Cookies
		driver.manage().deleteAllCookies();
		// Launching the Url//
		driver.get("https://login.salesforce.com/");
		// maximizing the window and setting up implicit wait
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		// Declaring Actions Instance
		Actions actions = new Actions(driver);
		// Explicit Wait Declaration
		WebDriverWait wait;
		Random random;
		String WindowHandle = driver.getWindowHandle();
		String DashboardName = "YourName_Workout";

		// Locating all the elements in the login page

		WebElement txtUserName = driver.findElement(By.xpath("//*[@id='username']"));

		WebElement txtPassword = driver.findElement(By.xpath("//*[@id='password']"));

		WebElement btnLogin = driver.findElement(By.xpath("//*[@id='Login']"));

		// ******Entering the credentials and clicking on login Button*******//
		txtUserName.sendKeys("bootcamp_2024@testleaf.com");
		txtPassword.sendKeys("Bootcamp@123");
		btnLogin.click();
		// Verifying the title of the login page
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.titleContains("Home | Salesforce"));
		// Verify login is successful
		if (driver.getTitle().contains("Home | Salesforce")) {
			System.out.println("Login is Successful");
		}

		// **********Identifying the elements in the Home| Salesforce page*******//
		WebElement btnAppLauncher = driver.findElement(By.xpath("//*[@title='App Launcher']"));
		// ****Clicking on App Launcher**********//
		js.executeScript("arguments[0].click();", btnAppLauncher);

		// ViewAll Button
		WebElement btnViewAll = driver.findElement(By.xpath("//*[@kx-type='underline']"));
		// Clicking the View All Button
		js.executeScript("arguments[0].click();", btnViewAll);
		// ********Checking for the Window Handles*********//

		// Switching to the App Launcher Window
		Set<String> OpenedWindows = driver.getWindowHandles();
		for (String OpenWindow : OpenedWindows) {
			if (!OpenWindow.equals(WindowHandle)) {
				driver.switchTo().window(OpenWindow);
			}
		}

		// Identifying the Sales Locator and clicking on Sales
		WebElement btnSales = driver.findElement(By.xpath(
				"//*[@data-name='Sales']//*[contains(text(),'Manage your sales process with accounts, leads, opportunities, and more')]"));

		actions.moveToElement(btnSales).click().build().perform();

		wait.until(ExpectedConditions.titleContains("Home | Salesforce"));

		// Checking for the Window Handles

		Set<String> NewOpenedWindows = driver.getWindowHandles();
		for (String OpenWindow : NewOpenedWindows) {
			if (!OpenWindow.equals(WindowHandle)) {
				driver.switchTo().window(OpenWindow);
			}
		}

		// Setting up the monthly Goals//

		WebElement btnEditGoals = driver.findElement(By.xpath("//*[@aria-label='Edit my goals']"));
		actions.moveToElement(btnEditGoals).click().build().perform();
		random = new Random();
		int min = 20;
		int max = 50;

		int RandomNumber = random.nextInt(max - min + 1) + min;
		String RandomValue = Integer.toString(RandomNumber);

		// Identifying all the webElements within Set Goals widget and entering the
		// values and setting up the GOAL//
		WebElement txtMeetings = driver.findElement(By.xpath("//*[@placeholder='Number of meetings']"));
		WebElement txtCalls = driver.findElement(By.xpath("//*[@placeholder='Number of calls']"));
		WebElement txtEmails = driver.findElement(By.xpath("//*[@placeholder='Number of emails']"));
		WebElement btnSave = driver.findElement(By.xpath("//*[@title='Save']"));
		actions.moveToElement(txtMeetings).sendKeys(RandomValue).build().perform();
		actions.moveToElement(txtCalls).sendKeys(RandomValue).build().perform();
		actions.moveToElement(txtEmails).sendKeys(RandomValue).build().perform();
		actions.moveToElement(btnSave).click().build().perform();

		// Navigating through the sales option and clicking on Dashboards
		List<WebElement> tabOptions = driver
				.findElements(By.xpath("//*[@role='navigation']//*[@role='list']//one-app-nav-bar-item-root//a"));
		for (int i = 0; i <= (tabOptions.size()) - 1; i++) {
			String TabName = tabOptions.get(i).getAttribute("title").trim();
			if (TabName.equals("Dashboards")) {
				js.executeScript("arguments[0].click();", tabOptions.get(i));
				break;
			}
		}

		// ****Identifying all the web elements in the Dashboards|Salesforce*******//
		WebElement linkNewDashBoard = driver.findElement(By.xpath("//a[@title='New Dashboard']"));
		WebElement txtSearchDashboard = driver.findElement(By.xpath("//*[@placeholder='Search recent dashboards...']"));
		js.executeScript("arguments[0].click();", linkNewDashBoard);

		// ****Checking for the Opened Windoes*****//
		// Checking for the Window Handles

		Set<String> CurrentOpenedWindows = driver.getWindowHandles();
		for (String OpenWindow : CurrentOpenedWindows) {
			if (!OpenWindow.equals(WindowHandle)) {
				driver.switchTo().window(OpenWindow);
			}
		}

		// ********Filling all the Details in the New Dashboard********//

		WebElement txtName = driver.findElement(By.xpath("//*[@id='dashboardNameInput']"));
		WebElement txtDescription = driver.findElement(By.xpath("//*[@id='dashboardDescriptionInput']"));
		WebElement btnCreateBtn = driver.findElement(By.xpath("//*[@id='submitBtn']"));
		actions.moveToElement(txtName).sendKeys(DashboardName).build().perform();
		actions.moveToElement(txtDescription).sendKeys("Testing").build().perform();
		actions.moveToElement(btnCreateBtn).click().build().perform();

		// Navigating through the sales option and clicking on Dashboards//
		for (int i = 0; i <= (tabOptions.size()) - 1; i++) {
			String TabName = tabOptions.get(i).getAttribute("title").trim();
			if (TabName.equals("Dashboards")) {
				js.executeScript("arguments[0].click();", tabOptions.get(i));
				break;
			}
		}

		// *********Searching for the Dashboard and Opening the Added Dashboard *****//

		wait.until(ExpectedConditions.visibilityOf(txtSearchDashboard));
		actions.moveToElement(txtSearchDashboard).sendKeys(DashboardName).build().perform();
		txtSearchDashboard.sendKeys(Keys.ENTER);

		List<WebElement> valuesDashBoardTable = driver.findElements(By.xpath("//table//tbody//tr//a"));
		for (int i = 0; i <= (valuesDashBoardTable.size()) - 1; i++) {
			String ColValue = valuesDashBoardTable.get(i).getText().trim();
			if (ColValue.equals(DashboardName)) {
				js.executeScript("arguments[0].click();", valuesDashBoardTable.get(i));
			}
		}

		// Subscribing the newly created Dashboard//

		wait.until(ExpectedConditions.titleContains(DashboardName));
		WebElement btnSubscribe = driver.findElement(By.xpath("//button[contains(text(),'Subscribe')]"));
		js.executeScript("arguments[0].click();", btnSubscribe);

		// Checking for the Window Handles//

		Set<String> Windows = driver.getWindowHandles();
		for (String OpenWindow : Windows) {
			if (!OpenWindow.equals(WindowHandle)) {
				driver.switchTo().window(OpenWindow);
			}
		}

		// *************** Editing the Subscription*******************//
		// Adding Daily Subscription//
		WebElement btnDaily = driver.findElement(By.xpath("//*[text()='Daily']"));
		js.executeScript("arguments[0].click()", btnDaily);

		// Selecting the Time//

		WebElement ddSelectTime = driver.findElement(By.xpath("//*[@id='time']"));
		Select select = new Select(ddSelectTime);
		select.selectByValue(driver.findElement(By.xpath("//option[text()='10:00 am']")).getText().trim());

		// *******Clicking on Save Button at the bottom******//
		WebElement btnSaveBottom = driver
				.findElement(By.xpath("//*[@class='modal-footer slds-modal__footer']//*[text()='Save']"));
		js.executeScript("arguments[0].click();", btnSaveBottom);

		// **********Verifying the success message in the toaster***********//

		WebElement txtSuccessMessage = driver.findElement(By.xpath("//*[@data-aura-class='forceActionsText']"));
		if (txtSuccessMessage.getText().trim().contains("Your subscription is all set.")) {
			System.out.println("Subscription is added Successfully");
		}

		// ***********Clicking on Dash board Tab******************//

		for (int i = 0; i <= (tabOptions.size()) - 1; i++) {
			String TabName = tabOptions.get(i).getAttribute("title").trim();
			if (TabName.equals("Dashboards")) {
				js.executeScript("arguments[0].click();", tabOptions.get(i));
				break;
			}
			// *********Searching for the Dashboard and Opening the Added Dashboard *****//

			wait.until(ExpectedConditions.visibilityOf(txtSearchDashboard));
			actions.moveToElement(txtSearchDashboard).sendKeys(DashboardName).build().perform();
			txtSearchDashboard.sendKeys(Keys.ENTER);

			// *******Verify the newly created Dashboard is available***********//

			List<WebElement> ValueDashBoardTable = driver.findElements(By.xpath("//table//tbody//tr//a"));
			for (int x = 0; i <= (ValueDashBoardTable.size()) - 1; x++) {
				String ColValue = ValueDashBoardTable.get(x).getText().trim();
				if (ColValue.equals(DashboardName)) {
					System.out.println("Newly added Dashboard is present in the Table");
					js.executeScript("arguments[0].click();", ValueDashBoardTable.get(x));
				}

			}
		}

		// ***Clicking on Dropdown from the Item********//
		WebElement ddDashboardAction = driver.findElement(By.xpath("//*[@data-action-triggers='enter,space']"));
		js.executeScript("arguments[0].click()", ddDashboardAction);

		// **********Selecting and Confirming the Delete*******//
		WebElement linkDeleteAction = driver.findElement(By.xpath("//*[text()='Delete']"));
		js.executeScript("arguments[0].click()", linkDeleteAction);

		WebElement hdrDeleteDashboardPopup = driver.findElement(By.xpath("//*[text()='Delete Dashboard']"));

		wait.until(ExpectedConditions.visibilityOf(hdrDeleteDashboardPopup));
		WebElement btnDeleteDashboard = driver.findElement(By.xpath("//*[@title='Delete']"));
		js.executeScript("arguments[0].click()", btnDeleteDashboard);

		WebElement txtToastMessage = driver.findElement(By.xpath("//*[@data-aura-class='forceActionsText']"));
		if (txtToastMessage.getText().contains("Dashboard was Deleted")) {

			System.out.println("Dashboard is deleted successfully ");
		}

		// Searching for the Deleted Dashboard//

		wait.until(ExpectedConditions.visibilityOf(txtSearchDashboard));
		actions.moveToElement(txtSearchDashboard).sendKeys(DashboardName).build().perform();
		txtSearchDashboard.sendKeys(Keys.ENTER);
		WebElement spanEmptyDashBoardBody = driver.findElement(By.xpath("//*[contains(@class,'emptyMessageTitle')]"));
		if (spanEmptyDashBoardBody.getText().contains("No results found")) {
			System.out.println("Item is not available under Private Dashboard folder");

		}

	}

}
