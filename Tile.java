public class Tile
{
	public static final char BLANK = '_';
    //Include blank tiles?
    private char letter;
    private int point;

    public Tile(char letter, int point)
    {
        this.letter = letter;
        this.point = point;
    }
    
    // to avoid formatting issues, a point value of 10 will be shown as a lower case 'x'
    public String toString()
    {
    	return letter == BLANK ? "  " : Character.toUpperCase(letter) + (point == 10 ? "x" : Integer.toString(point));
    }
    
    public static boolean isValidTileLetter(char letter)
    {
    	return Character.isLetter(letter) || letter == BLANK;
    }
    
    public static boolean isValidTileValue(int point)
    {
    	return point >= 0;
    }
}
