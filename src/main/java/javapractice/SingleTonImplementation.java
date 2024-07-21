package javapractice;

public class SingleTonImplementation {
	
	private static SingleTonImplementation ton;
	
	private SingleTonImplementation() {
		
	}
	
	public static SingleTonImplementation instantiateSingleTon() {
		ton = new SingleTonImplementation();
		return ton;
	}

}
