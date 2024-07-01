package javapractice;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CountChars {
	
	public static void countChars(String Input) {
		char[] A = Input.toCharArray();
		Map<Character, Integer> mp = new HashMap<Character, Integer>();
		
		for( char temp:A) {
			int count  = mp.getOrDefault(temp, 0);
			         mp.put(temp, count+1);
			  
	}
		for(char temp1: mp.keySet()) {
			int count1 = mp.get(temp1);
			System.out.println( temp1 + " is repeated so many times " + count1);
			
		}
}
	
	

	public static void main(String[] args) {
		
		CountChars.countChars("My name is Prasanth");
	}

}
