import java.util.Scanner;

/* stop */

// top layer
public class Shell
{
   
    /*
    * Start game
    * Forfeit
    * Make move
    * Scramble tiles (for human)
    * Pass
    * Get Score (Do we want the score to always be shown)
    */
   
   
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
	    while(true)
	    {
	        System.out.println("Enter command: ");
	        String command = scanner.nextLine();
	       
	        // giant dfa
	        // inserted new comment
	        if ("start".equals(command))
	        {
	
	        }
	        else if ("forfeit".equals(command))
	        {
	           
	        }
	        else if (command.startsWith("move"))
	        {
	
	        }
	        else if ("scramble".equals(command))
	        {
	
	        }
	        else if ("pass".equals(command))
	        {
	
	        }
	        else if ("getscore".equals(command))
	        {
	
	        }
	        else if ("print".equals(command))
	        {
	
	        }
	        else // errors
	        {
	           
	        }
	    }
	}
}
