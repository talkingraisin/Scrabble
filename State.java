public class State
{
	private int turn;
	private Board board;
	private TileBag bag; // we will design public accessors
	private int humanScore;
	private int computerScore;
	  
	// makes sure that the move is valid. literally like places the letter on the boards and sees if it’s valid within the dictionary and doesn’t intersect with other tiles and checks all orientations possible.
	private boolean checkMove(Move move, Player p)
	{
		  return true;
	}
	// removes the tiles from the actual players, puts it on the board, and at the end, gives new tiles to the player. yes. yes. *nods
	private void makeMove(Move move, Player p)
	{
	    
	}
}
