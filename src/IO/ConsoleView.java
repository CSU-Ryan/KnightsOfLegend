package IO;

import GameObjects.Knight;
import GameObjects.MOB;

import java.io.PrintStream;
import java.util.ArrayList;
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
        in = new Scanner(System.in);
        out = System.out;
    }

    public void displayException(Exception e) {
        out.println(e.getMessage());
        printHelp();
    }

    /**
     * Prints a splashscreen at the launch of the game.
     */
    @Override
    public void splashScreen() {
        // can be anything
        out.println("hey (:");
        out.println();
    }

    /**
     * Asks what the player wants to do.
     *
     * @return The player's response.
     */
    @Override
    public String displayMainMenu() {
        // asks "What would you like to do?"
        out.println("What would you like to do?");
        return in.nextLine();
    }

    /**
     * Displays a save confirmation.
     * @param filename path of file.
     */
    public void saved(String filename) {
        out.println("Progress saved to " + filename);
    }

    /**
     * Prints a message for completing the game.
     */
    @Override
    public void endGame() {
        out.println("Enjoy your rest! Until another adventure.");
    }

    /**
     * Prints the help menu.
     */
    @Override
    public void printHelp() {
        /* the options are as follows:
        Unsure what to do, here are some options:
            ls or list all  - listing the knights
            list active  - list the active knights only
            show name or id - show the knight details card
            set active name or id - set knight as active (note: only 4 knights can be active)
            remove active name or id - remove a knight from active status (heals knight)
            explore or adventure or quest - find random monsters to fight
            save filename - save the game to the file name (default: saveData.csv)
            exit or goodbye - to leave the game
         */
        out.println(
                "Unsure what to do, here are some options:\n" +
                "            ls or list all  - listing the knights\n" +
                "            list active  - list the active knights knights only\n" +
                "            show name or id - show the knight details card\n" +
                "            set active name or id - set knight as active (note: only 4 knights can be active)\n" +
                "            remove active name or id - remove a knight from active status (heals knight)\n" +
                "            explore or adventure or quest - find random monsters to fight\n" +
                "            save filename - save the game to the file name (default: saveData.csv)\n" +
                "            exit or goodbye - to leave the game"
        );
    }

    /**
     * Asks the player if they want to continue.
     *
     * @return The player's response (y/n).
     */
    @Override
    public boolean checkContinue() {
        // "Would you like to continue on your quest (y/n)?"
        char answer;
        while (true) {
            out.println("Would you like to continue on your quest (y/n)?");
            answer = in.nextLine().charAt(0);
            if (answer == 'y' || answer == 'Y') {
                return true;
            } else if (answer == 'n' || answer == 'N') {
                return false;
            }
            out.println("Error, invalid response. Please try again.");
        }
    }

    /**
     * Prints the given knight.
     *
     * @param knight The knight to be displayed.
     */
    @Override
    public void showKnight(Knight knight) {
        // add blank line.
        out.println(knight);
        out.println();
    }

    /**
     * Prints when a knight is not found.
     */
    public void knightNotFound() {
        out.println("Knight not found!");
    }

    /**
     * Lists the knights.
     *
     * @param knights List of knights to display.
     */
    @Override
    public void listKnights(ArrayList<Knight> knights) {
        // or if no knights, "No knights to list".
        if (knights.isEmpty()) {
            out.println("No knights to list");
            return;
        }

        // Lists the knights by `id: name`.
        for (Knight knight : knights) {
            out.println(knight.getId() + ": " + knight.getName());
        }
    }

    /**
     * Prints the fortunes of the active knights.
     *
     * @param activeKnights Knights to display fortunes.
     */
    @Override
    public void printFortunes(ArrayList<Knight> activeKnights) {
        // "For this quest, our knights drew the following fortunes!"
        // Format: {Name} drew \n {Fortune}
        out.println("For this quest, our knights drew the following fortunes!");
        for (Knight knight : activeKnights) {
            out.println(knight.getName() + " drew\n" + knight.getActiveFortune());
        }
    }

    /**
     * Prints the battle members.
     *
     * @param monsters      Monsters in the battle.
     * @param activeKnights Knights in the battle.
     */
    @Override
    public void printBattleText(ArrayList<MOB> monsters, ArrayList<Knight> activeKnights) {
        // Lists a number of knights side by side with their 'foes' (aka monsters).
        // "Our heroes come across the following monsters. Prepare for battle!"
        out.println("Our heroes come across the following monsters. Prepare for battle!");

        int i;
        for (i = 0; i < monsters.size(); ++i) {
            out.printf("%s%-27s%n", activeKnights.get(i), monsters.get(i));
        }
        for (; i < activeKnights.size(); ++i) {
            out.println(activeKnights.get(i));
        }
    }

    /**
     * Prints that the monster was defeated.
     *
     * @param dead the defeated monster.
     */
    @Override
    public void printBattleText(MOB dead) {
        // {Name} was defeated!
        out.println(dead.getName() + " was defeated!");
    }

    /**
     * Prints when all knights are defeated.
     */
    @Override
    public void printDefeated() {
        // "All active knights have been defeated!"
        out.println("All active knights have been defeated!");
    }

    /**
     * Prints a confirmation when a knight has been activated.
     *
     * @param knight the knight which was activated.
     */
    @Override
    public void setActiveSucceeded(Knight knight) {
        out.println("Activated " + knight.getName());
    }

    /**
     * Prints when a knight cannot be activated.
     */
    @Override
    public void setActiveFailed() {
        // "Unable to set active knight. Only four can be active at a time."
        out.println("Unable to set active knight. Only four can be active at a time.");
    }

    /**
     * Displays a confirmation when a knight has been deactivated.
     *
     * @param knight the deactivated knight.
     */
    @Override
    public void removeActiveSucceeded(Knight knight) {
        out.println("Deactivated " + knight.getName());
    }
}
