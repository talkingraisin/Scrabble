
public class Move
{
    private int direction;
    private int i; // row
    private int j; // column
    private String word;

    public Move(String word, int i, int j, int direction)
    {
        this.word = word;
        this.i = i;
        this.j = j;
        this.direction = direction;
    }
    
    public int getDirection()    
	{
		return direction;    
  	}
	  
    public int getI()
    {
    	return i;   
  	}
	  
    public int getJ()   
    {    
	    return j;
    }
	  
    public String getWord()    
    {
	    return word;   
    }
}