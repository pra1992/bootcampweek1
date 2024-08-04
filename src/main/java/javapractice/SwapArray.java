package javapractice;

public class SwapArray {
	
	public static void swapArray(int[] A, int i, int j) {
		   int temp = A[i];
		   A[i] = A[j];
		   A[j] = temp;
				   }

	public static void main(String[] args) {
		 int[] array = {1, 2, 3, 4, 5};
	        int i = 1; // index of first element to swap
	        int j = 3; // index of second element to swap
	        swapArray(array, i, j);

	}

}
