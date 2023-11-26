package GameObjects;

/**
 * A modifier for a MOB, changing their stats.
 */
public class Fortune implements Attributes {
    private final String name;
    private final int hpBonus;
    private final int armor;
    private final int hitModifier;
    private final DiceType dtype;

    /**
     * Constructs an empty fortune. I.e., one with no impact on stats.
     */
    public Fortune() {
        this("None", 0, 0, 0, DiceType.NONE);
    }

    /**
     * Constructs a fortune with given stat changes. (+ for increase, - for decrease).
     *
     * @param name the name of the fortune.
     * @param hpBonus the HP modifier.
     * @param armor the armor modifier.
     * @param hitModifier the hit modifier.
     */
    public Fortune(String name, int hpBonus, int armor, int hitModifier) {
        this(name, hpBonus, armor, hitModifier, DiceType.NONE);
    }

    /**
     * Constructs a fortune with given stat changes. (+ for increase, - for decrease).
     *
     * @param name the name of the fortune.
     * @param hpBonus the HP modifier.
     * @param armor the armor modifier.
     * @param hitModifier the hit modifier.
     * @param type the DiceType to change to.
     */
    public Fortune(String name, int hpBonus, int armor, int hitModifier, DiceType type) {
        this.name = name.trim();
        this.hpBonus = hpBonus;
        this.armor = armor;
        this.hitModifier = hitModifier;

        //Sh**ty Zybooks
        if (type == null) {
            this.dtype = DiceType.NONE;
        } else {
            this.dtype = type;
        }
    }

    /**
     * Creates a card displaying the fortune's stat modifiers.
     *
     * @return a string of the info card.
     */
    public String toString() {
        return "+======================+\n" +
                String.format("|%-22s|%n", getName()) +
                String.format("|HP Bonus: %+12d|%n", getMaxHP())  +
                String.format("|AC Bonus: %+12d|%n", getArmor()) +
                String.format("|Hit Bonus: %+11d|%n", getHitModifier()) +
                String.format("|Damage Adj: %10s|%n", getDamageDie())  +
                "+======================+";
    }

    /**
     * Gets the name of the fortune.
     *
     * @return a string of the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the HP modifier.
     *
     * @return an int.
     */
    @Override
    public int getMaxHP() {
        return hpBonus;
    }

    /**
     * Gets the armor modifier.
     *
     * @return an int.
     */
    @Override
    public int getArmor() {
        return armor;
    }

    /**
     * Gets the hit modifier.
     *
     * @return an int.
     */
    @Override
    public int getHitModifier() {
        return hitModifier;
    }

    /**
     * gets the type of damage die.
     *
     * @return a DiceType.
     */
    @Override
    public DiceType getDamageDie() {
        return dtype;
    }

    public static void main(String[] args) {
        Fortune merlinLuck = new Fortune("Merlin Luck", 10, 5, 2, DiceType.D12);
        Fortune horusCurse = new Fortune("Curse of Horus", -5, 0, -2, DiceType.NONE);
        // BS Zybooks
        Fortune zybooksPlague = new Fortune("The plague of Zybooks", -9999, -9999, -9999, null);

        System.out.println(merlinLuck);
        System.out.println(horusCurse);
        System.out.println(zybooksPlague);
        System.out.println("damageDie = null: " + zybooksPlague.getDamageDie());
    }
    
}
