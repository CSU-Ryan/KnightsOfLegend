package GameObjects;

import java.util.Random;

/**
 * Handles manipulating and using dice.
 */
public class DiceSet {
    private final static Random rnd = new Random();

    /**
     * Rolls a dice.
     *
     * @param diceType type of dice to roll
     * @return random int within [1, faceCount]
     */
    public int roll(DiceType diceType) {
        int faceCount = 1;
        switch (diceType) {
            case D4: faceCount = 4;
                break;
            case D6: faceCount = 6;
                break;
            case D8: faceCount = 8;
                break;
            case D10: faceCount = 10;
                break;
            case D12: faceCount = 12;
                break;
            case D20: faceCount = 20;
                break;
            case NONE: System.err.println("Dice.roll() called with NONE DiceType.");
                break;
        }
        return rnd.nextInt(faceCount) + 1;
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
