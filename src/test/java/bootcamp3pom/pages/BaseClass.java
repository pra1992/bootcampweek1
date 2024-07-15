package bootcamp3pom.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
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
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import bootcamp3pom.utils.Utilities;
import bootcamp3pom.utils.WaitUtils;
import io.cucumber.testng.AbstractTestNGCucumberTests;

public class BaseClass extends AbstractTestNGCucumberTests {
   private static final ThreadLocal<WebDriver>  tlDriver = new ThreadLocal<WebDriver>();
//	public  WebDriver driver;
	Actions actions;
	WebDriverWait wait;
	// Declaring Java Script Executor
	JavascriptExecutor js;
	String Folder = System.getProperty("user.dir") + "/Images";
	public WaitUtils w;
	public Utilities u;
	public Assertion softAssert;
	public String WindowHandle = null;
	public Set<String> OpenedWindows = null;
    public static String downloadDir= null;
    public static String ProjectRoot = null;
    public String fileName1;

    public Properties property = new Properties();
    
    public void setDriver( WebDriver driver) {
    	 tlDriver.set(driver);
    }
    
    public WebDriver getDriver() {
    	return tlDriver.get();
    } 
    
     public BaseClass() {
	 PageFactory.initElements(getDriver(), this);
   }
    
  // Locating all the elements in the login page
     
     @FindBy(xpath = "//*[@id='username']")
		WebElement txtUserName;
     
     @FindBy(xpath = "//*[@id='password']")
     WebElement txtPassword;
     
     @FindBy(xpath = "//*[@id='Login']")
     WebElement btnLogin;

	@Parameters({ "url", "username", "password" })
	@BeforeMethod
	public void setUp(String url, String username, String password) throws IOException, URISyntaxException {
      
		//Loading the property file
		 
	        FileInputStream fis = new FileInputStream("src/test/resources/Properties/Config.properties");
	        property.load(fis);
	     // ****Creation of WebDriver Object****//
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("profile.default_content_settings.popups", 0);
	    ProjectRoot = new File(getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParent();
		downloadDir= ProjectRoot + "\\src\\test\\resources\\download";
		File downloadFolder = new File(downloadDir);
		if(!downloadFolder.exists()) {
			downloadFolder.mkdir();
		}
	    map.put("download.default_directory", downloadDir);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", map);
		options.addArguments("download.default_filename", property.getProperty("FileName") + property.getProperty("FileProperty")); // set the file name
		options.addArguments("download.prompt_for_download", "false");// disable prompt
		options.addArguments("download.directory_upgrade", "true"); // enable downloading
		//Setting the default download directory
	//driver = new ChromeDriver(options);
	// Setting up the Browser Options	
		
	setDriver(new ChromeDriver(options) );
	
		// Deleting the Cookies
	getDriver().manage().deleteAllCookies();
		// Launching the Url//
	getDriver().get(url);
		// maximizing the window and setting up implicit wait
	getDriver().manage().window().maximize();
	getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		// Declaring Actions Instance

		w = new WaitUtils(getDriver());
        u = new Utilities(getDriver());
 
		// Entering the UserName, Password and click on Login Button
		txtUserName.sendKeys(username);
		txtPassword.sendKeys(password);
		btnLogin.click();

		// Verifying the title of the login page
		wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
		wait.until(ExpectedConditions.titleContains("Home | Salesforce"));
		// Verify login is successful
		if (getDriver().getTitle().contains("Home | Salesforce")) {
			Assert.assertTrue(true, "Login is Successful");
		}

	}

	@DataProvider(name = "getdata1")
	public Object[][] fetchDataForCreateAccount() throws IOException {
		return ExcelDataReader.readExcelData("CreateAccount");
	//	return new Object[][] { { ExcelDataReader.readExcelData("CreateAccount") } };
	}

	@DataProvider(name = "getdata2")
	public Object[][] fetchDataForEditAccount() throws IOException {
		return ExcelDataReader.readExcelData("EditAccount");
//		return new Object[][] {
//				{ ExcelDataReader.readExcelData("EditAccount") } 
//				};
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {

		if (!result.isSuccess()) {
			TakesScreenshot ss = (TakesScreenshot) getDriver();
			File source = ss.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File(Folder));
		}
		Assertion.doAssertAll();
		getDriver().close();
	}

}
