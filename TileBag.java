import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;

public class TileBag
{
	
    private Tile[] tiles;
    private int curIndex;
    
    public TileBag(String tilePath)
    {
    	int index = 0;
    	
    	// initialize the tile bag
    	FileReader fr = null;
    	Scanner scanner;
		try 
		{
			fr = new FileReader(tilePath);
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		scanner = new Scanner(fr);
    	
		// get the number of tiles
		int numTiles = scanner.nextInt();
		tiles = new Tile[numTiles];
		
		scanner.nextLine();
		while(scanner.hasNextLine())
		{
			String[] s = scanner.nextLine().split(" ");
			int pointValue = Integer.parseInt(s[0]);
			int numChars = (s.length - 1) / 2;
			
			for (int i = 0; i < numChars; i++)
			{
				String character = s[2 * i + 1];
				char c;
				if ("blank".equals(character))
					c = ' ';
				else
					c = character.charAt(0);
				
				int multiplicity = Integer.parseInt(s[2 * i + 2]);
				for (int j = 0; j < multiplicity; j++)
					tiles[index++] = new Tile(c, pointValue);
			}
		}
		
		if (numTiles != index)
			throw new RuntimeException("Invalid tile file");
		
		// now shuffle using Knuth shuffle
		Random random = new Random();
		for (int i = numTiles - 1; i > 0; i--)
		{
			int j = random.nextInt(i + 1);
			Tile temp = tiles[j];
			tiles[j] = tiles[i];
			tiles[i] = temp;
		}
		
		// lastly, initialize the current pointer to numTiles - 1
		curIndex = numTiles - 1;
    }

    public Tile draw()
    {
    	Tile ret = tiles[curIndex];
    	tiles[curIndex] = null;
    	curIndex--;
    	return ret;
    }
    
    public int size()
    {
    	return curIndex + 1;
    }
    
    // test
    public static void main(String[] args)
    {
    	TileBag tb = new TileBag("tiles");
    	int count = 0;
    	while (tb.size() > 0)
    	{
    		System.out.println(tb.draw());
    		count++;
    	}
    	
    	System.out.println("Count: " + count);
    }
}