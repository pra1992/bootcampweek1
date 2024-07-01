package bootcamp2testng;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class Utilities extends BaseClass {

	Actions actions;

	JavascriptExecutor js;
 
    WebDriver driver;
    Assertion softAssert;
    public Utilities(WebDriver driver) {
		System.out.println(driver);
	    this.driver = driver;
		softAssert = new Assertion();
        actions = new Actions(driver);
        js = (JavascriptExecutor)driver;
	}

	// Enter the value in the textBox

	public void enterValueInTextbox(WebElement textbox, String string) {
		try {
			textbox.isDisplayed();
			Assertion.assertTrue(true, textbox, "Able to verify the message");
			textbox.sendKeys(string);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			Assertion.assertFail(false, e, "Text Box Element is not identified");
		} catch (Exception e) {
			Assertion.assertFail(false, e, "Unable to enter text");
		}

	}

	// Clicking a WebElement

	public void verifyandclickOnElement(WebElement webelement, String element) {
		try {
			webelement.isDisplayed();
			Assertion.assertTrue(true, webelement, "Able to verify");
			webelement.click();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			Assertion.assertFail(false, e, "Not Identified");
		} catch (Exception e) {
		  Assertion.assertFail(false, e, "Not Identified");
		}

	}

	// Scroll to an element
	public void moveToElement(WebElement webelement, String element) {

		try {
			webelement.isDisplayed();
			Assertion.assertTrue(true, webelement, "Able to verify");
			actions.moveToElement(webelement).build().perform();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			Assertion.assertFail(false, e, "Not Identified");
		} catch (Exception e) {
			Assertion.assertFail(false, e, "Not Identified");
		}

	}

	// click an element using Javascript
	public void clickElementUsingJavascript(WebElement webelement, String element) {

		try {
			webelement.isDisplayed();
			Assertion.assertTrue(true, webelement, "Able to verify");
			js.executeScript("arguments[0].click();", webelement);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			Assertion.assertFail(false, e, "Not Identified");
		} catch (Exception e) {
			Assertion.assertFail(false, e, "Not Identified");
		}

	}

// click an element using Actions
	public void clickElementUsingActions(WebElement webelement, String element) {

		try {
			webelement.isDisplayed();
			Assertion.assertTrue(true, webelement, "Able to verify");
			actions.moveToElement(webelement).click().build().perform();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			Assertion.assertFail(false, e, "Not Identified");
		} catch (Exception e) {
			Assertion.assertFail(false, e, "Not Identified");
		}

	}

// Checking for Window Handles
	public void checkWindowHandles(Set<String> windows) {
		WindowHandle = driver.getWindowHandle();
		windows = driver.getWindowHandles();
		for (String OpenWindow : windows) {
			if (!OpenWindow.equals(WindowHandle)) {
				driver.switchTo().window(OpenWindow);
			}
		}
	}

//Scrolling within a WebElement Vertically

	public void scrollVertically(WebElement element, int ScrollBy) {

		js.executeScript("arguments[0].scrollBy(0, arguments[1])", element, ScrollBy);
	}

//Scrolling within a WebElement Vertically

	public void scrollHorizontally(WebElement element, int ScrollBy) {

		js.executeScript("arguments[0].scrollBy(arguments[1], 0)", element, ScrollBy);
	}
// Verify the title of the page
	
	public void verifyTitleOfPage(Boolean value, String expected) {
		try {
			if(driver.getTitle().contains(expected)) {
				Assertion.assertTrue(value, expected);
			}
		}
		catch(Exception e) {
			Assertion.assertFail("Title of page is not loaded due to" + e);
		}
	}
}
