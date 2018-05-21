import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;


public class MinimumDifference {
	
	public static void main(String[] args) {
		
		int x = 32;
		
		int a[] = {5,7,1,12,21};
		int b[] = {8,30,20,15,12};
		
		HashMap <Integer , Integer> hm = new HashMap<Integer, Integer>();
		for(int i : a)
		{
			hm.put(i, x-i);
		}
		
		int diff = Integer.MAX_VALUE;
		int num1, num2;
		int i = 0;
		
		for(Entry<Integer, Integer> e = hm.entrySet() )
		{
			if(e.getValue() - b[i]
		}
		
	}

}
