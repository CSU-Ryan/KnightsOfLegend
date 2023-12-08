package GameObjects.Effects;

import GameEngine.DiceType;
import GameObjects.CSV;

/**
 * A modifier for an object, changing their stats.<br>
 * <br>
 * The fortune's stats:
 * <ul>
 *     <li>{@link #getMaxHPModifier()}<br> maxHP modifies the max HP of the affected object.</li>
 *     <li>{@link #getArmorModifier()}<br> armor modifies the armor value of the affected object.</li>
 *     <li>{@link #getAccuracyModifier()}<br> accuracyModifier changes the accuracy of the affected object.</li>
 *     <li>{@link #getDamageDie()}<br> damageDie changes the attack die of the affected object.</li>
 *     <li>{@link #getName()}<br> the name of the fortune.</li>
 * </ul>
 *
 * @see #Fortune()
 * @see #Fortune(String, int, int, int, int)
 * @see #Fortune(String, int, int, int, int, DiceType)
 */
public class Fortune implements Effect, CSV {
    private final String name;

    private final int hpModifier;
    private final int armorModifier;
    private final int accuracyModifier;
    private final int damageModifier;

    private final DiceType damageDie;

    /**
     * Constructs a neutral "empty" fortune, one which does not impact stats.
     */
    public Fortune() {
        this("None", 0, 0, 0, 0, DiceType.NONE);
    }

    /**
     * Constructs a fortune with the given stat changes.<br>
     * <br>
     * <p>
     *     Positive values increase the stat, negative decrease it.<br>
     *     Does not modify the damage die.
     * </p>
     *
     * @param name             the name of the fortune
     * @param hpModifier       the HP modifier
     * @param armor            the armor modifier
     * @param accuracyModifier the accuracy modifier
     * @param damageModifier   the damage modifier
     */
    public Fortune(String name, int hpModifier, int armor, int accuracyModifier, int damageModifier) {
        this(name, hpModifier, armor, accuracyModifier, damageModifier, DiceType.NONE);
    }

    /**
     * Constructs a fortune with the given stat changes.<br>
     * <br>
     * <p>
     *     Positive values increase the stat, negative decrease it.<br>
     *     Replaces damage die with the provided one.
     * </p>
     *
     * @param name             the name of the fortune
     * @param hpModifier       the HP modifier
     * @param armor            the armor modifier
     * @param accuracyModifier the accuracy modifier
     * @param damageModifier   the damage modifier
     * @param damageDie        the replacement damageDie
     */
    public Fortune(String name, int hpModifier, int armor, int accuracyModifier, int damageModifier, DiceType damageDie) {
        this.name = name.trim();
        this.hpModifier = hpModifier;
        this.armorModifier = armor;
        this.accuracyModifier = accuracyModifier;
        this.damageModifier = damageModifier;
        this.damageDie = damageDie;
    }

    /**
     * Gets the name of the fortune.
     *
     * @return a string of the name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Gets the fortune's HP bonus.
     *
     * @return an int to add to the max HP
     */
    @Override
    public int getMaxHPModifier() {
        return hpModifier;
    }

    /**
     * Gets the fortune's HP bonus.
     *
     * @return an int to add to the armor
     */
    @Override
    public int getArmorModifier() {
        return armorModifier;
    }

    /**
     * Gets the fortune's accuracy modifier.
     *
     * @return an int to add to the accuracy
     */
    @Override
    public int getAccuracyModifier() {
        return accuracyModifier;
    }

    /**
     * Gets the fortune's damage modifier.
     *
     * @return an int to add to the damage
     */
    @Override
    public int getDamageModifier() {
        return damageModifier;
    }

    /**
     * gets the fortune's damage die.
     *
     * @return the replacement damageDie
     */
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
                String.format("|    HP Bonus: %+8d|%n", getMaxHPModifier())  +
                String.format("|    AC Bonus: %+8d|%n", getArmorModifier()) +
                String.format("|    Accuracy: %+8d|%n", getAccuracyModifier()) +
                String.format("|Damage Bonus: %+8d|%n", getDamageModifier()) +
                String.format("|  Damage Die: %8s|%n", getDamageDie())  +
                "+======================+";
    }

    /**
     * Formats the fortune's stats for storage in a CSV file.<br>
     * <br>
     * <p>
     *     Stored as:<br>
     *     <code>name,bonusHP,armor,accuracy,damage,damageDie</code>
     * </p>
     *
     * @return the fortune's stats as a CSV string
     */
    public String toCSV() {
        return String.format("%s,%d,%d,%d,%d,%s",
                getName(), getMaxHPModifier(), getArmorModifier(), getAccuracyModifier(), getDamageModifier(), getDamageDie());
    }

    public static void main(String[] args) {
        Fortune merlinLuck = new Fortune("Merlin Luck", 10, 5, 2, 0, DiceType.D12);
        Fortune horusCurse = new Fortune("Curse of Horus", -5, 0, -2, 0, DiceType.NONE);

        System.out.println(merlinLuck);
        System.out.println(horusCurse);
    }
    
}
