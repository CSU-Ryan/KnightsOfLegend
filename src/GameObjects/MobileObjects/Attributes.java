package GameObjects.MobileObjects;

import GameEngine.DiceSet;
import GameEngine.DiceType;

/**
 * Collection of attributes for typical game objects<br>
 * (monsters, knights, spells, fortunes, Etc.).<br>
 * <br>
 * Ensures a game object has:
 * <ul>
 *     <li>{@link #getMaxHP()}<br>       A hit-point (HP) level</li>
 *     <li>{@link #getArmor()}<br>       An armor level</li>
 *     <li>{@link #getAccuracy()}<br> A modifier for {@link MOB#rollHit(DiceSet, MOB)}</li>
 *     <li>{@link #getDamageDie()}<br>   A {@link DiceType} for {@link MOB#rollDamage(DiceSet)}</li>
 *     <li>{@link #toString()}<br>       An info card describing the object's stats</li>
 * </ul>
 */
public interface Attributes {


    /**
     * Gets the maximum hit points (HP) or HP-modifier of the object.
     *
     * @return the max HP
     */
    int getMaxHP();

    /**
     * Gets the armor value or modifier.<br>
     * <br>
     * Armor is used for deflecting attacks. See {@link MOB#rollHit(DiceSet, MOB)}.
     *
     * @return the armor value
     */
    int getArmor();

    /**
     * gets the object's accuracy stat.<br>
     * <br>
     * <p>
     *     Accuracy is used when rolling to overcome an opponent's armor.<br>
     *     <code>attackerAccuracy + roll(D20) > enemyArmor</code>
     * </p>
     *
     * @return the object's accuracy
     * @see MOB#rollHit(DiceSet, MOB)
     */
    int getAccuracy();

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
     * Creates an info card of this object's stats.
     *
     * @return a string of the info card.
     */
    @Override
    String toString();
}
