package javapractice;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import java.util.ArrayList;
import java.util.Arrays;


public class PrimeNumberArray {

	public static void findPrimeNumbers() {
	    int[] A = new int[20];
	    List<Integer> primeList = new ArrayList<Integer>();
	    for(int i=0; i< A.length; i++) {
	        A[i] = i+1;
	    }
	    System.out.println(Arrays.toString(A));
	    for(int j = 0; j<= (A.length)-1; j++) {
	        if(A[j] >= 1) { // Check if the number is greater than or equal to 2
	            boolean isPrime = true;
	            for(int k = 2; k < A[j]; k++) {
	                if(A[j] % k == 0) { // Check if the number is divisible by any number less than itself
	                    isPrime = false;
	                    break;
	                }
	            }
	             if(isPrime) {
	            	 primeList.add(A[j]);
	             }
	            }
	        }
	    System.out.println("Prime numbers: " + primeList);
	    }
	   
	
	
	
	
	public static void main(String[] args) {
		 
	}
}
