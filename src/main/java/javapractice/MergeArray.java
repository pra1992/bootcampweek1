package javapractice;
import java.util.Arrays;

public class MergeArray {

	public static void mergeArray(int[]A, int[]B) {
		
		int[] MergedArray = new int[A.length + B.length];
		for(int i=0; i< A.length; i++) {
			
			MergedArray[i] = A[i];
		}
		
		for(int j=0; j<B.length; j++) {
			MergedArray[A.length + j] = B[j];
		}
		
	System.out.println(Arrays.toString(MergedArray));	
		
	}
	
	public static void main(String[] args) {
		   int[] array1 = {1, 2, 3};
	        int[] array2 = {4, 5, 6};
	        MergeArray.mergeArray(array1, array2);
	}

}
