
public class GameEngine
{

private Board board;
    private TileBag bag;
    private ComputerPlayer computer;
    private HumanPlayer human;
    private State state;

    private static String defaultBoardPath;
   
    public GameEngine()
    {
        board = new Board(defaultBoardPath);
        computer = new ComputerPlayer();
        human = new HumanPlayer();
        bag = new TileBag();       

    }
    
    public void giveTiles()
    {
       
    }

    /* gives initial tiles to both players, then in a loop, ask for
     * each player to move, check the move, make the move, update everything
     * in the process, check for endgame conditions, and repeat */

public void startGame()
    {
       
    }
}