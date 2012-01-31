public class Square
{
    private int special; // DW, TW, DL, TL
    private Tile tile;

    public Square(Tile tile, int special)
    {
    	this.tile = tile;
	this.special = special;
    }

    public Tile getTile()
    {
    	return tile;
    }

    public int getSpecial()
    {
    	return special;
    }
}
