
public class Maze {
	int a[][];
	int row ;
	int col;
	
	Maze(int a[][], int row, int col)
	{
		this.a = a;
		this.row = row;
		this.col = col;
	}
	
	boolean checkFeasibility(int i, int j)
	{
		if(i>=0 && i<row && j<col && j >= 0 && a[i][j] == 1)
			return true;
		return  false; 
	}
	
	
	
	boolean ratMaze(int i, int j, int result[][])
	{
		
		if(i == row-1 && j == col-1)
		{
			result[i][j] = 1;
			return true;
		}
		
		if(checkFeasibility(i, j) == true)
		{
			result[i][j] = 1;
			
			if(ratMaze(i+1, j,result))
				return true;
		
			if(ratMaze(i, j+1,result))
				return true;
			
			else
			{
				result[i][j] = 0;
				return false;
			}
		}
		
	return false;
			
	}
	
	
	public static void main(String[] args) {
		
		int a[][] = {
						{1,1,0,0},
						{1,1,1,0},
						{0,0,1,1},
						{1,0,1,1}
		            };
		
		Maze maze = new Maze(a, a.length, a[0].length);
		int result[][] = new int[a.length][a[0].length];
		if(maze.ratMaze(0, 0,result))
			
		{
			for(int i=0;i<a.length;i++)
			{
				for(int j=0;j<a[0].length;j++)
				{
					System.out.print(result[i][j]);
				}
				System.out.println();
			}
			System.out.println();
		}
		
		
	}

}
