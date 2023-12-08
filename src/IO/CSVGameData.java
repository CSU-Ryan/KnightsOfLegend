package IO;

import GameEngine.DiceType;
import GameObjects.Effects.Fortune;
import GameObjects.MobileObjects.Knight;
import GameObjects.MobileObjects.MOB;

import java.io.*;
import java.util.*;

/**
 * Handles accessing and manipulating CSV data during gameplay.
 */
public class CSVGameData extends GameData {

    /**
     * Constructs a manager for data from CSV files.<br>
     * <br>
     * <p>
     *     gameFolder expects two CSV files, named "fortunes" and "monsters".<br>
     *     saveFolder expects one CSV file, named "knights".
     * </p>
     *
     * @param gameFolder the path to the game data folder
     * @param saveFolder the path to the save data folder
     */
    public CSVGameData(String gameFolder, String saveFolder) {
        super();

        try {
            parseGameFolder(gameFolder);
            parseSaveFile(saveFolder);
        }
        catch (FileNotFoundException e) {
            System.err.println(e);
        }
    }

    private void parseGameFolder(String gameFolderPath) throws FileNotFoundException {
        Map<String, File> gameFolder = parseFolder(gameFolderPath);

        try {
            Scanner fortuneData = new Scanner(gameFolder.get("fortunes.csv"));
            Scanner monsterData = new Scanner(gameFolder.get("monsters.csv"));

            parseFortunes(fortuneData);
            parseMOBs(monsterData);
        }
        catch (FileNotFoundException e) {
            throw new FileNotFoundException("GameData folder does not contain required files.");
        }
    }

    private void parseSaveFile(String saveFilePath) throws FileNotFoundException {
        parseKnights(readFile(saveFilePath));
    }

    private static Map<String, File> parseFolder(String folderPath) {
        HashMap<String, File> dataFiles = new HashMap<>();
        File[] folder = Objects.requireNonNull(new File(folderPath).listFiles());

        for (File dataFile : folder) {
            dataFiles.put(dataFile.getName(), dataFile);
        }
        return dataFiles;
    }

    public void parseKnights(Scanner KnightData) {
        ArrayList<Knight> knights = new ArrayList<>();
        Scanner KnightLine;
        int id = 1;

        while (KnightData.hasNext()) {
            KnightLine = new Scanner(KnightData.nextLine());
            knights.add(parseKnight(id, KnightLine));
            ++id;
        }
        this.knights = knights;
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
    private static Knight parseKnight(int idCount, Scanner line) {
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

    public void parseMOBs(Scanner MOBData) {
        ArrayList<MOB> mobs = new ArrayList<>();
        Scanner MOBLine;

        while (MOBData.hasNext()) {
            MOBLine = new Scanner(MOBData.nextLine());
            mobs.add(parseMOB(MOBLine));
        }
        this.monsters = mobs;
    }

    /**
     * Creates a MOB object from CSV line.
     *
     * @param MOBLine CSV data for the MOB
     * @return the knight from the CSV data
     */
    private static MOB parseMOB(Scanner MOBLine) {
        MOBLine.useDelimiter(",");

        return new MOB(
                MOBLine.next().trim(),
                MOBLine.nextInt(),
                MOBLine.nextInt(),
                MOBLine.nextInt(),
                DiceType.typeOf(MOBLine.next().trim())
        );
    }

    public void parseFortunes(Scanner FortuneData) {
        ArrayList<Fortune> fortunes = new ArrayList<>();
        Scanner FortuneLine;

        while (FortuneData.hasNext()) {
            FortuneLine = new Scanner(FortuneData.nextLine());
            fortunes.add(parseFortune(FortuneLine));
        }
        this.fortunes = fortunes;
    }

    /**
     * Creates a Fortune object from CSV line.
     *
     * @param fortuneLine CSV data for the fortune
     * @return the fortune from the CSV data
     */
    private static Fortune parseFortune(Scanner fortuneLine) {
        fortuneLine.useDelimiter(",");

        return new Fortune(
                fortuneLine.next().trim(),
                fortuneLine.nextInt(),
                fortuneLine.nextInt(),
                fortuneLine.nextInt(),
                fortuneLine.nextInt(),
                DiceType.typeOf(fortuneLine.next().trim())
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
    private static Scanner readFile(String filepath) {
        try {
            return new Scanner(new File(filepath));
        }
        catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return new Scanner("");
    }

    private static Scanner readFile(File file) {
        try {
            return new Scanner(file);
        }
        catch (FileNotFoundException e) {
            throw new Error("File not found");
        }
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
