package bootcamp2testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

public class Assertion {
  static SoftAssert a = new SoftAssert();


  public static void assertTrue(boolean value, WebElement element, String message ) {
	  if(value==true) {
		  a.assertTrue(value, message + element);
  }  
  }
  
  public static void assertTrue(boolean value, String message ) {
	  if(value==true) {
		  a.assertTrue(value, message );
  }  
  }
  
  public static void assertFail( boolean value,Exception e, String message) {
	  if(value == false) {
		  a.fail(message + e);
	  }
  }
  
  public static void assertFail( String message) {
	 
		  a.fail(message);
  }
  
  public static void assertSuccessMessage(boolean value, String Message) {
	  if(value == true) {
		  a.assertTrue(value, Message);
	  }
	  
  }

  public static void assertTimeOutError(String ElementName) {
	   a.fail(ElementName + "is failed due to Timeout");
  }


public static void doAssertAll() {
	 a.assertAll();
}
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
}

