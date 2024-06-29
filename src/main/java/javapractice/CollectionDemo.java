package javapractice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class CollectionDemo {

	public static void main(String[] args) {
		
		int a[] = {1,1,2,3,3};
		Map<Integer, Integer> frequencies = new HashMap<Integer, Integer>();
		
		
		for (int i : a) {
			int count = frequencies.getOrDefault(i, 0);
			   frequencies.put(i, count+1);
		}
		
		for (int i : frequencies.keySet()) {
			int count = frequencies.get(i);
			
			System.out.println( i + "is repeated" + count + "times");
		}
		
		
	}}
	