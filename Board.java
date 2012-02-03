import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Board
{
    public static final int ROWS = 15;
    public static final int COLS = 15;
    
    private Square[][] board;
    private int numTiles = 0;
    
    public Board(String path)
    {
    	board = new Square[ROWS][COLS];
    	
    	FileReader fr = null;
    	Scanner scanner;
		try 
		{
			fr = new FileReader(path);
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		scanner = new Scanner(fr);
		
		int curRowIndex = 0;
		while(scanner.hasNextLine())
		{
			if (curRowIndex >= ROWS)
				throw new RuntimeException("Invalid board file");
			
			String[] s = scanner.nextLine().split(" ");
			if (s.length != COLS)
				throw new RuntimeException("Invalid board file");
			
			for (int col = 0; col < COLS; col++)
			{
				if ("TW".equals(s[col]))
					board[curRowIndex][col] = new Square(Square.SquareType.TW);
				else if ("DW".equals(s[col]))
					board[curRowIndex][col] = new Square(Square.SquareType.DW);
				else if ("TL".equals(s[col]))
					board[curRowIndex][col] = new Square(Square.SquareType.TL);
				else if ("DL".equals(s[col]))
					board[curRowIndex][col] = new Square(Square.SquareType.DL);
				else if ("NS".equals(s[col]))
					board[curRowIndex][col] = new Square(Square.SquareType.NS);
				else // error
					throw new RuntimeException("Invalid board file");
			}
			
			curRowIndex++;
		}
		
		if (curRowIndex != ROWS)
			throw new RuntimeException("Invalid board file");
    }
    
    public void addTile(Tile tile, int row, int col)
    {
    	if (board[row][col].getTile() != null)
    		throw new RuntimeException("Tried to add tile to non-empty square: " + row + ", " + col);
    	
    	board[row][col].setTile(tile);
    	numTiles++;
    }
    
    public int getNumTiles()
    {
    	return numTiles;
    }
    
    // Here's how the board will look like on cmdline:
    // Any square with a tile on it will print out the tile
    // Any empty square with special type will print out the type
    // Otherwise, nothing will be printed
    public String toString()
    {
    	StringBuffer sb = new StringBuffer();
    	sb.append("----------------------------------------------\n");
    			   
    	for (int row = 0; row < ROWS; row++)
    	{
    		sb.append('|');
    		for (int col = 0; col < COLS; col++)
    		{
    			Tile t = board[row][col].getTile();
    			if (t != null)
    			{
    				sb.append(t);
    				sb.append('|');
    			}
    			else
    			{
    				sb.append(board[row][col].getSpecial());
    				sb.append('|');
    			}
    		}
    		sb.append("\n----------------------------------------------\n");
    	}
    	
    	return sb.toString();
    }
    
    public String toStringWordsOnly()
    {
    	StringBuffer sb = new StringBuffer();
    	sb.append("----------------------------------------------\n");
    			   
    	for (int row = 0; row < ROWS; row++)
    	{
    		sb.append('|');
    		for (int col = 0; col < COLS; col++)
    		{
    			Tile t = board[row][col].getTile();
    			if (t != null)
    			{
    				sb.append(t);
    				sb.append('|');
    			}
    			else
    			{
    				sb.append("  ");
    				sb.append('|');
    			}
    		}
    		sb.append("\n----------------------------------------------\n");
    	}
    	
    	return sb.toString();
    }
    
    public String toStringTilesOnly()
    {
    	StringBuffer sb = new StringBuffer();
    	sb.append("----------------------------------------------\n");
    			   
    	for (int row = 0; row < ROWS; row++)
    	{
    		sb.append('|');
    		for (int col = 0; col < COLS; col++)
    		{
    			sb.append(board[row][col].getSpecial());
    			sb.append('|');
    		}
    		sb.append("\n----------------------------------------------\n");
    	}
    	
    	return sb.toString();
    }
    
    public static void main(String[] args)
    {
    	Board board = new Board("board");
    	System.out.println(board);
    	System.out.println(board.toStringWordsOnly());
    	System.out.println(board.toStringTilesOnly());
    }
    
}