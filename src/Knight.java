public class Knight extends MOB {
    private Fortune activeFortune;
    protected int id;
    protected int xp;


    public Knight(int id, String name, int hp, int armor, int hitModifier, DiceType damageDie, int xp) {
        //TODO: implement constructor
        super(name, hp, armor, hitModifier, damageDie);
    }

    @Override
    public String toString() {
        //TODO: implement
        return "";
    }

    public String toCSV() {
        //TODO: implement
        return "";
    }

    public Integer getId() {
        //TODO: implement
        return null;
    }

    public int getXP() {
        //TODO: implement
        return -1;
    }

    public void addXP(int xp) {
        //TODO: implement
    }

    @Override
    public int getMaxHP() {
        //TODO: implement
        return -1;
    }

    @Override
    public int getArmor() {
        //TODO: implement
        return -1;
    }

    @Override
    public DiceType getDamageDie() {
        //TODO: implement
        return null;
    }

    @Override
    public int getHitModifier() {
        //TODO: implement
        return -1;
    }

    public void setActiveFortune(Fortune fortune) {
        //TODO: implement
    }

    public Fortune getActiveFortune() {
        //TODO: implement
        return null;
    }

    public static void main(String[] args) {
        
    }
    
}
