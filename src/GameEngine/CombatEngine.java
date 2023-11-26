package GameEngine;

import IO.*;
import GameObjects.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;

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
     * Assigns and displays randomized fortunes for active knights.
     */
    public void initialize() {
        for (Knight k : DATA.getActiveKnights()) {
            k.setActiveFortune(DATA.getRandomFortune());
        }
        IO.printFortunes((ArrayList<Knight>) DATA.getActiveKnights());
    }

    /**
     * Runs a battle.
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
     * Runs a battle.
     * @param knights the player's living party.
     * @param monsters
     * @return
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
     * runs one MOB's turn.
     *
     * @param attacker Who's turn it is.
     * @param defenders The group against our attacker.
     * @return
     */
    private int doTurn(MOB attacker, List<? extends MOB> defenders) {
        // Random Attack
        MOB target = getTarget(defenders);
        int damage = attacker.calculateHit(DICE_SET, target.getArmor());
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

    private MOB getTarget(List<? extends MOB> targetPool) {
        return targetPool.get(RANDOM.nextInt(targetPool.size()));
    }

    /**
     * Removes fortunes from all knights.
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
