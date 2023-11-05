public class Knight extends MOB {
    private Fortune activeFortune;
    protected final int id;
    protected int xp;


    public Knight(int id, String name, int maxHP, int armor, int hitModifier, DiceType damageDie, int xp) {
        super(name, maxHP, armor, hitModifier, damageDie);
        this.id = id;
        this.xp = xp;
        this.activeFortune = new Fortune();
    }

    @Override
    public String toString() {
        return "+============================+\n" +
                String.format("| %-27s|%n", getName()) +
                String.format("| id: %-23d|%n", getId()) +
                "|                            |\n" +
                String.format("| Health: %-6d     XP: %-4d|%n", getHP(), getXP())  +
                String.format("|  Power: %-6s  Armor: %-4d|%n", getDamageDie(), getArmor()) +
                "|                            |\n" +
                "+============================+";
    }

    public String toCSV() {
        return String.format("%s,%d,%d,%d,%s,%d",
                getName(), getMaxHP(), getArmor(), getHitModifier(), getDamageDie(), getXP());
    }

    public Integer getId() {
        return id;
    }

    public int getXP() {
        return xp;
    }

    public void addXP(int xp) {
        this.xp += xp;
    }

    @Override
    public int getMaxHP() {
        return super.getMaxHP() + getActiveFortune().getMaxHP();
    }

    @Override
    public int getArmor() {
        return super.getArmor() + getActiveFortune().getArmor();
    }

    @Override
    public DiceType getDamageDie() {
        DiceType fortuneDie = getActiveFortune().getDamageDie();

        return (fortuneDie != DiceType.NONE) ? fortuneDie : super.getDamageDie();
    }

    @Override
    public int getHitModifier() {
        return super.getHitModifier() + getActiveFortune().getHitModifier();
    }

    public void setActiveFortune(Fortune fortune) {
        activeFortune = fortune;
    }

    public Fortune getActiveFortune() {
        return activeFortune;
    }

    public static void main(String[] args) {

        Knight knight1 = new Knight(1, "knight1", 10, 1, -1, DiceType.D20, 0);

        Fortune merlinLuck = new Fortune("Merlin Luck", 10, 5, 2, DiceType.D12);
        Fortune horusCurse = new Fortune("Curse of Horus", -5, 0, -2, DiceType.NONE);

        System.out.println("knight1 CSV: " + knight1.toCSV());
        System.out.println();

        System.out.println(knight1);
        System.out.println("hitMod: " + knight1.getHitModifier());
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
