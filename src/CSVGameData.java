import java.io.*;
import java.util.Scanner;

public class CSVGameData extends GameData {

    /**
     * Loads CSV files for editing.
     * @param gamedata A game data file containing fortunes and MOBS.
     * @param saveData A game data file containing knights.
     */
    public CSVGameData(String gamedata, String saveData) {
        super();
        loadGameData(gamedata);
        loadSaveData(saveData);
    }

    /**
     * Creates a Knight object from CSV line.
     * @param line CSV data for Knight.
     * @return Knight object.
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
     * @param line CSV data for MOB.
     * @return MOB object.
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
     * @param line CSV data for Fortune.
     * @return Fortune object.
     */
    private Fortune parseFortune(Scanner line) {
        line.useDelimiter(",");

        return new Fortune(
                line.next().trim(),
                line.nextInt(),
                line.nextInt(),
                line.nextInt(),
                DiceType.typeOf(line.next().trim())
        );
    }

    /**
     * @param fileName name of file to read.
     * @return Scanner of file.
     */
    private Scanner readFile(String fileName) {
        try {
            return new Scanner(new File(fileName));
        }
        catch (FileNotFoundException e) {
            System.err.println(e);
        }
        return new Scanner("");
    }

    /**
     * Parses Fortune/MOB from data file.
     * @param line Fortune/MOB data.
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
     * Loads Fortunes and MOBs.
     * @param gamedata name of data file.
     */
    public void loadGameData(String gamedata) {
        Scanner file = readFile(gamedata);
        Scanner line;
        while (file.hasNext()) {
            line = new Scanner(file.nextLine());
            parseGameDataLine(line);
        }
    }

    /**
     * Loads knights from save file.
     * @param saveData filename of save file.
     */
    public void loadSaveData(String saveData) {
        int idCount = 0;
        Scanner file = readFile(saveData);

        while (file.hasNext()) {
            ++idCount;
            knights.add(parseKnight(idCount, new Scanner(file.nextLine())));
        }
    }

    /**
     * Saves the current gamestate to the given file.
     * @param filename file to save data
     */
    @Override
    public void save(String filename) {
        try (PrintWriter file = new PrintWriter(filename)) {
            for (Knight knight : knights) {
                file.println(knight.toCSV());
            }
            file.close();
        }
        catch (IOException e) {
            System.out.println("ERROR: Failed to save game.");
            System.err.println(e);
        }
    }

    public static void main(String[] args) {
        String gamedata = "GameData/test_gamedata.csv";
        String savedata = "GameData/test_savedata.csv";
        String saveFile = "GameData/test_savefile.csv";
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

        gameData.save(saveFile);
    }
}
