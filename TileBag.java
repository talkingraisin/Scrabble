import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class TileBag
{
	private static final String[] DEFAULT_TILE_BAG_FORMAT = new String[]
		{ "100", 
	      "0 _ 2", "1 e 12 a 9 i 9 o 8 n 6 r 6 t 6 l 4 s 4 u 4",
		  "2 d 4 g 3",
		  "3 b 2 c 2 m 2 p 2",
		  "4 f 2 h 2 v 2 w 2 y 2",
		  "5 k 1",
		  "8 j 1 x 1",
		  "10 q 1 z 1"
		};
    private ArrayList<Tile> tiles;

    protected TileBag(String[] tileBagFormat) throws InvalidTilesFormatException
    {
        if (tileBagFormat.length < 1)
            throw new InvalidTilesFormatException(new NoSuchElementException("Format is empty."));
        try
        {
            int numTiles = Integer.parseInt(tileBagFormat[0].trim());
            this.tiles = new ArrayList<Tile>(numTiles);

            for (int line = 1; line < tileBagFormat.length; ++line)
            {
                String[] tokens = tileBagFormat[line].trim().split("\\s");

                if (tokens.length % 2 == 0)
                    throw new InvalidTilesFormatException(
                            InvalidTilesFormatException.Cause.INVALID_FORMAT);

                int pointValue = Integer.parseInt(tokens[0]);
                if (!Tile.isValidTileValue(pointValue))
                    throw new InvalidTilesFormatException(
                            InvalidTilesFormatException.Cause.INVALID_POINT_VALUE);

                for (int i = 1; i < tokens.length; i += 2)
                {
                    if (tokens[i].length() != 1)
                        throw new InvalidTilesFormatException(
                                InvalidTilesFormatException.Cause.INVALID_LETTER_SIZE);
                    char c = tokens[i].charAt(0);
                    if (!Tile.isValidTileLetter(c))
                        throw new InvalidTilesFormatException(
                                InvalidTilesFormatException.Cause.INVALID_LETTER);
                    int multiplicity = Integer.parseInt(tokens[i + 1]);
                    if (multiplicity <= 0)
                        throw new InvalidTilesFormatException(
                                InvalidTilesFormatException.Cause.INVALID_MULTIPLICITY);
                    for (int j = 0; j < multiplicity; ++j)
                        this.tiles.add(new Tile(c, pointValue));
                }
            }

            if (this.tiles.size() != numTiles)
                throw new InvalidTilesFormatException(
                        InvalidTilesFormatException.Cause.INVALID_NUMBER_OF_TILES);

            Collections.shuffle(this.tiles, new Random(System.currentTimeMillis()));
        }
        catch (NumberFormatException e)
        {
            throw new InvalidTilesFormatException(e);
        }
    }

    public Tile draw()
    {
        if (tilesRemaining() == 0)
            throw new TileBagEmptyException();
        return tiles.remove(tiles.size() - 1);
    }

    public Collection<Tile> draw(int n)
    {
        ArrayList<Tile> tiles = new ArrayList<Tile>(n);
        for (int i = 0; i < n; ++i)
            tiles.add(draw());
        return tiles;
    }

    public int tilesRemaining()
    {
        return tiles.size();
    }

    public boolean isEmpty()
    {
        return tiles.isEmpty();
    }

    public void swapTiles(Collection<Tile> tiles)
    {
        int swapNum = tiles.size();
        this.tiles.addAll(tiles);
        Collections.shuffle(this.tiles, new Random(System.currentTimeMillis()));
        tiles.clear();
        for (int i = 0; i < swapNum; ++i)
            tiles.add(draw());
    }

    public static TileBag getNewDefaultBag()
    {
        try
        {
            return new TileBag(DEFAULT_TILE_BAG_FORMAT);
        }
        catch (InvalidTilesFormatException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static TileBag getNewBagFromFile(String tileFormatFilename)
            throws FileNotFoundException, InvalidTilesFormatException
    {
        Scanner s = new Scanner(new BufferedReader(new FileReader(new File(tileFormatFilename))));
        ArrayList<String> lines = new ArrayList<String>();
        while (s.hasNextLine())
            lines.add(s.nextLine());
        return new TileBag(lines.toArray(new String[0]));
    }
}