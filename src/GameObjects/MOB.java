package GameObjects;

public class MOB implements Attributes {
    private final String name; // The name of the MOB.
    protected final int maxHP; // The maxHP of the MOB.
    protected final int armor; // The armor rating of the MOB.
    protected int damage; // The amount of damage the MOB has taken.
    protected final int hitModifier; // The hitModifier of the MOB.
    protected final DiceType damageDie; // The type of damage die used if the mob successfully strikes the target.


    public MOB(String name, int maxHP, int armor, int hitModifier, DiceType damageDie) {
        this.name = name;
        this.maxHP = maxHP;
        this.armor = armor;
        this.hitModifier = hitModifier;
        // TODO:
        //  I hate zybooks with all my being. I have to add a stupid null check because they unit test my functions
        //  outside the scope of my own code.because even if I want to handle the program in a slightly smarter or more
        //  sensible way I am punished because they feel the need to unit test my code, despite this project supposedly
        //  being there so I can use my own implementation skills, giving me some actual freedom.
        //  Straight BS.
        if (damageDie == null) {
            this.damageDie = DiceType.NONE;
        } else {
            this.damageDie = damageDie;
        }
    }

    /**
     * Builds a MOB Card for easy printing of the stats.
     */
    @Override
    public String toString() {
        return "+============================+\n" +
                String.format("| %-27s|%n", getName()) +
                "|                            |\n" +
                String.format("|         Health: %-10d |%n", getHP())  +
                String.format("|  Power: %-6s  Armor: %-4d|%n", getDamageDie().toString(), getArmor()) +
                "|                            |\n" +
                "+============================+";
    }

    public MOB copy() {
        return new MOB(name, maxHP, armor, hitModifier, damageDie);
    }

    public MOB copy(String name) {
        return new MOB(name, maxHP, armor, hitModifier, damageDie);
    }

    /**
     * Returns the generic name of the MOB
     * @return the name of the MOB
     */
    public String getName() {
        return name;
    }

    /**
     * Returns current HP of the MOB.
     * @return current HP of the MOB
     */
    public int getHP() {
        return getMaxHP() - getDamage();
    }

    @Override
    public int getMaxHP() {
        return maxHP;
    }

    @Override
    public int getArmor() {
        return armor;
    }

    /**
     * Gets the amount of damage the MOB has taken.
     * @return the amount of damage the MOB has taken
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Adds damage to the mobs overall damage.
     * @param hit the int amount to add
     */
    //TODO: this might be the cause of all your pain
    public void addDamage(int hit) {
        damage = Math.min(maxHP, damage + hit);
    }

    /**
     * Resets the damage taken to 0.
     */
    public void resetDamage() {
        damage = 0;
    }

    @Override
    public DiceType getDamageDie() {
        return damageDie;
    }

    @Override
    public int getHitModifier() {
        return hitModifier;
    }
    
    public static void main(String[] args) {
        
        MOB mob1 = new MOB("mob1", 10, 1, -1, DiceType.D20);
        MOB mob2 = mob1.copy("mob2");

        System.out.println(mob1);
        System.out.println("hitMod: " + mob1.getHitModifier());
        System.out.println("MaxHP: " + mob1.getMaxHP());
        System.out.println("Damage: " + mob1.getDamage());
        System.out.println();

        System.out.println("Damaging for 5HP:");
        mob1.addDamage(5);
        System.out.println(mob1);
        System.out.println();

        System.out.println("Damaging for 7HP:");
        mob1.addDamage(7);
        System.out.println(mob1);
        System.out.println();

        System.out.println("Earlier copy:");
        System.out.println(mob2);
        System.out.println();

        System.out.println("Resetting mob1 damage");
        mob1.resetDamage();
        System.out.println(mob1);
    }
}
