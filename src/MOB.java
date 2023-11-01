public class MOB implements Attributes {
    private String name;
    private int hpBonus;
    private int armor;
    private int hitModifier;
    private DiceType dtype;

    public MOB(String name, int hpBonus, int armor, int hitModifier, DiceType type) {
        this.name = name;
        this.hpBonus = hpBonus;
        this.armor = armor;
        this.hitModifier = hitModifier;
        this.dtype = type;
    }

    @Override
    public int getArmor() {
        // TODO Auto-generated method stub
        return 0;
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
    }
}
