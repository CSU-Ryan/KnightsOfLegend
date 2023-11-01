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
    public int getArmor() {
        // TODO Auto-generated method stub
        return armor;
    }

    @Override
    public int getMaxHP() {
        // TODO Auto-generated method stub
        return 0;
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
        System.out.println("getArmor() for MOB: " + mob.getArmor());
    }
}
