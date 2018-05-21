import java.util.Arrays;


public class LongestIncreaingSubsequence {
	
	public static void main(String[] args) {
		
		int a[] = {10,22,9,33,21,5,41,60,80};
		
		int t[] = new int[a.length];
		
		Arrays.fill(t, 1);
		
		for(int i=1, j =0;i<a.length; i++)
		{
			while(i > j)
			{
				if(a[i] > a[j] && !(t[j]+1 <= t[i]) )
				{
					t[i] = Math.max(t[i], t[j]) + 1;
				}
				
				j++;
			}
			j=0;
		}
		
		Arrays.sort(t);
		
		System.out.println(t[8]);
		
	}

}
