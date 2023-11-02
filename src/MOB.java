public class MOB implements Attributes {
    private final String name; // The name of the MOB.
    protected int maxHP; // The maxHP of the MOB.
    protected int armor; // The armor rating of the MOB.
    protected int damage; // The amount of damage the MOB has taken.
    protected int hitModifier; // The hitModifier of the MOB.
    protected DiceType damageDie; // The type of damage die used if the mob successfully strikes the target.


    public MOB(String name, int hp, int armor, int hitModifier, DiceType damageDie) {
        this.name = name;
        this.maxHP = hp;
        this.armor = armor;
        this.hitModifier = hitModifier;
        this.damageDie = damageDie;
    }

    @Override
    public String toString() {
        //TODO implement function
        return "TODO";
    }

    public MOB copy() {
        //TODO implement function
        return this;
    }

    /**
     * Returns the generic name of the MOB
     * @return the name of the MOB
     */
    public String getName() {
        //TODO implement function
        return "TODO";
    }

    /**
     * Returns current HP of the MOB.
     * @return current HP of the MOB
     */
    public int getHP() {
        //TODO implement function
        return -1;
    }

    @Override
    public int getMaxHP() {
        // TODO Auto-generated method stub
        return -1;
    }

    @Override
    public int getArmor() {
        // TODO Auto-generated method stub
        return -1;
    }

    /**
     * Gets the amount of damage the MOB has taken.
     * @return the amount of damage the MOB has taken
     */
    public int getDamage() {
        //TODO implement function
        return -1;
    }

    /**
     * Adds damage to the mobs overall damage.
     * @param damage the int amount to add
     */
    public void addDamage(int damage) {
        //TODO implement function
    }

    /**
     * Resets the damage taken to 0.
     */
    public void resetDamage() {
        //TODO implement function
    }

    @Override
    public DiceType getDamageDie() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getHitModifier() {
        // TODO Auto-generated method stub
        return 0;
    }
    
    public static void main(String[] args) {
        
        MOB mob = new MOB("unnamed", 10, 1, -1, DiceType.D20);
        System.out.println("toString(): " + mob);
        System.out.println("copy(): " + mob.copy());
        System.out.println("getName(): " + mob.getName());
        System.out.println("getHP(): " + mob.getHP());
        System.out.println("getMaxHP(): " + mob.getMaxHP());
        System.out.println("getArmor(): " + mob.getArmor());
        System.out.println("getDamage(): " + mob.getDamage());
        System.out.println("getDamageDie(): " + mob.getDamageDie());
        System.out.println("getHitModifier(): " + mob.getHitModifier());
    }
}
