import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Board
{
    public static final int ROWS = 15;
    public static final int COLS = 15;
    
    private static final BoardFormat DEFAULT_BOARD_FORMAT = BoardFormat.HASBRO;
    
    
    private Square[][] board = new Square[ROWS][COLS];
    private int numTiles = 0;
    private int numTWSquares = 0;
    private int numDWSquares = 0;
    private int numDLSquares = 0;
    private int numTLSquares = 0;
    
    protected Board(String[] boardFormat)
        throws InvalidBoardFormatException
    {
        if (boardFormat.length != ROWS)
            throw new InvalidBoardFormatException(InvalidBoardFormatException.Cause.INVALID_ROW_COUNT);
        for (int row = 0; row < ROWS; ++row)
        {
            String[] squares = boardFormat[row].trim().split("\\s");
            if (squares.length != COLS)
                throw new InvalidBoardFormatException(InvalidBoardFormatException.Cause.INVALID_COL_COUNT);
            for (int col = 0; col < COLS; ++col)
            {
                SquareBonus bonus = SquareBonus.getByName(squares[col]);
                if (bonus == null)
                    throw new InvalidBoardFormatException(InvalidBoardFormatException.Cause.INVALID_BONUS);
                incSpecialSquareCount(bonus);
                board[row][col] = new Square(bonus);
            }
        }
    }
    
    private void incSpecialSquareCount(SquareBonus bonus)
    {
        switch (bonus)
        {
            case NS: return;
            case DW: { numDWSquares++; return; }
            case TW: { numTWSquares++; return; }
            case DL: { numDLSquares++; return; }
            case TL: { numTLSquares++; return; }
            default: throw new RuntimeException("Unexpected Square Bonus");
        }
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
    
    public int getNumBonusSquares() { return this.numTWSquares + this.numDWSquares + this.numTLSquares + this.numDLSquares; }
    public int getNumNormalSquares() { return ROWS*COLS - getNumBonusSquares(); }
    public int getNumTWSquares() { return this.numTWSquares; }
    public int getNumDWSquares() { return this.numDWSquares; }
    public int getNumTLSquares() { return this.numTLSquares; }
    public int getNumDLSquares() { return this.numDLSquares; }
    
    // Here's how the board will look like on cmdline:
    // Any square with a tile on it will print out the tile
    // Any empty square with special type will print out the type
    // Otherwise, nothing will be printed
    public String toString()
    {
    	StringBuilder sb = new StringBuilder();
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
    				sb.append(board[row][col].getBonus());
    				sb.append('|');
    			}
    		}
    		sb.append("\n----------------------------------------------\n");
    	}
    	
    	return sb.toString();
    }
    
    public String toStringWordsOnly()
    {
    	StringBuilder sb = new StringBuilder();
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
    	StringBuilder sb = new StringBuilder();
    	sb.append("----------------------------------------------\n");
    			   
    	for (int row = 0; row < ROWS; row++)
    	{
    		sb.append('|');
    		for (int col = 0; col < COLS; col++)
    		{
    			sb.append(board[row][col].getBonus());
    			sb.append('|');
    		}
    		sb.append("\n----------------------------------------------\n");
    	}
    	
    	return sb.toString();
    }
    
    public static Board getNewBoard(BoardFormat format)
    {
        try
        {
            return new Board(BoardFormat.getBoardFormat(format));
        }
        catch (InvalidBoardFormatException e)
        {
            throw new RuntimeException(e);
        }
    }
    
    public static Board  getNewDefaultBoard() { return getNewBoard(DEFAULT_BOARD_FORMAT); }
    
    public static Board getNewBoardFromFile(String boardFormatFilename)
        throws FileNotFoundException, InvalidBoardFormatException
    {
        Scanner s = new Scanner(new BufferedReader(new FileReader(new File(boardFormatFilename))));
        ArrayList<String> lines = new ArrayList<String>();
        while (s.hasNextLine())
            lines.add(s.nextLine());
        return new Board(lines.toArray(new String[0]));
    }
    
    public static void main(String[] args)
    {
    	Board board = Board.getNewBoard(BoardFormat.RANDOM);
    	System.out.println(board);
    	/*System.out.println(board.toStringWordsOnly());
    	System.out.println(board.toStringTilesOnly());*/
    	System.out.println(board.getNumBonusSquares());
    	System.out.println(board.getNumTWSquares());
    	System.out.println(board.getNumTLSquares());
    	System.out.println(board.getNumDWSquares());
    	System.out.println(board.getNumDLSquares());
    }
    
}