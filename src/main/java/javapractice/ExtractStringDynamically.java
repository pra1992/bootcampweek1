package javapractice;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ExtractStringDynamically {
	
	public static void extractString(String Input) {
		Set <String> result = new HashSet<String>();
		  if(Input.contains("test")) {
			int StartingIndex= Input.indexOf("test");
			System.out.println(StartingIndex);
			String[] SubString = Input.substring(StartingIndex).split("\\ s+");
			for (String temp:SubString) {
				result.add(temp);
			}
		    
	}
	System.out.println(String.join(" ", result));	  
	}

	public static void main(String[] args) {
		ExtractStringDynamically.extractString("Prasanth test to excel in Java Code testing Interviews");

	}

}
