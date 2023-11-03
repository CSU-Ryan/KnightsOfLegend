import java.util.Random;

public class DiceSet {
    private static Random rnd = new Random();

    /**
     * Rolls a dice.
     * @param diceType number of faces on dice
     * @return random int (1-faceCount)
     */
    public int roll(DiceType diceType) {
        return switch (diceType) {
            case D4 -> rnd.nextInt(4) + 1;
            case D6 -> rnd.nextInt(6) + 1;
            case D8 -> rnd.nextInt(8) + 1;
            case D10 -> rnd.nextInt(10) + 1;
            case D12 -> rnd.nextInt(12) + 1;
            case D20 -> rnd.nextInt(20) + 1;
            case NONE -> {
                System.err.println("Dice.roll() called with NONE dtype.");
                yield 0;
            }
        };
    }

    public static void main(String[] args) {
        DiceSet dice = new DiceSet();

        System.out.println("TESTING DiceSet (D4): " + dice.roll(DiceType.D4));
        System.out.println("TESTING DiceSet (D6): " + dice.roll(DiceType.D6));
        System.out.println("TESTING DiceSet (D8): " + dice.roll(DiceType.D8));
        System.out.println("TESTING DiceSet (D10): " + dice.roll(DiceType.D10));
        System.out.println("TESTING DiceSet (D12): " + dice.roll(DiceType.D12));
        System.out.println("TESTING DiceSet (D20): " + dice.roll(DiceType.D20));
        System.out.println("TESTING DiceSet (NONE): " + dice.roll(DiceType.NONE));
    }

    
}
