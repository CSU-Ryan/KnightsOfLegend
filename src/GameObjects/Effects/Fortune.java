package GameObjects.Effects;

import GameObjects.MobileObjects.Attributes;
import GameEngine.DiceType;

/**
 * A modifier for an object, changing their stats.<br>
 * <br>
 * The fortune's stats:
 * <ul>
 *     <li>{@link #getMaxHP()}<br> maxHP modifies the max HP of the affected object.</li>
 *     <li>{@link #getArmor()}<br> armor modifies the armor value of the affected object.</li>
 *     <li>{@link #getAccuracy()}<br> accuracyModifier changes the accuracy of the affected object.</li>
 *     <li>{@link #getDamageDie()}<br> damageDie changes the attack die of the affected object.</li>
 *     <li>{@link #getName()}<br> the name of the fortune.</li>
 * </ul>
 *
 * @see #Fortune()
 * @see #Fortune(String, int, int, int)
 * @see #Fortune(String, int, int, int, DiceType)
 */
public class Fortune implements Attributes {
    private final String name;
    private final int hpBonus;
    private final int armor;
    private final int accuracy;
    private final DiceType damageDie;

    /**
     * Constructs a neutral "empty" fortune, one which does not impact stats.
     */
    public Fortune() {
        this("None", 0, 0, 0, DiceType.NONE);
    }

    /**
     * Constructs a fortune with the given stat changes.<br>
     * <br>
     * <p>
     *     Positive values increase the stat, negative decrease it.<br>
     *     Does not modify the damage die.
     * </p>
     *
     * @param name        the name of the fortune
     * @param hpBonus     the HP modifier
     * @param armor       the armor modifier
     * @param accuracy    the accuracy modifier
     */
    public Fortune(String name, int hpBonus, int armor, int accuracy) {
        this(name, hpBonus, armor, accuracy, DiceType.NONE);
    }

    /**
     * Constructs a fortune with the given stat changes.<br>
     * <br>
     * <p>
     *     Positive values increase the stat, negative decrease it.<br>
     *     Replaces damage die with given one.
     * </p>
     *
     * @param name        the name of the fortune
     * @param hpBonus     the HP modifier
     * @param armor       the armor modifier
     * @param accuracy    the accuracy modifier
     * @param damageDie   the replacement damageDie
     */
    public Fortune(String name, int hpBonus, int armor, int accuracy, DiceType damageDie) {
        this.name = name.trim();
        this.hpBonus = hpBonus;
        this.armor = armor;
        this.accuracy = accuracy;

        //Sh**ty Zybooks
        this.damageDie = (damageDie != null) ? damageDie : DiceType.NONE;
    }

    /**
     * Gets the name of the fortune.
     *
     * @return a string of the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the fortune's HP bonus.
     *
     * @return an int to add to the max HP
     */
    @Override
    public int getMaxHP() {
        return hpBonus;
    }

    /**
     * Gets the fortune's HP bonus.
     *
     * @return an int to add to the armor
     */
    @Override
    public int getArmor() {
        return armor;
    }

    /**
     * Gets the fortune's accuracy modifier.
     *
     * @return an into to add to the accuracy
     */
    @Override
    public int getAccuracy() {
        return accuracy;
    }

    /**
     * gets the fortune's damage die.
     *
     * @return the replacement damageDie
     */
    @Override
    public DiceType getDamageDie() {
        return damageDie;
    }

    /**
     * Creates a card displaying the fortune's stat modifiers.
     *
     * @return a string of the info card
     */
    @Override
    public String toString() {
        return "+======================+\n" +
                String.format("|%-22s|%n", getName()) +
                String.format("|HP Bonus: %+12d|%n", getMaxHP())  +
                String.format("|AC Bonus: %+12d|%n", getArmor()) +
                String.format("|Accuracy: %+12d|%n", getAccuracy()) +
                String.format("|Damage Adj: %10s|%n", getDamageDie())  +
                "+======================+";
    }

    /**
     * Formats the fortune's stats for storage in a CSV file.<br>
     * <br>
     * <p>
     *     Stored as:<br>
     *     <code>name,bonusHP,armor,accuracy,damageDie</code>
     * </p>
     *
     * @return the fortune's stats as a CSV string
     */
    public String toCSV() {
        return String.format("%s,%d,%d,%d,%s",
                getName(), getMaxHP(), getArmor(), getAccuracy(), getDamageDie());
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
