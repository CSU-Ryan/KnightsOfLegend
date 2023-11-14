package IO;

import GameObjects.Knight;
import GameObjects.MOB;

import java.awt.*;
import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleView implements GameView {
    private final Scanner in;
    private final PrintStream out;

    /*
        Game rules: You can have four active knights. As long as they are active, they won't heal,
        but they can gain XP by going on adventures.
        When you make a knight inactive, they will heal. How many monsters can you defeat
        before, you have to heal?
     */

    public ConsoleView() {
        in = null;
        out = null;
    }

    /**
     * Prints a splashscreen at the launch of the game.
     */
    @Override
    public void splashScreen() {
        // can be anything
    }

    /**
     * Asks what the player wants to do.
     *
     * @return The player's response.
     */
    @Override
    public String displayMainMenu() {
        // asks "What would you like to do?"
        return null;
    }

    /**
     * Prints a message for completing the game.
     */
    @Override
    public void endGame() {

    }

    /**
     * Prints the help menu.
     */
    @Override
    public void printHelp() {
        /* the options are as follows:
        Unsure what to do, here are some options:
            ls or list all  - listing the knights
            list active  - list the active knights knights only
            show name or id - show the knight details card
            set active name or id - set knight as active (note: only 4 knights can be active)
            remove active name or id - remove a knight from active status (heals knight)
            explore or adventure or quest - find random monsters to fight
            save filename - save the game to the file name (default: saveData.csv)
            exit or goodbye - to leave the game
         */
    }

    /**
     * Asks the player if they want to continue.
     *
     * @return The player's response (y/n).
     */
    @Override
    public boolean checkContinue() {
        // "Would you like to continue on your quest (y/n)?"
        return false;
    }

    /**
     * Prints the given knight.
     *
     * @param knight The knight to be displayed.
     */
    @Override
    public void showKnight(Knight knight) {
        // add blank line.
    }

    /**
     * Lists the knights.
     *
     * @param knights List of knights to display.
     */
    @Override
    public void listKnights(List knights) {
        // Lists the knights by `id: name`.
        // or if no knights, "No knights to list".
    }

    /**
     * Prints the fortunes of the active knights.
     *
     * @param activeKnights Knights to display fortunes.
     */
    @Override
    public void printFortunes(List activeKnights) {
        // "For this quest, our knights drew the following fortunes!"
        // Format: {Name} drew \n {Fortune}
    }

    /**
     * Prints the battle members.
     *
     * @param monsters      Monsters in the battle.
     * @param activeKnights Knights in the battle.
     */
    @Override
    public void printBattleText(List monsters, List activeKnights) {
        // Lists a number of knights side by side with their 'foes' (aka monsters).
        // "Our heroes come across the following monsters. Prepare for battle!"
    }

    /**
     * Prints that the monster was defeated.
     *
     * @param dead the defeated monster.
     */
    @Override
    public void printBattleText(MOB dead) {
        // {Name} was defeated!
    }

    /**
     * Prints when all knights are defeated.
     */
    @Override
    public void printDefeated() {
        // "All active knights have been defeated!"
    }

    /**
     * Prints when a knight cannot be activated.
     */
    @Override
    public void setActiveFailed() {
        // "Unable to set active knight. Only four can be active at a time."
    }
}
