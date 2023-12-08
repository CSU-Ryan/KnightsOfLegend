import GameEngine.CombatEngine;
import IO.*;

import java.util.Scanner;


public class Main {
    private static String gameDataPath = "GameData\\NormalData";
    private static String saveDataPath = "SaveFiles\\default.csv";

    public Main() {}

    public static void main(String[] args) {
        processArgs(args);

        GameData data = new CSVGameData(gameDataPath, saveDataPath);
        GameView view = new ConsoleView();
        CombatEngine engine = new CombatEngine(data, view);
        GameController controller = new GameController(data, view, engine);

        controller.start();
    }

    private static void processArgs(String[] args) {
        for (String arg : args) {
            Scanner scanner = new Scanner(arg);
            scanner.useDelimiter("=");
            String next;

            while (scanner.hasNext()) {
                next = scanner.next();
                if (next.equals("--data")) {
                    gameDataPath = scanner.next();
                    next = scanner.next();
                }
                saveDataPath = next;
            }
        }
    }
}
