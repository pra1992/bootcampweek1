package interview;

import java.lang.reflect.Array;

public class IntegerOperations {
	
	public void secondHighestProduct( ) {
		int[] A = {1,2,3,4,5,6};
		 int product1 =0;
		 int product2 = 0;
		
		 for(int i=0; i< A.length; i++) {
			 
			 for(int j=1; j< A.length; j++) {
				 if(A[i]> A[j]) {
					 product1 = A[i]*A[j];
					 
				 }
			 }
			 
		 }
		
	}

	public static void main(String[] args) {
	
	

	}

}
