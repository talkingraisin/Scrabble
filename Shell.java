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
   
   
   
    while()
    {
        System.out.println(“Enter command: “);
        String command = System.in.nextLine();
       
        // giant dfa
        if (“start”.equals(command))
        {

        }
        else if (“forfeit”.equals(command))
        {
           
        }
        else if (command.startsWith(“move”))
        {

        }
        else if (“scramble”.equals(command))
        {

        }
        else if (“pass”.equals(command))
        {

        }
        else if (“getscore”.equals(command))
        {

        }
        else if (“print”.equals(command))
        {

        }
        else // errors
        {
           
        }
    }
}
S