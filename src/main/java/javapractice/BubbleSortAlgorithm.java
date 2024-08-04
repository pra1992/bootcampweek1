package javapractice;


import java.util.Arrays;
public class BubbleSortAlgorithm {
	
	public static void sort(int[] A) {
		for(int i=0; i<A.length-1; i++) {
			for(int j=0; j<A.length-i-1; j++) {
				if(A[j]> A[j+1]) {
					int temp = A[j];
					A[j] = A[j+1];
					A[j+1] = temp;
				}
			}
			
		}
	System.out.println("Array in Ascending order is " + Arrays.toString(A));
		
		 for(int k=0;k< A.length; k++) {
			 
			for(int x=0; x< A.length-k-1; x++) {
				if(A[x]< A[x+1]) {
					int temp = A[x];
					A[x] = A[x+1];
					A[x+1] = temp;
					
					
				}
				
			}
			
			 }
		 System.out.println("Array in descending order is " + Arrays.toString(A));
		 } 
	

	public static void main(String[] args) {
		int[] array = {9,10,5,6,29,800,8004,70};
		BubbleSortAlgorithm.sort(array);
	}

}
