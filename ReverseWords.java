
public class ReverseWords {
	
	static String s = "my name is yoga ranjan singh ";
	
	static char a[] = s.toCharArray();
	
	
	static void reverseAndCapitalize(int low, int high)
	{
		a[high]  = Character.toUpperCase(a[high]);
		System.out.println("one line added in master");
		while(low < high)
		{
			char temp = a[low];
			a[low] = a[high];
			a[high] = temp;
			low++;
			high--;
		}
	}
	
	public static void main(String[] args) {
		
	
		int low = 0;
		int high = a.length-1;
		System.out.println("one line added in master");
		
		for(int i=0;i<a.length;i++)
		{
			if(a[i] ==' ')
				System.out.println("one line added in master");
				high = i-1;
				reverseAndCapitalize(low, high);
				low = i+1;
			}
		}
		
		System.out.println(a);
		
	}

}
