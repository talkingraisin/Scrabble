import java.util.NoSuchElementException;


public class InvalidTilesFormatException extends Exception
{
	public enum Cause
	{
		INVALID_FORMAT("Invalid format of file. Correct format: <point_value> <letter1> <number1> ... <letterN> <numberN>"), 
		INVALID_POINT_VALUE("Invalid point value for tile."), 
		INVALID_LETTER_SIZE("Invalid description of letter, must only be a single character."), 
		INVALID_LETTER("Invalid letter, must be A-Z."), 
		INVALID_MULTIPLICITY("Invalid number of a particular tile specified, must be greater than 0."),
		INVALID_NUMBER_OF_TILES("Number of tiles described does not match total number of tiles specified.");
		
		private final String message;
		private Cause(String message) { this.message = message; }
		public String toString() { return this.message; }
	}
	
	/**
	 * MUST GENERATE THIS LATER
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String INPUT_MISMATCH = "Invalid, non-integer, number of Tiles, Point Value, or Tile Count.";
	private static final String NO_ELEMENT = "Tiles Description is Incomplete.";
	
	private Cause c;
	
	public InvalidTilesFormatException(NumberFormatException e)
	{
		super(INPUT_MISMATCH, e);
	}
	
	public InvalidTilesFormatException(NoSuchElementException e)
	{
		super(NO_ELEMENT, e);
	}
	
	public InvalidTilesFormatException(Cause c)
	{
		this.c = c;
	}
	
	@Override
	public String getMessage()
	{
		return (c == null) ? super.getMessage() : c.toString();
	}
}
