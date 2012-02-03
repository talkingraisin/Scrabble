
public class Move
{
	public enum MoveType
	{
		Normal, Exchange, Pass, Resign
	}
	
    private int direction;
    private int i; // row
    private int j; // column
    private String word;
    private MoveType type;

    public Move(MoveType type, String word, int i, int j, int direction)
    {
    	assert(type == MoveType.Normal);
    	this.type = type;
        this.word = word;
        this.i = i;
        this.j = j;
        this.direction = direction;
    }
    
    public Move(MoveType type, String letters)
    {
    	assert(type == MoveType.Exchange);
    	this.type = type;
    	this.word = letters;
    }
    
    public Move(MoveType type)
    {
    	assert(type == MoveType.Pass || type == MoveType.Resign);
    	this.type = type;
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