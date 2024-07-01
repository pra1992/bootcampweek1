package javapractice;

import java.util.Iterator;

public class StringManipilation {

	public static void stringManipulate(String Input) {
		 StringBuilder S = new StringBuilder();
		 StringBuilder D = new StringBuilder();
		char[] A = Input.toCharArray();
		 for( char temp :A) {
			 if(Character.isLetterOrDigit(temp)) {
				S.append(temp) ;
			 }
			 else {
				 D.append(temp);
			 }
		 }
		System.out.println("Normal Value" + S.toString());
		System.out.println("Normal Value" + D.toString());
	}
	
	public static void main(String[] args) {
		StringManipilation.stringManipulate("he@#$ell*owor(ld");
	}
}
