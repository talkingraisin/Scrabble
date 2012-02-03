import java.util.Random;

public enum BoardFormat
{
    HASBRO, WORDS_WITH_FRIENDS, WORDFEUD, RANDOM;
    
    private static final int DW = 12;
    private static final int TW = 8;
    private static final int DL = 24;
    private static final int TL = 16;
    private static final double TOTAL = Board.ROWS * Board.COLS;
    private static final double[] DISTRIBUTION = new double[] {
        (TOTAL-DW-TW-DL-TL)/TOTAL,
        DW/TOTAL,
        TW/TOTAL,
        DL/TOTAL,
        TL/TOTAL
    };
    
    private static final String[] HASBRO_BOARD_FORMAT = new String[] {
        "TW NS NS DL NS NS NS TW NS NS NS DL NS NS TW",
        "NS DW NS NS NS TL NS NS NS TL NS NS NS DW NS",
        "NS NS DW NS NS NS DL NS DL NS NS NS DW NS NS",
        "DL NS NS DW NS NS NS DL NS NS NS DW NS NS DL",
        "NS NS NS NS DW NS NS NS NS NS DW NS NS NS NS",
        "NS TL NS NS NS TL NS NS NS TL NS NS NS TL NS",
        "NS NS DL NS NS NS DL NS DL NS NS NS DL NS NS",
        "TW NS NS DL NS NS NS DW NS NS NS DL NS NS TW",
        "NS NS DL NS NS NS DL NS DL NS NS NS DL NS NS",
        "NS TL NS NS NS TL NS NS NS TL NS NS NS TL NS",
        "NS NS NS NS DW NS NS NS NS NS DW NS NS NS NS",
        "DL NS NS DW NS NS NS DL NS NS NS DW NS NS DL",
        "NS NS DW NS NS NS DL NS DL NS NS NS DW NS NS",
        "NS DW NS NS NS TL NS NS NS TL NS NS NS DW NS",
        "TW NS NS DL NS NS NS TW NS NS NS DL NS NS TW"
    };
    
    private static final String[] WORDS_WITH_FRIENDS_BOARD_FORMAT = new String[] {
        "NS NS NS TW NS NS TL NS TL NS NS TW NS NS NS",
        "NS NS DL NS NS DW NS NS NS DW NS NS DL NS NS",
        "NS DL NS NS DL NS NS NS NS NS DL NS NS DL NS",
        "TW NS NS DL NS NS NS DW NS NS NS TL NS NS TW",
        "NS NS DL NS NS NS DL NS DL NS NS NS DL NS NS",
        "NS DW NS NS NS TL NS NS NS TL NS NS NS DW NS",
        "TL NS NS NS DL NS NS NS NS NS DL NS NS NS TL",
        "NS NS NS DW NS NS NS NS NS NS NS DW NS NS NS",
        "TL NS NS NS DL NS NS NS NS NS DL NS NS NS TL",
        "NS DW NS NS NS TL NS NS NS TL NS NS NS DW NS",
        "NS NS DL NS NS NS DL NS DL NS NS NS DL NS NS",
        "TW NS NS DL NS NS NS DW NS NS NS TL NS NS TW",
        "NS DL NS NS DL NS NS NS NS NS DL NS NS DL NS",
        "NS NS DL NS NS DW NS NS NS DW NS NS DL NS NS",
        "NS NS NS TW NS NS TL NS TL NS NS TW NS NS NS"
    };
    
    private static final String[] WORDFEUD_BOARD_FORMAT = new String[] {
        "TL NS NS NS TW NS NS DL NS NS TW NS NS NS TL",
        "NS DL NS NS NS TL NS NS NS TL NS NS NS DL NS",
        "NS NS DW NS NS NS DL NS DL NS NS NS DW NS NS",
        "NS NS NS TL NS NS NS DW NS NS NS TL NS NS NS",
        "TW NS NS NS DW NS DL NS DL NS DW NS NS NS TW",
        "NS TL NS NS NS TL NS NS NS TL NS NS NS TL NS",
        "NS NS DL NS DL NS NS NS NS NS DL NS DL NS NS",
        "DL NS NS DW NS NS NS NS NS NS NS DW NS NS DL",
        "NS NS DL NS DL NS NS NS NS NS DL NS DL NS NS",
        "NS TW NS NS NS TL NS NS NS TL NS NS NS TL NS",
        "TW NS NS NS DW NS DL NS DL NS DW NS NS NS TW",
        "NS NS NS TL NS NS NS DW NS NS NS TL NS NS NS",
        "NS NS DW NS NS NS DL NS DL NS NS NS DW NS NS",
        "NS DL NS NS NS TL NS NS NS TL NS NS NS DL NS",
        "TL NS NS NS TW NS NS DL NS NS TW NS NS NS TL"
    };
    
    private static String[] createRandomBoardFormat()
    {
        String[] boardFormat = new String[Board.ROWS];
        Random r = new Random(System.currentTimeMillis());
        SquareBonus[] bonuses = SquareBonus.values();
        for (int row = 0; row < Board.ROWS; ++row)
        {
            StringBuilder b = new StringBuilder();
            for (int col = 0; col < Board.COLS; ++col)
            {
                int i = 0;
                double d = r.nextDouble();
                double sum = DISTRIBUTION[0];
                while (sum < d)
                    sum += DISTRIBUTION[++i];
                b.append(bonuses[i].name());
                b.append(' ');
            }
            boardFormat[row] = b.toString();
        }
        return boardFormat;
    }
    
    public static String[] getBoardFormat(BoardFormat format)
    {
        switch (format)
        {
            case HASBRO: return HASBRO_BOARD_FORMAT;
            case WORDS_WITH_FRIENDS: return WORDS_WITH_FRIENDS_BOARD_FORMAT;
            case WORDFEUD: return WORDFEUD_BOARD_FORMAT;
            case RANDOM: return createRandomBoardFormat();
            default: throw new RuntimeException("Unexpected Board Format");
        }
    }
}
