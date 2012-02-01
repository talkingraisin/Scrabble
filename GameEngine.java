
public class GameEngine
{
    private ComputerPlayer computer;
    private HumanPlayer human;
    private State state;

    private static String defaultBoardPath;
    public static String defaultTilePath = "tiles";
    
    public GameEngine()
    {
        computer = new ComputerPlayer();
        human = new HumanPlayer();
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