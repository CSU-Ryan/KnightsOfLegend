package GameObjects.Effects;

import GameEngine.DiceType;
import GameObjects.MobileObjects.Attributes;

/**
 * Describes the requirements for a status effect. <br>
 * <br>
 * <p>
 *     An object which modifies the stats of an object.<br>
 *     <ul> Ensures a status effect has:
 *         <li> A name. <br>
 *             {@link #getName()}
 *         </li>
 *         <li> A hit-point (HP) modifier. <br>
 *             {@link #getMaxHPModifier()}
 *         </li>
 *         <li> An armor modifier. <br>
 *             {@link #getArmorModifier()}
 *         </li>
 *         <li> An accuracy modifier. <br>
 *             {@link #getAccuracyModifier()}
*          </li>
 *         <li> A damage modifier. <br>
 *             {@link #getDamageModifier()}
 *         </li>
 *         <li> A card describing the object's status effects. <br>
 *             {@link #toString()}
 *         </li>
 *     </ul>
 * </p>
 */
public interface Effect {
    /**
     * Gets the name of the effect.<br>
     * <br>
     * <p>
     *     This will typically be a few words, such as<br>
     *     "<i>Geralt's Blessing</i>" or "<i>Poison</i>".
     * </p>
     *
     * @return the name of the effect
     */
    String getName();

    /**
     * Gets the effect on Max HP.<br>
     * <br>
     * <p>
     *     Could be a constant change to maxHP, or based upon a function.<br>
     *     For example, "<i>Rejuvenation</i>" could increase health by 10 HP.<br>
     *     Or "<i>Poison</i>" could incrementally decrease health after each battle.
     * </p>
     *
     * @return the value to modify health
     * @see Attributes#getMaxHP()
     */
    int getMaxHPModifier();

    /**
     * Gets the effect on armor.<br>
     * <br>
     * <p>
     *     Could be a constant change to armor, or based on a function.<br>
     *     For example, "<i>Mana Shield</i>" could increase armor by 10.<br>
     *     Or "<i>Adrenaline</i>" could increase armor with each kill.
     * </p>
     *
     * @return the value to modify armor
     * @see Attributes#getArmor()
     */
    int getArmorModifier();

    /**
     * Gets the effect on accuracy.<br>
     * <br>
     * <p>
     *     Could be a constant change to accuracy, or based on a function.
     *     For example, "<i>Eagle Eye</i>" could increase accuracy by 10.<br>
     *     Or "<i>Hunger</i>" could slowly decrease accuracy over time.
     * </p>
     *
     * @return the value to modify accuracy
     * @see Attributes#getAccuracy()
     */
    int getAccuracyModifier();

    /**
     * Gets the effect on the damage of an attack.<br>
     * <br>
     * <p>
     *     Could be a constant change to damage, or a multiplier.
     * </p>
     *
     * @return the value to modify damage
     * @see Attributes#getDamageDie()
     */
    int getDamageModifier();

    /**
     * Gets a card displaying the effect's stats.
     *
     * @return the display card as a String
     */
    @Override
    String toString();
}
