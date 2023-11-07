import java.util.ArrayList;
import java.util.Random;

public abstract class GameData {
    private final Random rnd = new Random();

    protected ArrayList<Fortune> fortunes;
    protected ArrayList<Knight> knights;

    private final int MAX_ACTIVE = 4;
    protected ArrayList<Knight> activeKnights;

    protected ArrayList<MOB> monsters;


    public GameData() {
        //TODO: implement
    }

    /**
     * @return List of knights.
     */
    public ArrayList<Knight> getKnights() {
        //TODO: implement
        return new ArrayList<Knight>();
    }

    /**
     * @return List of active knights.
     */
    public ArrayList<Knight> getActiveKnights() {
        //TODO: implement
        return new ArrayList<Knight>();
    }

    /**
     * Finds an active knight.
     * @param nameOrId name or ID of the knight.
     * @return the active knight.
     */
    @Deprecated
    public Knight getActive(String nameOrId) {
        //TODO: implement
        return activeKnights.get(0);

    }

    /**
     * Finds a knight.
     * @param nameOrId name or ID of the knight.
     * @return the knight.
     */
    @Deprecated
    public Knight getKnight(String nameOrId) {
        //TODO: implement
        return activeKnights.get(0);
    }

    /**
     * Finds a knight.
     * @param name name of the knight.
     * @param list list of knights.
     * @return the knight from the list.
     */
    protected Knight findKnightName(String name, ArrayList<Knight> list) {
        //TODO: implement
        return activeKnights.get(0);
    }

    /**
     * Finds a knight from the list of knights.
     * @param id ID of the knight.
     * @param list list of knights.
     * @return the knight from the list.
     */
    protected Knight findKnightID(int id, ArrayList<Knight> list) {
        //TODO: implement
        return activeKnights.get(0);
    }

    /**
     * Finds a Knight from the list of knights.
     *
     * @deprecated Use findKnightName or findKnightID.
     * @param nameOrId name or ID of the knight.
     * @param list list of knights.
     * @return the knight from the list.
     */
    @Deprecated
    protected Knight findKnight(String nameOrId, ArrayList<Knight> list) {
        //TODO: implement
        return activeKnights.get(0);
    }

    /**
     * Adds knight to active party
     * @param knight knight to add
     * @return boolean whether party has space to add knight
     */
    public boolean setActive(Knight knight) {
        //TODO: implement
        return false;
    }

    /**
     * Removes knight from active party
     * @param knight knight to remove
     */
    public void removeActive(Knight knight) {
        // Removes a knight from the activeKnights list **and** resets the damage on the knight! Remember, list.remove returns true if the remove was successful.
        //TODO: implement
    }

    /**
     * Gets a random Fortune.
     * @return a Fortune.
     */
    public Fortune getRandomFortune() {
        //TODO: implement
        return fortunes.get(0);
    }

    /**
     * Gets a random list of monsters.
     * @return a list of MOBs.
     */
    public ArrayList<MOB> getRandomMonsters() {
        //TODO: implement
        return monsters;
    }

    /**
     * Gets a list with given size of random monsters.
     * @param size number of monsters to return.
     * @return a list of MOBs.
     */
    public ArrayList<MOB> getRandomMonsters(int size) {
        //TODO: implement
        return monsters;
    }

    /**
     * Saves the current gamestate to the given file.
     * @param filename file to save data
     */
    public abstract void save(String filename);
}
