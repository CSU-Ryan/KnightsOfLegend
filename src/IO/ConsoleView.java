package IO;

import GameObjects.Knight;
import GameObjects.MOB;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleView implements GameView {
    private final Scanner in;
    private final PrintStream out;

    private GameData game;

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
            list active  - list the active knights knights only
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
     * Handles the possible commands from the player [see printHelp()].
     *
     * @param command command call, first word of call.
     * @param args arguments for command, rest of call.
     * @return boolean whether command was successfully called.
     */
    public boolean callAction(String command, String args) {
        try {
            switch (command) {
                case "list":
                case "ls":
                    callList(args);
                    break;
                case "show":
                    callShow(args);
                    break;
                case "set":
                    callSet(args);
                    break;
                case "remove":
                case "rm":
                    callRemove(args);
                    break;
                case "explore":
                case "adventure":
                case "quest":
                    beginAdventure();
                    break;
                case "save":
                    callSave(args);
                    break;
                case "exit":
                case "goodbye":
                    endGame();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid command.");
            }
        }
        catch (IllegalArgumentException e) {
            out.println(e.getMessage());
            return false;
        }
        catch (IOException e) {
            out.println(e.getMessage());
            return false;
        }
        catch (NoSuchElementException e) {
            out.println(e.getMessage());
            return false;
        }
        finally { return true; }
    }

    /**
     * Handles call to `list {category}`.
     *
     * @param category category to list.
     * @throws IllegalArgumentException if category is invalid.
     */
    private void callList(String category) throws IllegalArgumentException {
        category = category.toLowerCase();
        switch (category) {
            case "":
            case "all":
                listKnights(game.knights);
                break;
            case "active":
                listKnights(game.activeKnights);
                break;
            default:
                throw new IllegalArgumentException("Call to 'list' has improper argument.");
        }
    }

    /**
     * Handles call to `show {name/ID}`.
     *
     * @param args name/ID.
     * @throws NoSuchElementException if no knight with name/ID exists.
     */
    private void callShow(String args) throws NoSuchElementException {
        args = args.toLowerCase();

        showKnight(game.findKnight(args, game.knights));
    }

    /**
     * Handles call to `set {category} {name/ID}`.
     *
     * @param args category to find knight with name/ID.
     * @throws IllegalArgumentException if category is invalid.
     * @throws NoSuchElementException if no knight with name/ID exists.
     */
    private void callSet(String args) throws IllegalArgumentException, NoSuchElementException {
        args = args.toLowerCase();
        String arg = args.substring(0, args.indexOf(' '));
        String nameOrId = args.substring(args.indexOf(' '+1));

        Knight knight = game.findKnight(args, game.knights);

        switch (arg) {
            case "active":
                if (!game.setActive(knight)) setActiveFailed();
                break;
            default:
                throw new IllegalArgumentException("call to 'set' has improper argument.");
        }
    }

    /**
     * Handles call to `remove {category} {name/ID}`.
     *
     * @param args category to find knight with name/ID.
     * @throws IllegalArgumentException if category is invalid.
     * @throws NoSuchElementException if kno knight with name/ID exists.
     */
    private void callRemove(String args) throws IllegalArgumentException, NoSuchElementException {
        args = args.toLowerCase();
        String arg = args.substring(0, args.indexOf(' '));
        String nameOrId = args.substring(args.indexOf(' '+1));

        Knight knight = game.findKnight(args, game.knights);

        switch (arg) {
            case "active":
                game.removeActive(knight);
                break;
            default:
                throw new IllegalArgumentException("call to 'remove' has improper argument.");
        }
    }

    /**
     * Handles call to `save {filename}`.
     *
     * @param filename file to save game to.
     * @throws IOException if failed to access file.
     */
    private void callSave(String filename) throws IOException {
        game.save(filename);
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

    public void beginAdventure() {
        //TODO
    }

    public void beginBattle() {
        //TODO
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
     * Prints when a knight cannot be activated.
     */
    @Override
    public void setActiveFailed() {
        // "Unable to set active knight. Only four can be active at a time."
        out.println("Unable to set active knight. Only four can be active at a time.");
    }

    public static void main(String[] args) {
        ConsoleView view = new ConsoleView();

        view.splashScreen();
        do {
            String in = view.displayMainMenu();


        } while (view.checkContinue());
    }
}
