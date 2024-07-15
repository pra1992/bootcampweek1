package javapractice;

import java.util.Iterator;

public class ReverseEachWordsInString {
   
	public static void reverseWords(String Input) {
		 String[] A = Input.split(" ");
		// StringBuffer str = new StringBuffer();
		 for(  String   temp :A) {
     System.out.print(new StringBuilder(temp).reverse().append(" "));
		 }
	}
	
	public static void main(String[] args) {
		ReverseEachWordsInString.reverseWords("My name is Prasanth");
	}

}
