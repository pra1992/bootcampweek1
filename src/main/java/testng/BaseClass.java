package testng;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

public class BaseClass {
	
	public static WebDriver driver;
	Actions actions;
	WebDriverWait wait;
	String WindowHandle = driver.getWindowHandle();
	// Declaring Java Script Executor
	JavascriptExecutor js = (JavascriptExecutor) driver;
	String AccountName = "Prasanth Sankaran";
	String PhoneNo = "75023687";
	SoftAssert a = new SoftAssert();
	
	
	@BeforeMethod
	public void setUp() {
		
		// ****Creation of WebDriver Object****//
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-notifications");

				 driver = new ChromeDriver(options);
				// Deleting the Cookies
				driver.manage().deleteAllCookies();
				// Launching the Url//
				driver.get("https://login.salesforce.com/");
				// maximizing the window and setting up implicit wait
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

				// Declaring Actions Instance
				actions = new Actions(driver);
		
				// Locating all the elements in the login page

				WebElement txtUserName = driver.findElement(By.xpath("//*[@id='username']"));

				WebElement txtPassword = driver.findElement(By.xpath("//*[@id='password']"));

				WebElement btnLogin = driver.findElement(By.xpath("//*[@id='Login']"));

				// **********************Test Case1 Create
				// Account*****************************************//
				// Entering the UserName, Password and click on Login Button
				txtUserName.sendKeys("bootcamp_2024@testleaf.com");
				txtPassword.sendKeys("Bootcamp@123");
				btnLogin.click();

				// Verifying the title of the login page
				wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				wait.until(ExpectedConditions.titleContains("Home | Salesforce"));
				// Verify login is successful
				if (driver.getTitle().contains("Home | Salesforce")) {
					Assert.assertTrue(true, "Login is Successful");
				}

	}
	
	@AfterMethod
	public void tearDown() {
		
		driver.close();
	}

}
