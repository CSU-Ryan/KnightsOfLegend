package GameObjects;

/**
 * Attributes collection for typical game objects (monsters, knights, spells, fortunes, etc.).
 */
public interface Attributes {

    /**
     * Creates a card of this object's stats.
     *
     * @return a string of the info card.
     */
    @Override
    String toString();

    /**
     * Gets the armor value.
     *
     * @return the armor value.
     */
    int getArmor();

    /**
     * Gets the maximum hit points (HP).
     *
     * @return the max HP.
     */
    int getMaxHP();

    /**
     * Gets the type damage die.
     * <p>
     *     The {@link DiceType} used when rolling for attack damage.
     * </p>
     *
     * @return the type of dice.
     */
    DiceType getDamageDie();

    /**
     * gets the value added when rolling to overcome armor.
     *
     * @see MOB#calculateHit(DiceSet, int)
     * @return the hit modifier.
     */
    int getHitModifier();
}
