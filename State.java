public class State
{
    private static String defaultBoardPath = "board";
    public static final int NumTilesPerPlayer = 7;

    private int turn;
    private Board board;
    private TileBag bag; // we will design public accessors
    private int[] scores;
    private Player[] players;

    public State(Player[] players)
    {
        board = Board.getNewDefaultBoard();
        bag = TileBag.getNewDefaultBag();
        this.players = players;
        scores = new int[players.length]; // initially 0
        turn = 0;
    }

    // makes sure that the move is valid. literally like places the letter on
    // the boards and sees if it's valid within the dictionary and doesn't
    // intersect with other tiles and checks all orientations possible.
    public boolean checkMove(Move move, Player p)
    {
        return true;
    }

    // removes the tiles from the actual players, puts it on the board, and at
    // the end, gives new tiles to the player.
    public void makeMove(Move move, Player p)
    {

    }

    public boolean isGameOver()
    {
        return false;
    }

}
