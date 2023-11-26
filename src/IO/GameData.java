package IO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;

import GameObjects.*;

public abstract class GameData {
    private final Random RANDOM;

    protected ArrayList<Fortune> fortunes;
    protected ArrayList<Knight> knights;

    private final int MAX_ACTIVE;
    protected ArrayList<Knight> activeKnights;

    protected ArrayList<MOB> monsters;


    public GameData() {
        RANDOM = new Random();

        fortunes = new ArrayList<>();
        knights = new ArrayList<>();

        MAX_ACTIVE = 4;
        activeKnights = new ArrayList<>();

        monsters = new ArrayList<>();
    }

    private Optional<Integer> parseInt(String str) {
        try {
            return Optional.of(Integer.parseInt(str));
        }
        catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    /**
     * @return List of knights.
     */
    public List<Knight> getKnights() {
        return knights;
    }

    /**
     * @return List of active knights.
     */
    public List<Knight> getActiveKnights() {
        return activeKnights;
    }

    /**
     * Finds an active knight.
     * @param nameOrId name or ID of the knight.
     * @return the active knight.
     */
    public Knight getActive(String nameOrId) {
        return findKnight(nameOrId, activeKnights);
    }

    /**
     * Finds a knight.
     * @param nameOrId name or ID of the knight.
     * @return the knight.
     */
    public Knight getKnight(String nameOrId) {
        return findKnight(nameOrId, knights);
    }

    /**
     * Finds a knight.
     * @param searchName name of the knight.
     * @param list list of knights.
     * @return the knight from the list.
     */
    protected Optional<Knight> findKnightName(String searchName, List<Knight> list) {
        String knightName;
        for (Knight knight : list) {
            knightName = knight.getName().trim();
            if (knightName.equalsIgnoreCase(searchName)) {
                return Optional.of(knight);
            }
        }
        return Optional.empty();
    }

    /**
     * Finds a knight from the list of knights.
     * @param id ID of the knight.
     * @param list list of knights.
     * @return the knight from the list.
     */
    protected Optional<Knight> findKnightID(int id, List<Knight> list) {
        for (Knight knight : list) {
            if (knight.getId() == id) {
                return Optional.of(knight);
            }
        }
        return Optional.empty();
    }

    /**
     * Finds a Knight from the list of knights.
     *
     * @prefer: findKnightName or findKnightID.
     * @param nameOrId name or ID of the knight.
     * @param list list of knights.
     * @return the knight from the list.
     */
    protected Knight findKnight(String nameOrId, List<Knight> list) throws NoSuchElementException {
        Optional<Integer> search = parseInt(nameOrId);
        Optional<Knight> result;

        if (search.isPresent()) {
            result = findKnightID(search.get(), list);
        }
        else {
            result = findKnightName(nameOrId, list);
        }
        if (result.isPresent()) {
            return result.get();
        }
        else throw new NoSuchElementException("Knight `" + nameOrId + "` not found.");
    }

    /**
     * Adds knight to active party
     * @param knight knight to add
     * @return boolean whether party has space to add knight
     */
    public boolean setActive(Knight knight) {

        if (findKnightID(knight.getId(), activeKnights).isPresent()
                || (activeKnights.size() >= MAX_ACTIVE)) {
            return false;
        }

        activeKnights.add(knight);
        return true;
    }

    /**
     * Removes knight from active party and resets the knight's health.
     * @param knight knight to remove
     */
    public void removeActive(Knight knight) {
        // Removes a knight from the activeKnights list **and** resets the damage on the knight! Remember, list.remove returns true if the remove was successful.
        activeKnights.remove(knight);
        knight.resetDamage();
    }

    /**
     * Gets a random Fortune.
     * @return a Fortune.
     */
    public Fortune getRandomFortune() {
        int randomIndex = RANDOM.nextInt(fortunes.size());
        return fortunes.get(randomIndex);
    }

    /**
     * Gets a random list of monsters.
     *
     * @return a list of MOBs.
     * @implNote may return an empty list.
     */
    public List<MOB> getRandomMonsters() {
        int encounterSize = RANDOM.nextInt(activeKnights.size() + 1);

        return getRandomMonsters(encounterSize);
    }

    /**
     * Gets a list with given size of random monsters.
     * @param encounterSize number of monsters to return.
     * @return a list of MOBs.
     */
    public List<MOB> getRandomMonsters(int encounterSize) {
        ArrayList<MOB> encounter = new ArrayList<>(encounterSize);

        for (int i = 0; i < encounterSize; ++i) {
            encounter.add(getRandomMonsterCopy());
        }

        return encounter;
    }

    /**
     * Gets a random monster from monsters.
     * Returns a copy of the monster.
     *
     * @return a random monster from monsters.
     */
    public MOB getRandomMonsterCopy() {
        int randomMOBIndex = RANDOM.nextInt(monsters.size());
        return monsters.get(randomMOBIndex).copy();
    }

    /**
     * Saves the current game-state to the given file.
     * @param filename file to save data
     */
    public abstract void save(String filename) throws IOException;
}
