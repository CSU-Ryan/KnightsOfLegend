package GameEngine;

import IO.*;
import GameObjects.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;

/**
 * Handles a simple combat system.<br>
 * <br><br>
 * <p>
 *     For a battle, a party of knights and monsters fight.<br>
 *     Each cycle, the knights do their turn, then the monsters.<br>
 *     Only at the end of each cycle are knights/monsters (MOBs) defeated and removed from play.<br>
 *     <br>
 *     For each MOB's turn, they select a random target and attack them.<br>
 *     (See {@link GameObjects.MOB#calculateAttack(DiceSet, MOB)} for how damage is calculated)<br>
 *     <br>
 *     If a monster dies, all living knights are rewarded experience points (XP).
 * </p>
 */
public class CombatEngine {

    private final GameData DATA; // The data for the game.
    private final GameView IO; // The IO system.

    private final DiceSet DICE_SET; // Allows a die to be rolled.
    private final Random RANDOM;

    private final int DEATH_XP; // The amount of xp granted for killing a monster.

    /**
     * Constructs the combat engine.
     *
     * @param data game data.
     * @param io player IO accessor.
     */
    public CombatEngine(GameData data, GameView io) {
        DATA = data;
        IO = io;

        RANDOM = new Random();
        DICE_SET = new DiceSet();

        DEATH_XP = 1;
    }

    /**
     * Assigns fortunes for active knights.
     */
    public void initialize() {
        for (Knight k : DATA.getActiveKnights()) {
            k.setActiveFortune(DATA.getRandomFortune());
        }
        IO.printFortunes((ArrayList<Knight>) DATA.getActiveKnights());
    }

    /**
     * Runs a quest.
     */
    public void runCombat() {
        ArrayList<Knight> party = (ArrayList<Knight>) DATA.getActiveKnights();
        ArrayList<MOB> monsters;

        boolean hasLost;
        do {
            monsters = (ArrayList<MOB>) DATA.getRandomMonsters();
            hasLost = doBattle(party, monsters);
            if (hasLost) {
                IO.printDefeated();
                break;
            }
        } while (IO.checkContinue());
    }

    /**
     * Iterates through all character's turns.
     *
     * @param knights the player's active party.
     * @param monsters the enemies of the encounter.
     * @return true if the party has been defeated, else false.
     */
    private boolean doBattle(ArrayList<Knight> knights, ArrayList<MOB> monsters) {
        IO.printBattleText(monsters, knights);

        int xpReward;
        while (!knights.isEmpty() && !monsters.isEmpty()) {
            // Knight turns
            for (Knight k : knights) {
                xpReward = doTurn(k, monsters);
                for (Knight knight : knights) knight.addXP(xpReward);

            }
            // Monster turns
            for (MOB m : monsters) {
                doTurn(m, knights);
            }
        }
        return (knights.isEmpty());
    }

    /**
     * Runs one MOB's turn.
     *
     * @param attacker the active MOB
     * @param defenders the opposition
     * @return xp from a defeat
     */
    private int doTurn(MOB attacker, List<? extends MOB> defenders) {
        // Random Attack
        MOB target = getTarget(defenders);
        int damage = attacker.calculateAttack(DICE_SET, target);
        target.addDamage(damage);

        if (target.getHP() <= 0) {
            defenders.remove(target);
            IO.printBattleText(target);

            if (!(target instanceof Knight)) {
                return DEATH_XP;
            }
        }
        return 0;
    }

    /**
     * Selects a target for the attack.
     *
     * @param targetPool the group to select a target from.
     * @return the selected target.
     */
    private MOB getTarget(List<? extends MOB> targetPool) {
        return targetPool.get(RANDOM.nextInt(targetPool.size()));
    }

    /**
     * Resets the knights.
     *
     * Removes their fortunes and resets their health.
     */
    public void clear() {
        for (Knight k : DATA.getKnights()) {
            k.setActiveFortune(new Fortune()); // Equivalent of removing fortune.
            k.resetDamage();
        }
    }

    public static void main(String[] args) {

    }
}
