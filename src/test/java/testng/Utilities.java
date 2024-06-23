package testng;

import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilities extends BaseClass {

	Actions actions;

	JavascriptExecutor js;

	WebDriverWait wait;

	// Enter the value in the textBox

	public void enterValueInTextbox(WebElement textbox, String string) {
		try {
			textbox.isDisplayed();
			a.assertTrue(true, "Able to verify the Textbox");
			textbox.sendKeys(string);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			a.fail("Text Box Element is not identified due to " + e);
		} catch (Exception e) {
			a.fail("Unable to enter text due to " + e);
		}

	}

	// Clicking a WebElement

	public void verifyandclickOnElement(WebElement webelement, String element) {
		try {
			webelement.isDisplayed();
			a.assertTrue(true, "Able to verify the " + element);
			webelement.click();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			a.fail(element + "is not identified due to " + e);
		} catch (Exception e) {
			a.fail("Unable to click" + element + " due to " + e);
		}

	}

	// Scroll to an element
	public void moveToElement(WebElement webelement, String element) {

		try {
			webelement.isDisplayed();
			a.assertTrue(true, "Able to verify the " + element);
			actions.moveToElement(webelement).build().perform();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			a.fail(element + "is not identified due to " + e);
		} catch (Exception e) {
			a.fail("Unable to scroll to " + element + " due to " + e);
		}

	}

	// click an element using Javascript
	public void clickElementUsingJavascript(WebElement webelement, String element) {

		try {
			webelement.isDisplayed();
			a.assertTrue(true, "Able to verify the " + element);
			js.executeScript("arguments[0].click();", webelement);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			a.fail(element + "is not identified due to " + e);
		} catch (Exception e) {
			a.fail("Unable to click through JavaScript " + element + " due to " + e);
		}

	}

// click an element using Actions
	public void clickElementUsingActions(WebElement webelement, String element) {

		try {
			webelement.isDisplayed();
			a.assertTrue(true, "Able to verify the " + element);
			actions.moveToElement(webelement).click().build().perform();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			a.fail(element + "is not identified due to " + e);
		} catch (Exception e) {
			a.fail("Unable to click through Actions " + element + " due to " + e);
		}

	}

// Checking for Window Handles
	public void checkWindowHandles(Set<String> windows) {
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

}
