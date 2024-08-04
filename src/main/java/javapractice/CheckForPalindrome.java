package javapractice;

public class CheckForPalindrome {
	
	public static void checkPalindrome(String Input) {
		char[] A = Input.toCharArray();
		int[] freqMap = new int[256];// Forming an array to check the Ascii value for each character
		int count=0;
		// populating the freqMap with ASCII value of Input
		for ( char temp:A) {
			freqMap[temp]++;// it increments the value of freqMap, but leave temp as unchanged, whereas freeMap[temp++] increments temp immediately
		}
		
		//Count the characters that form Palindrome
		for (int  i : freqMap) {
			 count = count + i/2;//Each pair of chars that form a palindrome, so that's why we are dividing by 2
		}
		
		System.out.println("Count of chars that form a palindrome is " + count);
	}

	public static void main(String[] args) {

     checkPalindrome("ATMOOPYY");
	}

}
