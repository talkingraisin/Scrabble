import java.util.HashMap;
import java.util.Random;

public enum TilesFormat
{
    HASBRO, WORDS_WITH_FRIENDS, RANDOM;
    
    private static final double BLANK_PROBABILITY = 0.02;
    private static final double BLANK_BONUS_PROBABILITY = 0.5;
    private static final double VOWEL_PROBABILITY = 0.42;
    private static final double CONSONANT_PROBABILITY = 0.56;
    private static final double[] LETTER_DISTRIBUTION = new double[] { BLANK_PROBABILITY, VOWEL_PROBABILITY, CONSONANT_PROBABILITY };
    private static final char[][] VOWEL_SPREAD = new char[][] {
        { 'a', 'i' },
        { 'e' },
        { 'o' },
        { 'u' }
    };
    private static final double[] VOWEL_DISTRIBUTION = new double[] {
        ((double) 9+9)/42,
        ((double) 12)/42,
        ((double) 8)/42,
        ((double) 4)/42
    };
    private static final int VOWEL_VALUE = 1;
    private static final double[] BONUS_DISTRIBUTION = new double[] { 0.1, 0.45, 0.33, 0.12 };
    private static final char[][] CONSONANT_SPREAD = new char[][] {
        { 'n', 'r', 't', 'l', 's' },
        { 'd', 'g'},
        { 'b', 'c', 'm', 'p' },
        { 'f', 'h', 'v', 'w', 'y' },
        { 'k' },
        { 'j', 'x' },
        { 'q', 'z' }
    };
    private static final double[] CONSONANT_DISTRIBUTION = new double[] {
        ((double) 26)/56,
        ((double) 7)/56,
        ((double) 8)/56,
        ((double) 10)/56,
        ((double) 1)/56,
        ((double) 2)/56,
        ((double) 2)/56,
    };
    private static final int[] CONSONANT_VALUES = new int[] { 1, 2, 3, 4, 5, 8, 10 };
    private static final int MIN_TILES = 90;
    private static final int MAX_EXTRA = 20;
    
    private static final String[] HASBRO_TILES_FORMAT = new String[] { 
        "100", 
        "0 _ 2", 
        "1 e 12 a 9 i 9 o 8 n 6 r 6 t 6 l 4 s 4 u 4",
        "2 d 4 g 3",
        "3 b 2 c 2 m 2 p 2",
        "4 f 2 h 2 v 2 w 2 y 2",
        "5 k 1",
        "8 j 1 x 1",
        "10 q 1 z 1"
    };
    
    private static final String[] WORDS_WITH_FRIENDS_TILES_FORMAT = new String[] { 
        "104", 
        "0 _ 2", 
        "1 a 9 s 5 e 13 t 7 i 8 o 8 r 6",
        "2 d 5 n 5 l 4 u 4",
        "3 g 3 y 2 h 4",
        "4 m 2 p 2 b 2 w 2 c 2 f 2",
        "5 v 2 k 1",
        "8 x 1",
        "10 j 1 q 1 z 1"
    };
    
    private static String[] createRandomTilesFormat()
    {
        Random r = new Random(System.currentTimeMillis());
        int numTiles = MIN_TILES + r.nextInt(MAX_EXTRA + 1);
        HashMap<Integer, HashMap<Character, Integer>> tiles = new HashMap<Integer, HashMap<Character, Integer>>();
        for (int i = 0; i < numTiles; ++i)
        {
            int letterChoice = chooseFromDistr(LETTER_DISTRIBUTION, r);
            if (letterChoice == 0) // BLANK
            {
                int pointVal = (r.nextDouble() < BLANK_BONUS_PROBABILITY) ? 1 : 0;
                addLetterToTiles(tiles, Tile.BLANK, pointVal);
            }
            else if (letterChoice == 1) // VOWEL
            {
                int pointVal = VOWEL_VALUE + chooseBonusValue(r);
                int index = chooseFromDistr(VOWEL_DISTRIBUTION, r);
                addLetterToTiles(tiles, chooseRandomChar(VOWEL_SPREAD[index], r), pointVal);               
            }
            else if (letterChoice == 2) // CONSONANT
            {
                int index = chooseFromDistr(CONSONANT_DISTRIBUTION, r);
                int pointVal = CONSONANT_VALUES[index] + chooseBonusValue(r);
                addLetterToTiles(tiles, chooseRandomChar(CONSONANT_SPREAD[index], r), pointVal);
            }
            else // HUH?
            {
                throw new RuntimeException("Unexpected Letter Type");
            }
        }
        
        String[] tilesFormat = new String[tiles.size() + 1];
        tilesFormat[0] = String.valueOf(numTiles);
        
        int formatLine = 1;
        for (int pointVal : tiles.keySet())
        {
            StringBuilder b = new StringBuilder();
            b.append(pointVal);
            b.append(' ');
            HashMap<Character, Integer> letterCounts = tiles.get(pointVal);
            for (char c : letterCounts.keySet())
            {
                b.append(c);
                b.append(' ');
                b.append(letterCounts.get(c));
                b.append(' ');
            }
            tilesFormat[formatLine++] = b.toString();
        }
        
        return tilesFormat;
    }
    
    private static int chooseFromDistr(double[] distr, Random r)
    {
        int index = 0;
        double sum = distr[0];
        double d = r.nextDouble();
        while (sum < d)
        {
            sum += distr[++index];
        }
        return index;
    }
    
    private static void addLetterToTiles(HashMap<Integer, HashMap<Character, Integer>> tiles, 
            char letter, int pointVal)
    {
        if (tiles.containsKey(pointVal))
        {
            HashMap<Character, Integer> letterCount = tiles.get(pointVal);
            if (letterCount.containsKey(letter))
            {
                letterCount.put(letter, letterCount.get(letter)+1);
            }
            else
            {
                letterCount.put(letter, 1);
            }
        }
        else
        {
            HashMap<Character, Integer> letterCount = new HashMap<Character, Integer>();
            letterCount.put(letter, 1);
            tiles.put(pointVal, letterCount);
        }
    }
    
    private static char chooseRandomChar(char[] c, Random r)
    {
        return c[r.nextInt(c.length)];
    }
    
    private static int chooseBonusValue(Random r)
    {
        return chooseFromDistr(BONUS_DISTRIBUTION, r) - 1;
    }
    
    public static String[] getTilesFormat(TilesFormat format)
    {
        switch (format)
        {
            case HASBRO: return HASBRO_TILES_FORMAT;
            case WORDS_WITH_FRIENDS: return WORDS_WITH_FRIENDS_TILES_FORMAT;
            case RANDOM: return createRandomTilesFormat();
            default: throw new RuntimeException("Unexpected Tiles Format");
        }
    }
}
