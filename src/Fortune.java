public class Fortune implements Attributes {
    private String name;
    private int hpBonus;
    private int armor;
    private int hitModifier;
    private DiceType dtype;

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
        //TODO: implement
        return "";
    }

    public String getName() {
        //TODO: implement
        return "";
    }

    @Override
    public int getArmor() {
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
        Fortune ftn = new Fortune("Merlin Luck", 10, 5, 2, DiceType.D12);
        System.out.println("TESTING Armor in fortune " + ftn.getArmor());
    }
    
}
