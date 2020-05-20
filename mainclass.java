/*
 * Topic: Sudoku Platform
 *        Make your own sudoku,check if valid
 *        Or Simply solve a generated sudoku
 *        
 * By: Rutvika Patil    &&   Vallari Rajurkar
 *     2426                  2436
 *     B2 Comp SY            B2 Comp SY
 */


package minisem4dsa;
import java.util.*;



class Sudoku {
	char[][] board=new char[9][9];
	Scanner sc=new Scanner(System.in);
	
	
	
	public void accept()
	{
		System.out.println(">>Input 9*9 Sudoku \n   NOTE: Enter . at Empty Places");
		System.out.println("\n  Some Sample Inputs(testcases to check): \n. . . . . . . . .\r\n" + 
				". . . . . . . . .\r\n" + 
				". . . . . . . . .\r\n" + 
				". . . . . . . . .\r\n" + 
				". . . . . . . . .\r\n" + 
				". . . . . . . . .\r\n" + 
				". . . . . . . . .\r\n" + 
				". . . . . . . . .\r\n" + 
				". . . . . . . . ." +"\n \n. 2 . . . . 7 8 9 \r\n" + 
						". 5 . 7 8 . 1 2 . \r\n" + 
						"7 . 9 . 2 3 . 5 6 \r\n" + 
						"2 . 4 . 6 . 8 9 . \r\n" + 
						". 6 5 8 . 7 . 1 4 \r\n" + 
						"8 9 . 2 1 4 3 6 . \r\n" + 
						". 3 . 6 . 2 . 7 8 \r\n" + 
						"6 4 2 9 . 8 5 . 1 \r\n" + 
						"9 7 . 5 . 1 6 4 2 \n\n" +"  >>Input 9*9 sudoku as above:");
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++)
			{
				char c=sc.next().charAt(0); //there is no nextChar() in java
				if((c-'0')<=9 && (c-'0'>0))
				{
					board[i][j]=c;
				}
				else if(c=='.')
				{
					board[i][j]=c;
				}
				else
				{
					System.out.println("\nEnter Number in Range from 1 to 9");
				}
			}
		}
	}
	
	
    public boolean isValidSudoku(char[][] board) {
        /*
        A Sudoku board (partially filled) could be valid but is not necessarily solvable.
        Only the filled cells need to be validated according to  rules.
        The given board contain only digits 1-9 and the character '.'.
        The given board size is always 9x9.
        */
       

    for(int i = 0; i<9; i++){
        HashSet<Character> rows = new HashSet<Character>();
        HashSet<Character> columns = new HashSet<Character>();
        HashSet<Character> cube = new HashSet<Character>();
        for (int j = 0; j < 9;j++){
            if(board[i][j]!='.' && !rows.add(board[i][j]))
                return false;
            if(board[j][i]!='.' && !columns.add(board[j][i]))
                return false;
            //matrix traversal
            /* we need to stop after 3 horizontal steps, and go down 1 step vertical.
                 Use % for horizontal traversal. Because % increments by 1 for each j : 0%3 = 0 , 1%3 = 1, 2%3 = 2,
                 and resets back. So this covers horizontal traversal for each block by 3 steps.
                 Use / for vertical traversal. Because / increments by 1 after every 3 j: 0/3 = 0; 1/3 = 0; 2/3 =0; 3/3 = 1.
                 So far, for a given block, you can traverse the whole block using just j.
                 But because j is just 0 to 9, it will stay only first block. 
                 But to increment block, use i. To move horizontally to next block,
                 use % again : ColIndex = 3 * i%3 (Multiply by 3 so that the next block is after 3 columns. 
                 Ie 0,0 is start of first block, second block is 0,3 (not 0,1);*/
            
            int RowIndex = 3*(i/3);
            int ColIndex = 3*(i%3);
            if(board[RowIndex + j/3][ColIndex + j%3]!='.' && !cube.add(board[RowIndex + j/3][ColIndex + j%3]))
                return false;
        }
    }
    return true;     
        
    }
    
    
    
    public void solveSudoku(char[][] board) {
        dfs(board,0);
    }
    
    
    private boolean dfs(char[][] board, int d) {
        if (d==81) return true; //found solution
        int i=d/9, j=d%9;
        if (board[i][j]!='.') return dfs(board,d+1);//prefill number skip
        
        boolean[] flag=new boolean[10];
        
        validate(board,i,j,flag);
        //isValidSudoku(board);
        for (int k=1; k<=9; k++) {
            if (flag[k]) //if true
            {
                board[i][j]=(char)('0'+k);
                if (dfs(board,d+1)) return true;
            }
        }
        board[i][j]='.'; //if can not solve, in the wrong path, change back to '.' and out
        return false;
    }
    
    
    private void validate(char[][] board, int i, int j, boolean[] flag) {
    	//same as isValidSudoku, just void and maintains flag array instead
        Arrays.fill(flag,true);//fills all values of flag array to true
        
        for (int k=0; k<9; k++) {
            if (board[i][k]!='.') flag[board[i][k]-'0']=false;
            if (board[k][j]!='.') flag[board[k][j]-'0']=false;
            int r=i/3*3+k/3;//matrix traversal
            int c=j/3*3+k%3;
            if (board[r][c]!='.') flag[board[r][c]-'0']=false;
        }
        
    }
 

    //display board 
    void display(char[][] board)
    {
    System.out.println("\n--------------------------");
    	for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++)
			{
				System.out.print(board[i][j]+ " ");
			}
			
			System.out.println();
			
	    }
    	System.out.println("\n--------------------------");
    }
    
    
}




