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
        ArrayList<Knight> party = (ArrayList<Knight>) data.getActiveKnights();
        ArrayList<MOB> monsters;

        boolean hasWon;
        do {
            hasWon = doBattle(party, monsters);
        } while (hasWon && view.checkContinue());
            monsters = (ArrayList<MOB>) DATA.getRandomMonsters();
    }

    /**
     * Runs a battle.
     * @param knights the player's living party.
     * @param monsters
     * @return
     */
    private boolean doBattle(ArrayList<Knight> knights, ArrayList<MOB> monsters) {
        boolean hasDied;
        List<MOB> monstersToRemove = new ArrayList<>();
        List<Knight> knightsToRemove = new ArrayList<>();
        IO.printBattleText(monsters, knights);


        while (!knights.isEmpty() && !monsters.isEmpty()) {
            // Knight turns
            for (Knight k : knights) {
                hasDied = doTurn(k, monsters);
                if (hasDied) {
                    knightsToRemove.add(k);
                }
            }
            // Monster turns
            for (MOB m : monsters) {
                hasDied = doTurn(m, knights);
                if (hasDied) {
                    for (Knight k : knights) k.addXP(DEATH_XP);
                    monstersToRemove.add(m);
                }
            }
            // Remove dead MOBs.
            for (Knight k : knightsToRemove) knights.remove(k);
            for (MOB m : monstersToRemove) monsters.remove(m);
        }
        if (knights.isEmpty()) {
            view.printDefeated();
            return false;
        }

        return true;
    }

    /**
     * runs one MOB's turn.
     *
     * @param attacker Who's turn it is.
     * @param defenders The group against our attacker.
     * @return
     */
    private boolean doTurn(MOB attacker, List<? extends MOB> defenders) {
        // Check if defeated.
        if (attacker.getHP() <= 0) {
            view.printBattleText(attacker);
            return true;
        }

        // Random Attack
        MOB target = getTarget(defenders);
        int damage = attacker.calculateHit(dice, target.getArmor());
        target.addDamage(damage);

        return false;
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
