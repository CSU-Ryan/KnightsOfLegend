package GameEngine;

/**
 * Enumerates the possible types of dice (i.e. faces on die).
 */
public enum DiceType {
    D4, D6, D8, D10, D12, D20, NONE;

    /**
     * The name of the dice type. If NONE, returns `-`.
     *
     * @return a string of the dice type.
     */
    @Override
    public String toString() {
        return (this != NONE) ? this.name() : "-";
    }

    /**
     * Turns a string into a DiceType. `-` becomes NONE.
     *
     * @param name string of DiceType
     * @return the corresponding DiceType
     */
    public static DiceType typeOf(String name) {
        return (name.equals("-")) ? DiceType.NONE : valueOf(name);
    }

    public static void main(String[] args) {
        System.out.println("  D4: " + D4);
        System.out.println("  D6: " + D6);
        System.out.println("  D8: " + D8);
        System.out.println(" D10: " + D10);
        System.out.println(" D12: " + D12);
        System.out.println(" D20: " + D20);
        System.out.println("NONE: " + NONE);
    }
}