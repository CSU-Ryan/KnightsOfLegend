package GameObjects;

/**
 * Attributes collection for typical game objects.<br>
 * (monsters, knights, spells, fortunes, etc.).<br>
 * <br>
 * Ensures a game object has:
 * <ul>
 *     <li>An info card describing the object's stats<br> {@link #toString()}</li>
 *     <li>An armor stat<br> {@link #getArmor()}</li>
 *     <li>A hit-point (HP) level<br> {@link #getMaxHP()}</li>
 *     <li>A {@link DiceType} for damage rolls<br> {@link #getDamageDie()}</li>
 *     <li>A modifier for hit rolls<br> {@link #getHitModifier()}</li>
 * </ul>
 */
public interface Attributes {

    /**
     * Creates an info card of this object's stats.
     *
     * @return a string of the info card.
     */
    @Override
    String toString();

    /**
     * Gets the armor value.
     *
     * @return the armor value.
     * @see MOB#calculateHit(DiceSet, int)
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
