package bootcamp3pom.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
	int count = 0;
	int Max_Count = 3;

	public boolean retry(ITestResult result) {

		while (count < Max_Count) {

			count++;
			return true;
		}
		return false;
	}
	
	

}
