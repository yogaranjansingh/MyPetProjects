
public class Permute {
	
	String s = "abc";
	
	
	void permuteString(String str, String string)
	{
		int n = string.length();
		
		if(n==0)
			System.out.println(str);
		
		else
		{
			for(int i=0;i<n;i++)
			{
				permuteString(str+string.charAt(i), string.substring(0, i) + string.substring(i+1,n));
			}
		}
		
	}
	public static void main(String[] args) {
		
		Permute p = new Permute();
		p.permuteString("", "abc");
	}

}
