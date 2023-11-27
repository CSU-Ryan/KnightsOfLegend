package IO;

import GameObjects.*;
import java.util.ArrayList;

/**
 * Describes protocols for interacting with the user.<br>
 * <br>
 * Commands:
 * <ul>
 *     <li>ls/list all  - listing the knights</li>
 *     <li>list active  - list the active knights knights only</li>
 *     <li>show knight - show the knight details card</li>
 *     <li>set active knight - set knight as active (note: only 4 knights can be active)</li>
 *     <li>remove active knight - remove a knight from active status (heals knight)</li>
 *     <li>explore/adventure/quest - find random monsters to fight</li>
 *     <li>save filename - save the game to the file name (default: saveData.csv)</li>
 *     <li>quit/exit/goodbye - to leave the game</li>
 * </ul>
 */
public interface GameView {

    /**
     * Presents a warning to the user.<br>
     * <br>
     * <p>
     *     Intended for communication with the user that isn't an error, but they may want to be warned against.
     * </p>
     *
     * @param message the warning message
     */
    void displayWarning(String message);

    /**
     * Displays an exception.
     *
     * @param exception the exception to display.
     */
    void displayException(Exception exception);

    /**
     * Displays a splashscreen at the launch of the game.
     */
    void splashScreen();

    /**
     * Displays option menu for the player.
     *
     * @see
     * @return the player's input.
     */
    String displayMainMenu();

    /**
     * Displays a confirmation from saving.
     *
     * @param filename path of save file.
     */
    void saved(String filename);

    /**
     * Displays a message for completing the game.
     */
    void endGame();

    /**
     * Displays the help menu.
     */
    void printHelp();

    /**
     * Asks the player if they want to continue.
     *
     * @return the player's response.
     */
    boolean checkContinue();

    /**
     * Displays the given knight.
     *
     * @param knight the knight to be displayed.
     */
    void showKnight(Knight knight);

    /**
     * Display when a knight is not found.
     */
    void knightNotFound();

    /**
     * Lists the knights.
     *
     * @param knights list of knights to display.
     */
    void listKnights(ArrayList<Knight> knights);

    /**
     * Displays the fortunes of the active knights.
     *
     * @param activeKnights knights to display fortunes.
     */
    void printFortunes(ArrayList<Knight> activeKnights);

    /**
     * Displays the battle members.
     *
     * @param monsters monsters in the battle.
     * @param activeKnights knights in the battle.
     */
    void printBattleText(ArrayList<MOB> monsters, ArrayList<Knight> activeKnights);

    /**
     * Displays that the monster was defeated.
     *
     * @param dead the defeated monster.
     */
    void printBattleText(MOB dead);

    /**
     * Displays when all knights are defeated.
     */
    void printDefeated();

    /**
     * Displays a confirmation when a knight has been activated.
     *
     * @param knight the activated knight.
     */
    void setActiveSucceeded(Knight knight);

    /**
     * Displays when a knight cannot be activated.
     */
    void setActiveFailed();

    /**
     * Displays a confirmation when a knight has been deactivated.
     *
     * @param knight the deactivated knight.
     */
    void removeActiveSucceeded(Knight knight);
}
