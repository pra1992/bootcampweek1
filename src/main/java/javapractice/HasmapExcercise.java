package javapractice;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.bag.SynchronizedSortedBag;

public class HasmapExcercise {
	  

	public static void main(String[] args) {
        Map<String, String> mp = new HashMap<String, String>();		
       mp.put("name1", "Prasanth");
       mp.put("name2", "Sankaran");
       System.out.println(mp.get("name1"));
       System.out.println(mp.containsKey("name2"));
       System.out.println(mp.containsValue("Sita"));
       System.out.println(mp.size());
       System.out.println(mp.isEmpty());
       System.out.println(mp.keySet());
       System.out.println(mp.values());
       System.out.println("from merger function " +mp.merge("name2", "Subbu", (OldValue,NewValue)->OldValue));
      for(String key: mp.keySet()) {
    	  System.out.println("Key -" + key);
      }
     for(String value : mp.values()) {
    	 System.out.println("Value is " + value);
     }
  
     //Iterating through Key Value
    for (Map.Entry<String, String> m : mp.entrySet()) {
		 System.out.println( m.getKey() + "and the corresponding value is " + m.getValue());
	} 
     
   
     for(int i =0; i<=(mp.size())-1; i++) {
    	 System.out.println(mp.getOrDefault("n", "Not Present"));
}}}
