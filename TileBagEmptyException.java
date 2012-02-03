
public class TileBagEmptyException extends RuntimeException
{
	/**
	 * MUST GENERATE ONE LATER
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String MESSAGE = "Tile Bag Does Not Contain Any More Tiles";
	public TileBagEmptyException()
	{
		super(MESSAGE);
	}
}
