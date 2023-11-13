package GameObjects;

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
        //TODO: Sh**ty zybooks
        if (type == null) {
            this.dtype = DiceType.NONE;
        } else {
            this.dtype = type;
        }
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
        //TODO:
        // BS Zybooks
        Fortune zybooksPlague = new Fortune("The plague of Zybooks", -9999, -9999, -9999, null);

        System.out.println(merlinLuck);
        System.out.println(horusCurse);
        System.out.println(zybooksPlague);
        System.out.println("damageDie = null: " + zybooksPlague.getDamageDie());
    }
    
}
