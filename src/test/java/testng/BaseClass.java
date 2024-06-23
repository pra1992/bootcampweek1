package testng;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

public class BaseClass {

	public static WebDriver driver;
	Actions actions;
	WebDriverWait wait;
	String WindowHandle = null;
	Set<String> OpenedWindows = null;
	// Declaring Java Script Executor
	JavascriptExecutor js = (JavascriptExecutor) driver;
	String AccountName = "Prasanth Sankaran";
	String PhoneNo = "75023687";
	SoftAssert a = new SoftAssert();
	Utilities u = new Utilities();
	WaitUtils w = new WaitUtils();
	String Folder = System.getProperty("user.dir") + "/Images";

	@Parameters({ "url", "username", "password" })
	@BeforeMethod
	public void setUp(String url, String username, String password) {

		// ****Creation of WebDriver Object****//
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

		driver = new ChromeDriver(options);
		// Deleting the Cookies
		driver.manage().deleteAllCookies();
		// Launching the Url//
		driver.get(url);
		// maximizing the window and setting up implicit wait
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		// Window Handle

		WindowHandle = driver.getWindowHandle();
		OpenedWindows = driver.getWindowHandles();
		// Declaring Actions Instance
		actions = new Actions(driver);

		// Locating all the elements in the login page

		WebElement txtUserName = driver.findElement(By.xpath("//*[@id='username']"));

		WebElement txtPassword = driver.findElement(By.xpath("//*[@id='password']"));

		WebElement btnLogin = driver.findElement(By.xpath("//*[@id='Login']"));

		// **********************Test Case1 Create
		// Account*****************************************//
		// Entering the UserName, Password and click on Login Button
		txtUserName.sendKeys(username);
		txtPassword.sendKeys(password);
		btnLogin.click();

		// Verifying the title of the login page
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.titleContains("Home | Salesforce"));
		// Verify login is successful
		if (driver.getTitle().contains("Home | Salesforce")) {
			Assert.assertTrue(true, "Login is Successful");
		}

	}

	@DataProvider(name = "getdata1")
	public Object[][] fetchDataForCreateAccount() {
		return new Object[][] { { AccountName } };
	}

	@DataProvider(name = "getdata2")
	public Object[][] fetchDataForEditAccount() {
		return new Object[][] {
				{ "Chennai1", "Chennai2", "Chennai3", "Chennai4", "Blore1", "Blore2", "Blore3", "Blore4" } };
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {

		if (!result.isSuccess()) {
			TakesScreenshot ss = (TakesScreenshot) driver;
			File source = ss.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File(Folder));
		}
		a.assertAll();
		driver.close();
	}

}
