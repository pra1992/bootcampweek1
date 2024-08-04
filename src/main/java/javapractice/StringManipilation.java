package javapractice;

import java.util.Iterator;

public class StringManipilation {

	public static void stringManipulate(String Input) {
		 StringBuilder S = new StringBuilder();
		 StringBuilder D = new StringBuilder();
		
		  char[] A = Input.toCharArray();
		  for (char c : A) {
			  if(Character.isLetterOrDigit(c)) {
				  S.append(c);
			  }
			  else if(! Character.isLetterOrDigit(c)) {
				  D.append(c);
			  }
		}
	}
	
	public static void main(String[] args) {
		StringManipilation.stringManipulate("he@#$ell*owor(ld");
	}
}
