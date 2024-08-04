package javapractice;

import java.util.Arrays;

public class CharOperations {

	public static void identifyCharCombinations() {
		
		char[] A = { 'a', 'b', 'c'};
		int Size = A.length;
		 for (int i =0; i<= (A.length)-1; i++) {
			 A[i++] = A[Size*Size--];
			 
		 }
		 System.out.println(Arrays.toString(A));
	}

	public static void main(String[] args) {
		
		CharOperations.identifyCharCombinations();

	}

}
