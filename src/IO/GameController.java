package IO;

import GameEngine.*;
import GameObjects.Knight;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class GameController {
    private final GameData data; // Game data.
    private final GameView view; // game view.
    private final CombatEngine engine; // combat engine.


    public GameController(GameData data, GameView view, CombatEngine engine) {
        this.data = data;
        this.view = view;
        this.engine = engine;
    }

    /**
     * Starts the game, causing it to run until a client exits. Starts with splashScreen, loops as long as processCommand returns true, prints endgame when loop is done.
     */
    public void start() {
        boolean proceed;
        view.splashScreen();

        do {
            proceed = processCommand(view.displayMainMenu());
        }
        while (proceed);
    }

    /**
     * Handles the possible commands from the player. See {@link GameView} for full list.
     *
     * @param input the full command call
     * @return boolean whether to continue prompting the user
     */
    protected boolean processCommand(String input) {
        Scanner scanner = new Scanner(input.toLowerCase());

        if (!scanner.hasNext()) return false;

        String command = scanner.next();

        try {
            switch (command) {
                case "list":
                case "ls":
                    callList(scanner);
                    break;
                case "show":
                    callShow(scanner);
                    break;
                case "set":
                    callSet(scanner);
                    break;
                case "remove":
                case "rm":
                    callRemove(scanner);
                    break;
                case "explore":
                case "adventure":
                case "quest":
                    beginAdventure();
                    break;
                case "save":
                    callSave(scanner);
                    break;
                case "quit":
                case "exit":
                case "goodbye":
                    view.endGame();
                    return false;
                case "help":
                    view.printHelp();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid command.");
            }
        }
        catch (IllegalArgumentException | IOException | NoSuchElementException e) {
            view.displayException(e);
        }
        return true;
    }

    /**
     * Handles call to list a category.
     * <br>
     * <p>
     *     Call format:<br>
     *     <code>list {category}</code>
     * </p>
     *
     * @param scanner scanner of category to list
     * @throws IllegalArgumentException if category is invalid
     */
    private void callList(Scanner scanner) throws IllegalArgumentException {
        String category = (scanner.hasNext()) ? scanner.next() : "";
        switch (category) {
            case "":
            case "all":
                view.listKnights(data.knights);
                break;
            case "active":
                view.listKnights(data.activeKnights);
                break;
            default:
                throw new IllegalArgumentException("Call to 'list' has improper argument.");
        }
    }

    /**
     * Handles call to show a given knight.
     * <br>
     * <p>
     *     Call format:<br>
     *     <code>show {name/ID}</code>
     * </p>
     *
     * @param scanner scanner of the name/ID
     * @throws NoSuchElementException if no knight with name/ID exists
     */
    private void callShow(Scanner scanner) throws IllegalArgumentException, NoSuchElementException {
        if (!scanner.hasNext()) throw
                new IllegalArgumentException("Call to 'show' requires input.");

        view.showKnight(
                data.findKnight(scanner.nextLine().trim(), data.knights));
    }

    /**
     * Handles call to set a knight to a party.
     * <br>
     * <p>
     *     Call format:<br>
     *     <code>set {category} {name/ID}</code>
     * </p>
     *
     * @param scanner scanner of the user's category choice
     * @throws IllegalArgumentException if the category is invalid
     * @throws NoSuchElementException if no knight with name/ID exists
     */
    private void callSet(Scanner scanner) throws IllegalArgumentException, NoSuchElementException {
        String category;
        String nameOrID;
        if (scanner.hasNext()) {
            category = scanner.next();
        } else throw new IllegalArgumentException("Call to 'set' requires a category.");

        if (scanner.hasNext()) {
            nameOrID = scanner.nextLine().trim();
        } else throw new IllegalArgumentException("Call to set requires a name or ID.");

        Knight knight = data.getKnight(nameOrID);

        switch (category) {
            case "active":
                if (data.setActive(knight)) {
                    view.setActiveSucceeded(knight);
                }
                else {
                    view.setActiveFailed();
                }
                break;
            default:
                throw new IllegalArgumentException("Categories to set a knight are: \n'active'");
        }
    }

    /**
     * Handles call to remove a knight from a party.<br>
     * <br>
     * <p>
     *     Call format:<br>
     *     <code>remove {category} {name/ID}</code>
     * </p>
     *
     * @param scanner scanner of the user's category choice
     * @throws IllegalArgumentException if the category is invalid
     * @throws NoSuchElementException if no knight with name/ID exists
     */
    private void callRemove(Scanner scanner) throws IllegalArgumentException, NoSuchElementException {
        String category;
        String nameOrID;
        if (scanner.hasNext()) {
            category = scanner.next();
        } else throw new IllegalArgumentException("Call to 'set' requires a category.");

        if (scanner.hasNext()) {
            nameOrID = scanner.nextLine().trim();
        } else {
            nameOrID = category;
            category = "active";
        }

        Knight knight = data.getActive(nameOrID);

        switch (category) {
            case "active":
                data.removeActive(knight);
                view.removeActiveSucceeded(knight);
                break;
            default:
                throw new IllegalArgumentException("call to 'remove' has improper argument.");
        }
    }

    /**
     * Begins an adventure.<br>
     * <br>
     * <p>
     *     Using the player's active party, they battle a series of monsters.<br>
     *     The adventure ends either when the player decides to stop or their whole party has been defeated.
     * </p>
     * @see CombatEngine
     * @throws NoSuchElementException if activeKnights is empty
     */
    private void beginAdventure() throws NoSuchElementException {
        if (data.activeKnights.isEmpty()) {
            throw new NoSuchElementException("Cannot begin adventure, as your party is empty!");
        }
        engine.initialize();
        engine.runCombat();
        engine.clear();
    }

    /**
     * Handles call to save current gamestate.<br>
     * <br>
     * <p>
     *     Call format:<br>
     *     <code>save {filename}</code>
     * </p>
     *
     * @param scanner scanner of the user's intended save file
     * @throws IOException if failed to access file
     */
    private void callSave(Scanner scanner) throws IOException {
        String saveNumber;
        if (scanner.hasNext()) {
            saveNumber = scanner.next();
        } else throw new IllegalArgumentException("Call to 'save' must include a save file.");

        String filename = "SaveFiles/";
        switch (saveNumber) {
            case "1":
            case "one":
                filename += "one.csv";
                break;
            case "2":
            case "two":
                filename += "two.csv";
                break;
            case "3":
            case "three":
                filename += "three.csv";
                break;
            case "4":
            case "four":
                filename += "four.csv";
                break;
            case "test":
                filename += "test.csv";
                break;
            default:
                filename = saveNumber;
                view.displayWarning("WARNING: saving file outside of saves folder.");
        }
        data.save(filename);
        view.saved(filename);
    }

    public static void main(String[] args) {
        String gameDataPath = "GameData/test_gamedata.csv";
        String saveDataPath = "GameData/test_savedata.csv";

        GameData game = new CSVGameData(gameDataPath, saveDataPath);
        ConsoleView view = new ConsoleView();
        CombatEngine engine = new CombatEngine(game, view);

        GameController controller = new GameController(game, view, engine);


        controller.start();
    }
}
