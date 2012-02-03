
public class InvalidBoardFormatException extends Exception
{
    /**
     * NEED TO GENERATE THIS
     */
    private static final long serialVersionUID = 1L;

    public enum Cause
    {
        INVALID_ROW_COUNT("Number of rows specified in format is invalid."), 
        INVALID_COL_COUNT("Number of columns specified in format is invalid."), 
        INVALID_BONUS("A bonus specified is invalid.");
        
        private final String message;
        private Cause(String message) { this.message = message; }
        public String toString() { return this.message; }
    }
    
    private Cause c;
    public InvalidBoardFormatException(Cause c)
    {
        super();
        this.c = c;
    }
    
    @Override
    public String getMessage()
    {
        return c.toString();
    }
}
