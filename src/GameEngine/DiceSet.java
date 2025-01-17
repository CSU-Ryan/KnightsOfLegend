package GameEngine;

import java.util.Random;

/**
 * Handles rolling dice.
 *
 * @see #roll(DiceType)
 */
public class DiceSet {
    private final static Random RANDOM = new Random();

    /**
     * Rolls a dice.<br>
     * <br>
     * <p>
     *     Uses {@link java.util.Random} to determine the result of a die-roll.<br>
     *     Gives a random value between 1 and the face-count (inclusive).
     * </p>
     *
     * @param diceType type of dice to roll
     * @return the roll's value
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
        return RANDOM.nextInt(faceCount) + 1;
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
