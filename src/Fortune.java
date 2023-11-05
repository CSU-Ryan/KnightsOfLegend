public class Fortune implements Attributes {
    private final String name;
    private final int hpBonus;
    private final int armor;
    private final int hitModifier;
    private final DiceType dtype;

    public Fortune() {
        this("None", 0, 0, 0, DiceType.NONE);
    }

    public Fortune(String name, int hpBonus, int armor, int hitModifier) {
        this(name, hpBonus, armor, hitModifier, DiceType.NONE);
    }

    public Fortune(String name, int hpBonus, int armor, int hitModifier, DiceType type) {
        this.name = name;
        this.hpBonus = hpBonus;
        this.armor = armor;
        this.hitModifier = hitModifier;
        this.dtype = type;
    }

    public String toString() {
        return "+======================+\n" +
                String.format("|%-22s|%n", getName()) +
                String.format("|HP Bonus: %+12d|%n", getMaxHP())  +
                String.format("|AC Bonus: %+12d|%n", getArmor()) +
                String.format("|Hit Bonus: %+11d|%n", getHitModifier()) +
                String.format("|Damage Adj: %10s|%n", getDamageDie())  +
                "+======================+";
    }

    public String getName() {
        return name;
    }

    @Override
    public int getMaxHP() {
        return hpBonus;
    }

    @Override
    public int getArmor() {
        return armor;
    }

    @Override
    public int getHitModifier() {
        return hitModifier;
    }

    @Override
    public DiceType getDamageDie() {
        return dtype;
    }

    public static void main(String[] args) {
        Fortune merlinLuck = new Fortune("Merlin Luck", 10, 5, 2, DiceType.D12);
        Fortune horusCurse = new Fortune("Curse of Horus", -5, 0, -2, DiceType.NONE);

        System.out.println(merlinLuck);
        System.out.println(horusCurse);
    }
    
}
