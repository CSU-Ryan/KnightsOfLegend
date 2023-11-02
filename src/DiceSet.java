import java.util.Random;

public class DiceSet {
    private static Random rnd = new Random();

    /**
     * Rolls a dice.
     * @param diceType number of faces on dice
     * @return random int (1-faceCount)
     */
    public int roll(DiceType diceType) {
        int myval = 0;
        switch(diceType) {
            case D4:
                myval = rnd.nextInt(4) + 1;
            break;
            case D6:
                myval = rnd.nextInt(6) + 1;
            break;
            case D8:
                myval = rnd.nextInt(8) + 1;
            break;
            case D10:
                myval = rnd.nextInt(10) + 1;
            break;
            case D12:
                myval = rnd.nextInt(12) + 1;
            break;
            case D20:
                myval = rnd.nextInt(20) + 1;
            break;
            default:
                System.err.println("Dice.roll() called with no dtype.");
                myval = 0;
        }


        return myval;
    }

    public static void main(String[] args) {
        DiceSet dice = new DiceSet();

        System.out.println("TESTING DiceSet (D4): " + dice.roll(DiceType.D4));
        System.out.println("TESTING DiceSet (D6): " + dice.roll(DiceType.D6));
        System.out.println("TESTING DiceSet (D8): " + dice.roll(DiceType.D8));
        System.out.println("TESTING DiceSet (D10): " + dice.roll(DiceType.D10));
        System.out.println("TESTING DiceSet (D12): " + dice.roll(DiceType.D12));
        System.out.println("TESTING DiceSet (D20): " + dice.roll(DiceType.D20));
    }

    
}
