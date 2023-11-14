package IO;

import GameObjects.*;
import java.awt.*;

public interface GameView {
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
     * Lists the knights.
     *
     * @param knights List of knights to display.
     */
    void listKnights(List knights);

    /**
     * Displays the fortunes of the active knights.
     *
     * @param activeKnights Knights to display fortunes.
     */
    void printFortunes(List activeKnights);

    /**
     * Displays the battle members.
     *
     * @param monsters Monsters in the battle.
     * @param activeKnights Knights in the battle.
     */
    void printBattleText(List monsters, List activeKnights);

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
     * Displays when a knight cannot be activated.
     */
    void setActiveFailed();
}
