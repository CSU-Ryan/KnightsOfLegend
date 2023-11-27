package IO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;

import GameObjects.*;

/**
 * Handles accessing and manipulating data during gameplay.
 */
public abstract class GameData {
    private final Random RANDOM;

    protected ArrayList<Fortune> fortunes;
    protected ArrayList<Knight> knights;

    private final int MAX_ACTIVE;
    protected ArrayList<Knight> activeKnights;

    protected ArrayList<MOB> monsters;


    /**
     * Constructs an object to hold and access gamedata.
     */
    public GameData() {
        RANDOM = new Random();

        fortunes = new ArrayList<>();
        knights = new ArrayList<>();

        MAX_ACTIVE = 4;
        activeKnights = new ArrayList<>();

        monsters = new ArrayList<>();
    }

    /**
     * Attempts to parse a string into an int.
     *
     * @param str the int as string
     * @return if str is an int, returns Optional.of(int), otherwise Optional.empty().
     */
    private Optional<Integer> parseInt(String str) {
        try {
            return Optional.of(Integer.parseInt(str));
        }
        catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    /**
     * Gets the knights.
     *
     * @return the list of knights
     */
    public List<Knight> getKnights() {
        return knights;
    }

    /**
     * Gets the active knights.
     *
     * @return the list of active knights
     */
    public List<Knight> getActiveKnights() {
        return activeKnights;
    }

    /**
     * Gets an active knight.
     *
     * @param nameOrId the name or ID of the knight
     * @return the active knight
     * @throws NoSuchElementException if knight is not in list
     */
    public Knight getActive(String nameOrId) throws NoSuchElementException {
        return findKnight(nameOrId, activeKnights);
    }

    /**
     * Gets a knight.
     *
     * @param nameOrId the name or ID of the knight
     * @return the knight
     * @throws NoSuchElementException if knight is not in list
     */
    public Knight getKnight(String nameOrId) throws NoSuchElementException {
        return findKnight(nameOrId, knights);
    }

    /**
     * Finds a Knight.
     *
     * @param nameOrId the name or ID of the knight
     * @param list     the list of knights to search
     * @return the knight from the list
     * @throws NoSuchElementException if knight is not in list
     * @deprecated use findKnightName or findKnightID for better clarity of use
     */
    protected Knight findKnight(String nameOrId, List<Knight> list) throws NoSuchElementException {
        Optional<Integer> search = parseInt(nameOrId);
        Optional<Knight> result;

        // If input is an ID.
        if (search.isPresent()) {
            result = findKnightID(search.get(), list);
        }
        // If input is a name.
        else {
            result = findKnightName(nameOrId, list);
        }

        if (result.isPresent()) {
            return result.get();
        }
        else throw new NoSuchElementException("Knight `" + nameOrId + "` not found.");
    }

    /**
     * Finds a knight.
     *
     * @param searchName the name of the knight
     * @param list       the list of knights to search
     * @return Optional of the knight from the list. If not found, Optional none.
     */
    protected Optional<Knight> findKnightName(String searchName, List<Knight> list) {
        searchName = searchName.trim().toLowerCase();
        String knightName;

        for (Knight knight : list) {
            knightName = knight.getName().toLowerCase();

            if (knightName.contains(searchName)) {
                return Optional.of(knight);
            }
        }
        return Optional.empty();
    }

    /**
     * Finds a knight.
     *
     * @param id   the ID of the knight
     * @param list the list of knights to search
     * @return Optional of the knight from the list. If not found, Optional none.
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
     * Adds knight to active party.
     *
     * @param knight the knight to add
     * @return whether party has space to add knight
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
     *
     * @param knight knight to remove
     */
    public void removeActive(Knight knight) {
        activeKnights.remove(knight);
        knight.resetDamage();
    }

    /**
     * Gets a random {@link Fortune}.
     *
     * @return a fortune
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
     *
     * @param encounterSize number of monsters to return
     * @return a list of MOBs
     */
    public List<MOB> getRandomMonsters(int encounterSize) {
        ArrayList<MOB> encounter = new ArrayList<>(encounterSize);

        for (int i = 0; i < encounterSize; ++i) {
            encounter.add(getRandomMonsterCopy());
        }

        return encounter;
    }

    /**
     * Gets a new random monster.
     *
     * @return a copy of a random monster.
     */
    public MOB getRandomMonsterCopy() {
        int randomMOBIndex = RANDOM.nextInt(monsters.size());
        return monsters.get(randomMOBIndex).copy();
    }

    /**
     * Saves the current game-state to the given file.
     *
     * @param filename file to save data
     */
    public abstract void save(String filename) throws IOException;
}
