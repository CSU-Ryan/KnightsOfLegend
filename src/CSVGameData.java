import java.util.Scanner;

public class CSVGameData extends GameData {

    /**
     * Loads CSV files for editing.
     * @param gamedata A game data file containing fortunes and MOBS.
     * @param saveData A game data file containing knights.
     */
    public CSVGameData(String gamedata, String saveData) {
        //TODO: Implement
    }

    /**
     * Loads knights from save file.
     * @param saveData filename of save file.
     */
    public void loadSaveData(String saveData) {
        //Starts a counter for the IDs, with each new knight being assigned an ID in order of which they are read from the file
        //TODO: implement
    }

    /**
     * @param fileName name of file to read.
     * @return Scanner of file.
     */
    private Scanner readFile(String fileName) {
        //TODO: implement
        return new Scanner("");
    }

    /**
     * Loads Fortunes and MOBs.
     * @param gamedata name of data file.
     */
    public void loadGameData(String gamedata) {
        //TODO: implement
    }

    /**
     * Parses Fortune/MOB from data file.
     * @param line Fortune/MOB data.
     */
    private void parseGameDataLine(Scanner line) {
        //TODO: implement
    }

    /**
     * Saves the current gamestate to the given file.
     * @param filename file to save data
     */
    @Override
    public void save(String filename) {
        //TODO: Implement
    }
}
