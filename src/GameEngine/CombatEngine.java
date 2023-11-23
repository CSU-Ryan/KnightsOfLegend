package GameEngine;

import IO.*;
import GameObjects.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class CombatEngine {
    private final GameData data; // The data for the game, passed in as part of the constructor.
    private final DiceSet dice; // A DiceSet to be used for the Combat engine
    private final Random rnd; // Used in runCombat() to select who is fighting who.
    private final GameView view; // The View used for the game, passed in as part of the constructor.

    private final int DEATH_XP = 1;

    public CombatEngine(GameData data, GameView view) {
        this.data = data;
        this.view = view;

        rnd = new Random();
        dice = new DiceSet();
    }

    /**
     * Assigns and displays randomized fortunes for active knights.
     */
    public void initialize() {
        for (Knight k : data.getActiveKnights()) {
            k.setActiveFortune(data.getRandomFortune());
        }
        view.printFortunes((ArrayList<Knight>) data.getActiveKnights());
    }

    /**
     * Runs a battle.
     */
    public void runCombat() {
        ArrayList<Knight> party = (ArrayList<Knight>) data.getActiveKnights();
        ArrayList<MOB> monsters;

        boolean hasWon;
        do {
            monsters = (ArrayList<MOB>) data.getRandomMonsters();
            hasWon = doBattle(party, monsters);
        } while (hasWon && view.checkContinue());
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

        view.printBattleText(monsters, knights);

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
        return targetPool.get(rnd.nextInt(targetPool.size()));
    }

    /**
     * Removes fortunes from all knights.
     */
    public void clear() {
        for (Knight k : data.getKnights()) {
            k.setActiveFortune(new Fortune());
            k.resetDamage();
        }
    }

    public static void main(String[] args) {

    }
}
