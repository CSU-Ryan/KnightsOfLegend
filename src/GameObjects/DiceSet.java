package GameObjects;

import java.util.Random;

public class DiceSet {
    private final static Random rnd = new Random();

    /**
     * Rolls a dice.
     * @param diceType number of faces on dice
     * @return random int (1-faceCount)
     */
    public int roll(DiceType diceType) {
        int i = 1;
        switch (diceType) {
            case D4: i = 4;
                break;
            case D6: i = 6;
                break;
            case D8: i = 8;
                break;
            case D10: i = 10;
                break;
            case D12: i = 12;
                break;
            case D20: i = 20;
                break;
            case NONE: System.err.println("Dice.roll() called with NONE dtype.");
                break;
        }
        return rnd.nextInt(i) + 1;
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
