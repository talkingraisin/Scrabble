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
    private static final TilesFormat DEFAULT_TILES_FORMAT = TilesFormat.HASBRO;
    
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

    public static TileBag getNewBag(TilesFormat format)
    {
        try
        {
            return new TileBag(TilesFormat.getTilesFormat(format));
        }
        catch (InvalidTilesFormatException e)
        {
            throw new RuntimeException(e);
        }
    }
    
    public static TileBag getNewDefaultBag() { return getNewBag(DEFAULT_TILES_FORMAT); }

    public static TileBag getNewBagFromFile(String tileFormatFilename)
            throws FileNotFoundException, InvalidTilesFormatException
    {
        Scanner s = new Scanner(new BufferedReader(new FileReader(new File(tileFormatFilename))));
        ArrayList<String> lines = new ArrayList<String>();
        while (s.hasNextLine())
            lines.add(s.nextLine());
        return new TileBag(lines.toArray(new String[0]));
    }
    
    // Test
    public static void main(String[] args)
    {
        TileBag bag = TileBag.getNewBag(TilesFormat.RANDOM);
        System.out.println(bag.tilesRemaining());
        for (Tile t : bag.draw(bag.tilesRemaining()))
            System.out.print(t + " ");
        System.out.println();
    }
}