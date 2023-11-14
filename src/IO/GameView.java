package IO;

import GameObjects.*;
import java.awt.*;

public interface GameView {
    boolean checkContinue();

    String displayMainMenu();

    void endGame();

    void listKnights(List);

    void printBattleText(List, List);

    void printBattleText(MOB);

    void printDefeated();

    void printFortunes(List);

    void printHelp();

    void setActiveFailed();

    void showKnight(Knight);

    void splashScreen();
}