public class mainclass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("\n%%%%%%%%%%%%%%%%%%%%%%%%%%%%%SUDOKU%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
	    Scanner sc=new Scanner(System.in);
	    
	    Sudoku obj=new Sudoku();
	    int choice=0;
	    do
	    {
	    	System.out.println("\n 1.Input Unsolved Sudoku \n 2.Validate Input Sudoku \n 3.Generate Sudoku \n 4.Solution \n 5.Exit");
	    	choice=sc.nextInt();
	    	switch(choice)
	    	{
	    	case 1:
	    		obj.accept();
	    		break;
	    	case 2:
	            boolean z=obj.isValidSudoku(obj.board);
	            if(z==true)
	            {
	            	System.out.println("\n>>VALID SUDOKU");
	            }
	            else
	            {
	            	System.out.println("\n>>INVALID SUDOKU  \n   Enter Again:");
	            	obj.accept();
	            	
	            }
	    		break;
	    	case 3:
	    		
	    		break;
	    	case 4:
	    		obj.solveSudoku(obj.board);
	    		obj.display(obj.board);
	    		break;
	    	
	    	}
	    }while(choice!=0);

	}

}


 

/*
 *




%%%%%%%%%%%%%%%%%%%%%%%%%%%%%SUDOKU%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

 1.Input Unsolved Sudoku 
 2.Validate Input Sudoku 
 3.Generate Sudoku 
 4.Solution 
 5.Exit
1
>>Input 9*9 Sudoku 
   NOTE: Enter . at Empty Places

  Some Sample Inputs(testcases): 
. . . . . . . . .
. . . . . . . . .
. . . . . . . . .
. . . . . . . . .
. . . . . . . . .
. . . . . . . . .
. . . . . . . . .
. . . . . . . . .
. . . . . . . . .
 
. 2 . . . . 7 8 9 
. 5 . 7 8 . 1 2 . 
7 . 9 . 2 3 . 5 6 
2 . 4 . 6 . 8 9 . 
. 6 5 8 . 7 . 1 4 
8 9 . 2 1 4 3 6 . 
. 3 . 6 . 2 . 7 8 
6 4 2 9 . 8 5 . 1 
9 7 . 5 . 1 6 4 2

    >>Input 9*9 sudoku as above:

. 2 . . . . 7 8 9 
. 5 . 7 8 . 1 2 . 
7 . 9 . 2 3 . 5 6 
2 . 4 . 6 . 8 9 . 
. 6 5 8 . 7 . 1 4 
8 9 . 2 1 4 3 6 . 
. 3 . 6 . 2 . 7 8 
6 4 2 9 . 8 5 . 1 
9 7 . 5 . 1 6 4 2

 1.Input Unsolved Sudoku 
 2.Validate Input Sudoku 
 3.Generate Sudoku 
 4.Solution 
 5.Exit
2

>>VALID SUDOKU

 1.Input Unsolved Sudoku 
 2.Validate Input Sudoku 
 3.Generate Sudoku 
 4.Solution 
 5.Exit
4

--------------------------
1 2 3 4 5 6 7 8 9 
4 5 6 7 8 9 1 2 3 
7 8 9 1 2 3 4 5 6 
2 1 4 3 6 5 8 9 7 
3 6 5 8 9 7 2 1 4 
8 9 7 2 1 4 3 6 5 
5 3 1 6 4 2 9 7 8 
6 4 2 9 7 8 5 3 1 
9 7 8 5 3 1 6 4 2 

--------------------------
*/
