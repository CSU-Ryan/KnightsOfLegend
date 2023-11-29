package GameObjects.MobileObjects;

import GameEngine.DiceType;
import GameObjects.Effects.Fortune;

/**
 * A knight which can join the player's party and fight for them.
 */
public class Knight extends MOB {

    private Fortune activeFortune;
    protected final int id;
    protected int xp;


    /**
     * Constructs a knight from the given stats.
     *
     * @param id          the unique ID number
     * @param name        the name
     * @param maxHP       the max HP
     * @param armor       the armor class
     * @param accuracy    the accuracy
     * @param damageDie   the die for rolling damage
     * @param xp          the current XP
     */
    public Knight(int id, String name, int maxHP, int armor, int accuracy, DiceType damageDie, int xp) {
        super(name, maxHP, armor, accuracy, damageDie);
        this.id = id;
        this.xp = xp;
        this.activeFortune = new Fortune();
    }

    /**
     * Gets the knight's ID.
     *
     * @return the knight's ID.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Gets the knight's max HP.
     *
     * @return the knight's max HP.
     */
    @Override
    public int getMaxHP() {
        return super.getMaxHP() + getActiveFortune().getMaxHP();
    }

    /**
     * Gets the knight's armor class.
     *
     * @return the knight's armor level.
     */
    @Override
    public int getArmor() {
        return super.getArmor() + getActiveFortune().getArmor();
    }

    /**
     * Gets the knight's accuracy.
     *
     * @return the knight's accuracy.
     */
    @Override
    public int getAccuracy() {
        return super.getAccuracy() + getActiveFortune().getAccuracy();
    }

    /**
     * Gets the knight's damage die.
     *
     * @return the knight's damage DiceType.
     */
    @Override
    public DiceType getDamageDie() {
        DiceType fortuneDie = getActiveFortune().getDamageDie();

        return (fortuneDie != DiceType.NONE) ? fortuneDie : super.getDamageDie();
    }

    /**
     * Gets the knight's fortune.
     *
     * @return the knight's active fortune.
     */
    public Fortune getActiveFortune() {
        return activeFortune;
    }

    /**
     * Sets the knight's fortune.
     *
     * @param fortune the fortune to give the knight.
     */
    public void setActiveFortune(Fortune fortune) {
        activeFortune = fortune;
    }

    /**
     * Gets the knight's current XP.
     *
     * @return the knight's XP.
     */
    public int getXP() {
        return xp;
    }

    /**
     * Adds to the current XP level.
     *
     * @param xp the XP to add.
     */
    public void addXP(int xp) {
        this.xp += xp;
    }

    /**
     * Creates a card displaying the knight's current stats.
     *
     * @return a string of the info card.
     */
    @Override
    public String toString() {
        return "+============================+\n" +
                String.format("| %-27s|%n", getName()) +
                String.format("| id: %-23d|%n", getId()) +
                "|                            |\n" +
                String.format("| Health: %-6d  XP: %-7d|%n", getHP(), getXP())  +
                String.format("|  Power: %-6s  Armor: %-4d|%n", getDamageDie(), getArmor()) +
                "|                            |\n" +
                "+============================+";
    }

    /**
     * Formats the knight's stats for storage in a CSV file.<br>
     * <br>
     * <p>
     *     Stored as:<br>
     *     <code>name,maxHP,armor,accuracy,damageDie,xp</code>
     * </p>
     *
     * @return the knight's stats as a CSV string
     */
    @Override
    public String toCSV() {
        return super.toCSV() + "," + getXP();
    }

    public static void main(String[] args) {

        Knight knight1 = new Knight(1, "knight1", 10, 1, -1, DiceType.D20, 0);

        Fortune merlinLuck = new Fortune("Merlin Luck", 10, 5, 2, DiceType.D12);
        Fortune horusCurse = new Fortune("Curse of Horus", -5, 0, -2, DiceType.NONE);

        System.out.println("knight1 CSV: " + knight1.toCSV());
        System.out.println();

        System.out.println(knight1);
        System.out.println("Accuracy: " + knight1.getAccuracy());
        System.out.println("MaxHP: " + knight1.getMaxHP());
        System.out.println("Damage: " + knight1.getDamage());
        System.out.println();

        System.out.println("Damaging for 5HP:");
        knight1.addDamage(5);
        System.out.println(knight1);
        System.out.println();

        System.out.println("Damaging for 7HP:");
        knight1.addDamage(7);
        System.out.println(knight1);
        System.out.println();

        System.out.println("Resetting knight1 damage");
        knight1.resetDamage();
        System.out.println(knight1);
        System.out.println();

        System.out.println("Adding 37 XP:");
        knight1.addXP(37);
        System.out.println(knight1);
        System.out.println();

        System.out.println("Adding Curse of Horus:");
        System.out.println(horusCurse);
        knight1.setActiveFortune(horusCurse);
        System.out.println(knight1);
        System.out.println("Fortune: " + knight1.getActiveFortune().getName());
        System.out.println();

        System.out.println("Adding Merlin Luck:");
        System.out.println(merlinLuck);
        knight1.setActiveFortune(merlinLuck);
        System.out.println(knight1);
        System.out.println("Fortune: " + knight1.getActiveFortune().getName());
        System.out.println();
    }
    
}
