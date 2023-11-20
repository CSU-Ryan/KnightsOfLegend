package IO;

import GameObjects.*;
import java.util.ArrayList;

public interface GameView {
    /**
     * Displays an exception.
     * @param e The exception to display.
     */
    void displayException(Exception e);

    /**
     * Displays a splashscreen at the launch of the game.
     */
    void splashScreen();

    /**
     * Displays option menu for the player.
     *
     * @return The player's input.
     */
    String displayMainMenu();

    /**
     * Displays a save confirmation.
     * @param filename
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
     * @return The player's response.
     */
    boolean checkContinue();

    /**
     * Displays the given knight.
     *
     * @param knight The knight to be displayed.
     */
    void showKnight(Knight knight);

    /**
     * Display when a knight is not found.
     */
    void knightNotFound();

    /**
     * Lists the knights.
     *
     * @param knights List of knights to display.
     */
    void listKnights(ArrayList<Knight> knights);

    /**
     * Displays the fortunes of the active knights.
     *
     * @param activeKnights Knights to display fortunes.
     */
    void printFortunes(ArrayList<Knight> activeKnights);

    /**
     * Displays the battle members.
     *
     * @param monsters Monsters in the battle.
     * @param activeKnights Knights in the battle.
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
