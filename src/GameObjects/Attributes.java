package GameObjects;

/**
 * Attributes collection for typical game objects.<br>
 * (monsters, knights, spells, fortunes, etc.).<br>
 * <br>
 * Ensures a game object has:
 * <ul>
 *     <li>{@link #toString()}<br>       An info card describing the object's stats</li>
 *     <li>{@link #getArmor()}<br>       An armor level</li>
 *     <li>{@link #getMaxHP()}<br>       A hit-point (HP) level</li>
 *     <li>{@link #getDamageDie()}<br>   A {@link DiceType} for {@link MOB#rollDamage(DiceSet)}</li>
 *     <li>{@link #getHitModifier()}<br> A modifier for {@link MOB#rollHit(DiceSet, MOB)}</li>
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
     * Gets the armor value or modifier.<br>
     * <br>
     * Armor is used for deflecting attacks. See {@link MOB#rollHit(DiceSet, MOB)}.
     *
     * @return the armor value
     */
    int getArmor();

    /**
     * Gets the maximum hit points (HP) or HP-modifier of the object.
     *
     * @return the max HP
     */
    int getMaxHP();

    /**
     * Gets the type of the object's damage die.<br>
     * <br>
     * <p>
     *     The {@link DiceType} used when {@link MOB#rollDamage(DiceSet)}.
     * </p>
     *
     * @return the type of die
     */
    DiceType getDamageDie();

    /**
     * gets the value or modifier added when rolling to overcome armor.
     *
     * @return the hit modifier
     * @see MOB#rollHit(DiceSet, MOB)
     */
    int getHitModifier();
}
