package IO;

import GameObjects.Knight;
import GameObjects.MOB;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Interacts with the player through IO streams.
 *
 * @see IO.GameView
 */
public class ConsoleView implements GameView {
    private final Scanner in;
    private final PrintStream out;


    /**
     * Constructs an IO connection with the user through the console.
     */
    public ConsoleView() {
        this(System.in, System.out);
    }

    /**
     * Constructs an IO connection with the user with given IO streams.
     *
     * @param in  the InputStream from the user
     * @param out the PrintStream to print information
     */
    public ConsoleView(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Prints an exception.<br>
     * <br>
     * <p>
     *     Prints the exception's message with a blank line following.
     * </p>
     *
     * @param exception the exception to print
     */
    public void displayException(Exception exception) {
        out.println(exception.getMessage());
        out.println();
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
     * Asks what the player wants to do.<br>
     * <br>
     * Prints: "<i>What would you like to do?</i>"
     *
     * @return the response line
     */
    @Override
    public String displayMainMenu() {
        out.print("What would you like to do? ");
        return in.nextLine();
    }

    /**
     * Displays a confirmation from saving.<br>
     * <br>
     * "<i>Progress saved to {filepath}</i>"
     *
     * @param filepath path of the save file
     */
    public void saved(String filepath) {
        out.println("Progress saved to " + filepath);
        out.println();
    }

    /**
     * Prints a message for completing the game.<br>
     * <br>
     * "<i>Enjoy your rest! Until another adventure.</i>"
     */
    @Override
    public void endGame() {
        out.println("Enjoy your rest! Until another adventure.");
        out.println();
    }

    /**
     * Prints the help menu.<br>
     * <br>
     * For commands, see {@link IO.GameView}
     */
    @Override
    public void printHelp() {
        out.println(
            "Unsure what to do, here are some options:\n" +
            "            ls or list all  - listing the knights\n" +
            "            list active  - list the active knights knights only\n" +
            "            show name or id - show the knight details card\n" +
            "            set active name or id - set knight as active (note: only 4 knights can be active)\n" +
            "            remove active name or id - remove a knight from active status (heals knight)\n" +
            "            explore or adventure or quest - find random monsters to fight\n" +
            "            save filename - save the game to the file name (default: saveData.csv)\n" +
            "            exit or goodbye - to leave the game\n" +
            "\n" +
            "Game rules: You can have four active knights. As long as they are active, they won't heal, but they can gain XP by going on adventures.\n" +
            "When you make a knight inactive, they will heal. How many monsters can you defeat before, you have to heal?"
        );
        out.println();
    }

    /**
     * Asks the player if they want to continue.<br>
     * <br>
     * "<i>Would you like to continue on your quest (y/n)?</i>"
     *
     * @return a boolean on whether to continue
     */
    @Override
    public boolean checkContinue() {
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
     * Prints the given knight's stats card<br>
     * (see {@link Knight#toString()}).
     *
     * @param knight the knight to be printed
     */
    @Override
    public void showKnight(Knight knight) {
        out.println(knight);
        out.println();
    }

    /**
     * Prints when a knight is not found, followed by a blank line.<br>
     * <br>
     * "<i>Knight not found!</i>"
     */
    @Override
    public void knightNotFound() {
        out.println("Knight not found!");
        out.println();
    }

    /**
     * Prints the given list of knights.<br>
     * <br>
     * <p>
     *     Prints the knights line by line,with format<br>
     *     <i>ID: name</i><br>
     *     If the list is empty, "<i>No knights to list</i>"
     * </p>
     *
     * @param knights the list of knights to display
     */
    @Override
    public void listKnights(ArrayList<Knight> knights) {
        if (knights.isEmpty()) {
            out.println("No knights to list");
            out.println();
            return;
        }

        // Lists the knights by `id: name`.
        for (Knight knight : knights) {
            out.println(knight.getId() + ": " + knight.getName());
        }
        out.println();
    }

    /**
     * Prints the fortunes of the given knights.<br>
     * <br>
     * <p><i>
     *     For this quest, our knights drew the following fortunes!<br>
     *     {Name} drew<br>
     *     {fortune stat card}<br>
     * </i></p><br>
     * (for stat card, see {@link GameObjects.Fortune#toString()})
     *
     * @param activeKnights list of knights to display
     */
    @Override
    public void printFortunes(ArrayList<Knight> activeKnights) {
        out.println("For this quest, our knights drew the following fortunes!");
        for (Knight knight : activeKnights) {
            out.println(knight.getName() + " drew\n" + knight.getActiveFortune());
        }
        out.println();
    }

    /**
     * Prints the battle members.<br>
     * <br>
     * <p><i>
     *     Our heroes come across the following monsters. Prepare for battle!<br>
     *     {Knight name} {Monster name}<br>
     *     {Knight name} {Monster name}<br>
     *     Etc.
     * </i></p>
     *
     * @param monsters      monsters in the battle
     * @param activeKnights knights in the battle
     */
    @Override
    public void printBattleText(ArrayList<MOB> monsters, ArrayList<Knight> activeKnights) {
        out.println("Our heroes come across the following monsters. Prepare for battle!");
        out.println("Knights                     Foes");
        int i;
        for (i = 0; i < monsters.size(); ++i) {
            out.printf("%s%27s%n", activeKnights.get(i).getName(), monsters.get(i).getName());
        }
        for (; i < activeKnights.size(); ++i) {
            out.println(activeKnights.get(i).getName());
        }
        out.println();
    }

    /**
     * Prints the defeat message for a character.<br>
     * <br>
     * "<i>{name} was defeated!</i>"
     *
     * @param defeated the defeated MOB
     */
    @Override
    public void printBattleText(MOB defeated) {
        out.println(defeated.getName() + " was defeated!");
    }

    /**
     * Prints when all knights are defeated.<br>
     * <br>
     * "<i>All active knights have been defeated!</i>"
     */
    @Override
    public void printDefeated() {
        out.println("All active knights have been defeated!");
        out.println();
    }

    /**
     * Prints a confirmation when a knight has been activated.<br>
     * <br>
     * "<i>Activated {name}.</i>"
     *
     * @param knight the activated knight.
     */
    @Override
    public void setActiveSucceeded(Knight knight) {
        out.println("Activated " + knight.getName() + ".");
        out.println();
    }

    /**
     * Prints a confirmation when a knight has been deactivated.<br>
     * <br>
     * "<i>Deactivated {name}.</i>"
     *
     * @param knight the deactivated knight.
     */
    @Override
    public void removeActiveSucceeded(Knight knight) {
        out.println("Deactivated " + knight.getName() + ".");
        out.println();
    }

    /**
     * Prints when a knight cannot be activated.<br>
     * <br>
     * "<i>Unable to set active knight. Only four can be active at a time.</i>"
     */
    @Override
    public void setActiveFailed() {
        out.println("Unable to set active knight. Only four can be active at a time.");
        out.println();
    }
}
