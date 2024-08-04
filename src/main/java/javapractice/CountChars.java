package javapractice;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CountChars {
	
	public static void countChars(String Input) {
		char[] A = Input.toCharArray();
		Map<Character, Integer> mp = new HashMap<Character,Integer>();
		for( char temp: A ) {
			int count = mp.getOrDefault(temp, 0);
			mp.put(temp, count+1);
		}
		
		for (Map.Entry<Character, Integer> m : mp.entrySet()) {
			System.out.println(m.getKey() + " " + "is repeated" + " " +  m.getValue() + " " + "times");
		}
}
	
	

	public static void main(String[] args) {
		
		CountChars.countChars("My name is Prasanth");
	}

}
