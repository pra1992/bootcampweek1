package javapractice;

public class Arrays {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       int[][] a = {{123,2,3},{4,500,9},{6,8,7}};
       
      int max = a[2][2];
      
       for (int i=0; i<3; i++) {
    	   
    	   for (int j=0; j<3; j++) {
    		   if(a[i][j]>max) {
    			   max = a[i][j];
    		   }
    		   
    	   }
       }
       System.out.println(max);
}
}
