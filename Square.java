public class Square
{
	public enum SquareType // 0:normal 1:DW 2:TW 3:DL 4:TL
	{
		NS("  "), DW("2W"), TW("3W"), DL("2L"), TL("3L");
		
		private SquareType(String name) 
		{
			this.name = name;
		}
		
		private final String name;
		
		public String toString() 
		{
			return name;
		}
	}
	
    private SquareType special; 
    private Tile tile;

    public Square(SquareType special)
    {
    	this.tile = null;
    	this.special = special;
    }
    
    public Square(Tile tile, SquareType special)
    {
    	this.tile = tile;
    	this.special = special;
    }

    public Tile getTile()
    {
    	return tile;
    }

    public SquareType getSpecial()
    {
    	return special;
    }
    
    public void setTile(Tile tile)
    {
    	this.tile = tile;
    }
    
}
