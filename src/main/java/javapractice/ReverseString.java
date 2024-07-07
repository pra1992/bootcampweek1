package javapractice;

public class ReverseString {
	static String Output = null;
	
	public  void reverseString(String Input) {

	
	StringBuilder st = new StringBuilder(Input);
     Output = st.reverse().toString();
	}
	public static void main(String[] args) {
		ReverseString rs = new ReverseString();
		rs.reverseString("My Name Is Prasanth");
		System.out.println(Output);
	}

}
