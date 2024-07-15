package bootcamp3pom.utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import bootcamp3pom.pages.Assertion;
import bootcamp3pom.pages.BaseClass;

public class WaitUtils extends BaseClass {
	WebDriverWait wait;
	public WaitUtils(WebDriver driver) {
		System.out.println(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}
	// Wait for Title
	public void waitforTitle(String Tiltle) {
		try {
			wait.until(ExpectedConditions.titleContains(Tiltle));
		} catch (org.openqa.selenium.TimeoutException e) {
			Assertion.assertTimeOutError(Tiltle);
			
		}
	}

	// Wait for Visibility of an element
	public void waitforVisibilityofElement(WebElement element, String ElementName) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (org.openqa.selenium.TimeoutException e) {
			Assertion.assertTimeOutError(ElementName);
		}
	}

	// Wait for Visibility of All element
	public void waitforVisibilityofAllElements(List<WebElement> element, String ElementName) {
		try {
			wait.until(ExpectedConditions.visibilityOfAllElements(element));
		} catch (org.openqa.selenium.TimeoutException e) {
			Assertion.assertTimeOutError(ElementName);
		}
	}

	// Wait for element to become Stale
	public void waitforStalenessofElement(WebElement element, String ElementName) {
		try {
			wait.until(ExpectedConditions.stalenessOf(element));
		} catch (org.openqa.selenium.TimeoutException e) {
			Assertion.assertTimeOutError(ElementName);
		}
	}

	// Wait for presence of an Element
	public void waitforPresenceofElement(String xpath, String ElementName) {
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		} catch (org.openqa.selenium.TimeoutException e) {
			Assertion.assertTimeOutError(ElementName);
		}
	}

}
