package GameEngine;

import IO.*;
import GameObjects.*;

import java.util.Random;
import java.util.List;

public class CombatEngine {
    private final GameData data; // The data for the game, passed in as part of the constructor.
    private final DiceSet dice; // A DiceSet to be used for the Combat engine
    private final Random rnd; // Used in runCombat() to select who is fighting who.
    private final GameView view; // The View used for the game, passed in as part of the constructor.

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
        //TODO
    }

    /**
     * Runs a battle.
     */
    public void runCombat() {
        //NOTE: this function is not graded.
        /*
         Combat will continue to run as long as there are either knights or monsters/MOBs. If MOBs are reduced to zero, the player will be promoted to see if they wish to continue exploring GameView.checkContinue(). If they respond yes, more random monsters will be generated, and combat begins again. At the start of each battle:

    Generates a random list of MOBs, no larger than the total number of knights GameData.getRandomMonsters()
    Prints the battle text, on who the fight is between GameView.printBattleText(List, List)
    Runs through the combat


The combat order itself is undefined on order of actions, but the following must happen

    When knights are defeated (MOB.getHP() <= 0), they are removed from active knights
    When MOBs are defeated, every active knight earns 1 XP point (Knight.addXP(int))
    While combat order is undefined, a common implementation is cycle through the knights having them attack a random monster. We then cycle through the MOBs having them each attack a random knight.
    When a knight or mob is defeated, we print that they were defeated GameView.printBattleText(MOB)

If all knights are defeated, we notify the player using GameView.printDefeated().

Calculating Hits
To calculate a successful hit, you roll a D20 (DiceSet.roll(DiceType) take that value, add the MOBs/Knights toHitModifier. If the value is greater than the armor value, they score a hit, and the damage die is rolled.

     D20 + hitModifier > armor  (successful hit formula)



Upon a successful strike, the damage die is rolled to determine the amount of damage the opponent takes Hint to students: private helper methods are extremely helpful here. As is helps break up the above steps. Make sure to take it in small parts, printing out in each step.
         */
        //TODO
    }

    /**
     * helper method?
     * @param attackers
     * @param defenders
     * @return
     */
    private int doBattle(List<MOB> attackers, List<MOB> defenders) {
        //TODO
        return -1;
    }

    /**
     * Removes fortunes from all knights.
     */
    public void clear() {
        //TODO
    }
}
