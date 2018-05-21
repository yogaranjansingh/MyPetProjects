
public class LongestCommonSubsequence {

	public static void commonSubsequence(String s1, String s2, int m, int n)
	{
		int arr[][] = new int[m+1][n+1];
		
		
		for(int i=1;i<=m;i++)
		{
			for(int j=1;j<=n;j++)
			{
				if(s1.charAt(i-1) == s2.charAt(j-1))
				{
					arr[i][j] =  arr[i-1][j-1]+1;
				}
				
				if(s1.charAt(i-1) != s2.charAt(j-1))
				{
					arr[i][j] = Math.max( arr[i-1][j], arr[i][j-1] );
				}
			}
			//System.out.println();
		}
		
		for(int i=0;i<=m;i++)
		{
			for(int j=0;j<=n;j++)
			{
				
					System.out.print(arr[i][j]+" ");
				
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		
		String s1 = "abcdef";
		String s2 = "acbcf";
		int m = s1.length();
		int n = s2.length();
		commonSubsequence(s1, s2, m, n);
		

	}

}
