public interface Attributes {
    /**
     * Gets the armor attribute value, often between 8-20 for a D20 system, but not fixed. Also called "armor class/AC".
     * @return
     *     whole number value of the armor stat
     */
    int getArmor();

    /**
     * Gets the maximum hit points attribute. While HP can go over max, they should always be reset to maxHP. Essentially maxHP is fixed, and remaining HP is modified either up or down and calculated when needed.
     * @return
     *     whole number value of the HP
     */
    int getMaxHP();

    /**
     * Gets the Damage Die Type.
     * @return
     *     a DiceType often ranging from D4-D12
     */
    DiceType getDamageDie();

    /**
     * A whole number of any int value to apply when making 'hit roles'.
     * @return
     *     whole number value of the modifier
     */
    int getHitModifier();
}
