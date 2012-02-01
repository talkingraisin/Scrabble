public class Square
{
	public enum TileType // 0:normal 1:DW 2:TW 3:DL 4:TL
	{
		NS("  "), DW("2W"), TW("3W"), DL("2L"), TL("3L");
		
		private TileType(String name) 
		{
			this.name = name;
		}
		
		private final String name;
		
		public String toString() 
		{
			return name;
		}
	}
	
    private TileType special; 
    private Tile tile;

    public Square(TileType special)
    {
    	this.tile = null;
    	this.special = special;
    }
    
    public Square(Tile tile, TileType special)
    {
    	this.tile = tile;
    	this.special = special;
    }

    public Tile getTile()
    {
    	return tile;
    }

    public TileType getSpecial()
    {
    	return special;
    }
    
    public void setTile(Tile tile)
    {
    	this.tile = tile;
    }
    
}
