package IO;

import GameEngine.DiceType;
import GameObjects.Effects.Fortune;
import GameObjects.MobileObjects.Knight;
import GameObjects.MobileObjects.MOB;

import java.io.*;
import java.util.Scanner;

/**
 * Handles accessing and manipulating CSV data during gameplay.
 */
public class CSVGameData extends GameData {

    /**
     * Constructs object from CSV files for usage.
     *
     * @param gamedata the CSV file containing fortunes and MOBS
     * @param saveData the CSV file containing knights
     */
    public CSVGameData(String gamedata, String saveData) {
        super();
        loadGameData(gamedata);
        loadSaveData(saveData);
    }

    /**
     * Creates a Knight object from a CSV line.<br>
     * <br>
     * Knights are stored as<br>
     * <i>name,maxHP,armor,accuracy,damageDie,xp</i><br>
     *
     * @param line CSV data for the knight
     * @return the knight from the CSV data
     */
    private Knight parseKnight(int idCount, Scanner line) {
        line.useDelimiter(",");

        return new Knight(
                idCount,
                line.next().trim(),
                line.nextInt(),
                line.nextInt(),
                line.nextInt(),
                DiceType.typeOf(line.next().trim()),
                line.nextInt()
        );
    }

    /**
     * Creates a MOB object from CSV line.
     *
     * @param line CSV data for the MOB
     * @return the knight from the CSV data
     */
    private MOB parseMOB(Scanner line) {
        line.useDelimiter(",");

        return new MOB(
                line.next().trim(),
                line.nextInt(),
                line.nextInt(),
                line.nextInt(),
                DiceType.typeOf(line.next().trim())
        );
    }

    /**
     * Creates a Fortune object from CSV line.
     *
     * @param line CSV data for the fortune
     * @return the fortune from the CSV data
     */
    private Fortune parseFortune(Scanner line) {
        line.useDelimiter(",");

        return new Fortune(
                line.next(),
                line.nextInt(),
                line.nextInt(),
                line.nextInt(),
                DiceType.typeOf(line.next().trim())
        );
    }

    /**
     * Gets a scanner from a file.<br>
     * <br>
     * If it fails to read the file, returns a scanner from a blank string.
     *
     * @param filepath path of the file to read
     * @return a scanner from the file
     */
    private Scanner readFile(String filepath) {
        try {
            return new Scanner(new File(filepath));
        }
        catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return new Scanner("");
    }

    /**
     * Parses Fortune/MOB/Knight from data file.
     *
     * @param line the object's CSV data
     */
    private void parseGameDataLine(Scanner line) {
        line.useDelimiter(",");

        String type = line.next();
        if (type.equals("MOB")) {
            monsters.add(parseMOB(line));
        }
        else if (type.equals("FORTUNE")) {
            fortunes.add(parseFortune(line));
        }
        else {
            System.err.println("Failed to parse line.");
        }
    }

    /**
     * Loads gamedata files.
     *
     * @param filepath path of the data file
     */
    public void loadGameData(String filepath) {
        Scanner file = readFile(filepath);
        Scanner line;
        while (file.hasNext()) {
            line = new Scanner(file.nextLine());
            parseGameDataLine(line);
        }
    }

    /**
     * Loads knights from save file.
     *
     * @param filepath path of the save file
     */
    public void loadSaveData(String filepath) {
        int idCount = 0;
        Scanner file = readFile(filepath);

        while (file.hasNext()) {
            ++idCount;
            knights.add(parseKnight(idCount, new Scanner(file.nextLine())));
        }
    }

    /**
     * Saves the current game-state to the given file.
     *
     * @param filepath filepath to save data
     */
    @Override
    public void save(String filepath) throws IOException {
        PrintWriter file = new PrintWriter(filepath);

        for (Knight knight : knights) {
            file.println(knight.toCSV());
        }
        file.close();
    }

    public static void main(String[] args) {
        String gamedata = "GameData/test_gamedata.csv";
        String savedata = "GameData/test_savedata.csv";
        String saveFile = "SaveFiles/test.csv";
        Knight knight;

        CSVGameData gameData = new CSVGameData(gamedata, savedata);

        knight = gameData.findKnightID(3, gameData.getKnights()).get();
        gameData.setActive(knight);

        knight = gameData.findKnightName("Your Mother", gameData.getKnights()).get();
        gameData.setActive(knight);

        System.out.println(gameData.getKnights());
        System.out.println();

        System.out.println(gameData.getActiveKnights());
        System.out.println();

        System.out.println(gameData.getRandomFortune());
        System.out.println();

        System.out.println("active knights: " + gameData.getActiveKnights().size());
        System.out.println(gameData.getRandomMonsters());
        System.out.println();

        for (Knight k : gameData.getActiveKnights()) {
            k.addXP(10);
        }

        try {
            gameData.save(saveFile);
        }
        catch (IOException ignored) {}
    }
}
