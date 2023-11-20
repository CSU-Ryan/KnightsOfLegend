package IO;

import GameEngine.*;
import GameObjects.Knight;

import java.io.IOException;
import java.util.NoSuchElementException;

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
        //TODO
    }

    protected boolean processCommand(String command) {
        int spaceIndex = command.indexOf(' ');
        if (spaceIndex != -1) {
            String comm = command.substring(0, spaceIndex);
            String args = command.substring(spaceIndex + 1);
            return processCommand(comm, args);
        }
        else {
            return processCommand(command, "");
        }
    }

    /**
     * Handles the possible commands from the player [see printHelp()].
     *
     * @param command command call, first word of call.
     * @param args arguments for command, rest of call.
     * @return boolean whether command was successfully called.
     */
    protected boolean processCommand(String command, String args) {
        command = command.trim().toLowerCase();
        args = args.trim().toLowerCase();
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
        catch (IllegalArgumentException e) {
            view.displayException(e);
        }
        catch (IOException e) {
            view.displayException(e);
        }
        catch (NoSuchElementException e) {
            view.displayException(e);
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
     * Handles call to `show {name/ID}`.
     *
     * @param args name/ID.
     * @throws NoSuchElementException if no knight with name/ID exists.
     */
    private void callShow(String args) throws NoSuchElementException {
        args = args.toLowerCase();

        view.showKnight(data.findKnight(args, data.knights));
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
        String nameOrId = args.substring(args.indexOf(' ')+1);

        Knight knight = data.findKnight(nameOrId, data.knights);

        switch (arg) {
            case "active":
                if (!data.setActive(knight)) {
                    view.setActiveFailed();
                }
                else {
                    view.setActiveSucceeded(knight);
                }
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
        String nameOrId = args.substring(args.indexOf(' ')+1);

        Knight knight = data.findKnight(nameOrId, data.knights);

        switch (arg) {
            case "active":
                data.removeActive(knight);
                view.removeActiveSucceeded(knight);
                break;
            default:
                throw new IllegalArgumentException("call to 'remove' has improper argument.");
        }
    }

    /**
     * Begins an adventure.
     */
    private void beginAdventure() {
        engine.initialize();
        engine.runCombat();
        engine.clear();
    }

    /**
     * Handles call to `save {filename}`.
     *
     * @param filename file to save game to.
     * @throws IOException if failed to access file.
     */
    private void callSave(String filename) throws IOException {
        data.save(filename);
        view.saved(filename);
    }

}
