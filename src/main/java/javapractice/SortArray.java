package javapractice;


import java.util.Arrays;

public class SortArray {
	
	public static void printMax(int[] A) {
		 int maxValue = Integer.MIN_VALUE;
		 int secondMaxValue = Integer.MIN_VALUE;
		 int ThirdMaxValue = Integer.MIN_VALUE;
		 for(int i=0; i< A.length; i++) {
			 if(A[i]>maxValue) {
				secondMaxValue = maxValue;
				maxValue = A[i];
			 }
			 else if(A[i]< maxValue) {
				 ThirdMaxValue = secondMaxValue;
				 secondMaxValue = A[i];
			 }
			 else if(A[i]< secondMaxValue) {
				 ThirdMaxValue = A[i];
			 }
		 }
		System.out.println(maxValue); 
		System.out.println(secondMaxValue); 
		System.out.println(ThirdMaxValue); 
		}
	
		
	


	public static void main(String[] args) {
	 int[] A = {50,85,20,65,75};
	 SortArray.printMax(A);
	 
	   
	}

}
