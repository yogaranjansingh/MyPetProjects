
public class SpiralMatrix {
	
	public static void main(String[] args) {
		
		int a[][] = {
				
				{1,  2,  3,  4},
				{5,  6,  7,  8},
				{9,  10, 11, 12},
				{13, 14, 15, 16}
		};
		
		int m = a.length;  // no of rows
	    int n = a[0].length;   // no of columns
	    int x = 0;
	    int y = 0;
	    int i = 0;
	    
	    while(x < m && y < n)
	    {
	    	
	    	for(i=0; i<n; i++)
	    	{
	    		System.out.println(a[x][i]);
	    	}
	    	x++;
	    	
	    	
	    	
	    }
		
	}

}
