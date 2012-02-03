
public class Square
{	
    private final SquareBonus bonus; 
    private Tile tile;
    
    public Square(SquareBonus bonus)
    {
    	this.tile = null;
    	this.bonus = bonus;
    }
    
    public Square(Tile tile, SquareBonus bonus)
    {
    	this.tile = tile;
    	this.bonus = bonus;
    }

    public Tile getTile()
    {
    	return tile;
    }

    public SquareBonus getBonus()
    {
    	return bonus;
    }
    
    public void setTile(Tile tile)
    {
    	this.tile = tile;
    }
    
    public boolean hasTile()
    {
    	return this.tile != null;
    }
}
