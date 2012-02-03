import java.util.EnumSet;
import java.util.HashMap;

public enum SquareBonus // 0:normal 1:DW 2:TW 3:DL 4:TL
{
    NS("  "), DW("2W"), TW("3W"), DL("2L"), TL("3L");

    private static final HashMap<String, SquareBonus> nameToValueMap = createEnumSet();

    private static HashMap<String, SquareBonus> createEnumSet()
    {
        HashMap<String, SquareBonus> nameToValueMap = new HashMap<String, SquareBonus>();
        for (SquareBonus s : EnumSet.allOf(SquareBonus.class))
            nameToValueMap.put(s.name().toUpperCase(), s);
        return nameToValueMap;
    }

    public static SquareBonus getByName(String name)
    {
        return nameToValueMap.get(name.toUpperCase());
    }

    private final String name;

    private SquareBonus(String name)
    {
        if (name.length() != 2)
            throw new RuntimeException("Everything in the module currently assumes name is 2 characters long.");
        this.name = name;
    }

    public String toString()
    {
        return name;
    }
}
