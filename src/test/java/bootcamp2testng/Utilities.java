package bootcamp2testng;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class Utilities extends BaseClass {

	Actions actions;

	JavascriptExecutor js;
	public static List<DateTime> dates = null;
	WebDriver driver;
	Assertion softAssert;

	public Utilities(WebDriver driver) {
		System.out.println(driver);
		this.driver = driver;
		softAssert = new Assertion();
		actions = new Actions(driver);
		js = (JavascriptExecutor) driver;
		dates = new ArrayList<DateTime>();
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

	// Click on Enter in the textBox

	public void pressEnterInTextbox(WebElement textbox) {
		try {
			textbox.isDisplayed();
			Assertion.assertTrue(true, textbox, "Able to verify the message");
			textbox.sendKeys(Keys.ENTER);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			Assertion.assertFail(false, e, "Text Box Element is not identified");
		} catch (Exception e) {
			Assertion.assertFail(false, e, "Unable to press ENTER");
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

//Switch to default content
	public void switchDefaultContent() {
		try {
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			Assertion.assertFail("Unable to switch to default window due to " + e);
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
			if (driver.getTitle().contains(expected)) {
				Assertion.assertTrue(value, expected);
			}
		} catch (Exception e) {
			Assertion.assertFail("Title of page is not loaded due to" + e);
		}
	}

	// Select a particular value from Dropdown
	public void selectValueFromDropdown(List<WebElement> Dropdownoptions, String DropdownName, String value) {
		try {
			for (int i = 0; i <= (Dropdownoptions.size()) - 1; i++) {
				if (Dropdownoptions.get(i).getText().trim().equals(value)) {
					u.clickElementUsingJavascript(Dropdownoptions.get(i), value);
					break;
				}
			}
		} catch (java.lang.IndexOutOfBoundsException e) {
			Assertion.assertFail("Failed due to Index Out Of Bound" + e);
		} catch (Exception e) {
			Assertion.assertFail("Unable to select value from" + DropdownName + " due to" + e);
		}
	}

	// Convert Date to a specified Format

	public DateTime convertDateToSpecifiedFormat(String InputDate) {
		DateTime ResultDate = null;
		try {
			DateTimeFormatter format = DateTimeFormat.forPattern("dd/MM/yyyy hh:mm a");
			ResultDate = format.parseDateTime(InputDate);
		} catch (Exception e) {
			Assertion.assertFail("Unable to fetch the date due to" + e);
		}
		return ResultDate;
	}

	// Verify element is Displayed
	public boolean verifyElementIsDisplayed(WebElement element, String ElementName) {
		boolean flag = false;
		try {
			if (element.isDisplayed()) {
				flag = true;
			}
		} catch (Exception e) {
			Assertion.assertFail("Element is not displayed due to " + e);
		}
		return flag;
	}

	// Verify the expected Text is present
	public void verifyExpectedTest(WebElement element, String ElementName, String ExpectedText) {
		try {
			w.waitforVisibilityofElement(element, ElementName);
			String actual = element.getText().trim();
			Assertion.assertEquals(true, actual.equals(ExpectedText));
		} catch (Exception e) {
			Assertion.assertFail("Unable to verify the Expected Text due to " + e);
		}
	}

}
