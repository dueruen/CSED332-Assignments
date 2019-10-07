package edu.postech.csed332.homework1;

import java.util.*;

/**
 * A game board contains a set of units and a goal position. A game consists
 * of a number of rounds. For each round, all units perform their actions:
 * a monster can escape or move, and a tower can attack nearby monsters.
 * The following invariant conditions must be maintained throught the game:
 * <p>
 * (a) The position of each unit is inside the boundaries.
 * (b) Different ground units cannot be on the same tile.
 * (c) Different air units cannot be on the same tile.
 * <p>
 * TODO: implements all the unimplemented methods (marked with TODO)
 * NOTE: do not modify the signature of public methods (which will be
 * used for GUI and grading). But you can feel free to add new fields
 * or new private methods if needed.
 */
public class GameBoard {
    private final Position goal;
    private final int width, height;
    private int numMobsKilled, numMobsEscaped;

    // TODO: add more fields to implement this class
    private Unit[][][] gameUnits;

    /**
     * Creates a game board with a given width and height. The goal position
     * is set to the middle of the end column.
     *
     * @param width  of this board
     * @param height of this board
     */
    public GameBoard(int width, int height) {
        this.width = width;
        this.height = height;
        goal = new Position(width - 1, height / 2);

        gameUnits = new Unit[height][width][2];
    }

    /**
     * Places a unit at a given position into this board.
     *
     * @param obj a unit to be placed
     * @param p   the position of obj
     * @throws IllegalArgumentException if p is outside the bounds of the board.
     */
    public void placeUnit(Unit obj, Position p) {
        if (p == null) {
            return;
        }
        if (p == null || 0 > p.getX() || p.getX() >= width || 0 > p.getY() || p.getY() >= height) {
            throw new IllegalArgumentException();
        }
        Position prevPos = getPosition(obj);
        if (obj.isGround()) {

            if (gameUnits[p.getY()][p.getX()][0] == null) {
                if (prevPos != null) {
                    gameUnits[prevPos.getY()][prevPos.getX()][0] = null;
                }
                gameUnits[p.getY()][p.getX()][0] = obj;
            }
        } else if (gameUnits[p.getY()][p.getX()][1] == null){
            if (prevPos != null) {
                gameUnits[prevPos.getY()][prevPos.getX()][1] = null;
            }
            gameUnits[p.getY()][p.getX()][1] = obj;
        }
    }

    /**
     * Clears this game board. That is, all units are removed, and all numbers
     * for game statistics are reset to 0.
     */
    public void clear() {
        gameUnits = new Unit[height][width][2];
        numMobsKilled = 0;
        numMobsEscaped = 0;
    }

    /**
     * Returns the set of units at a given position in the board. Note that
     * multiple units can exist at the same position (e.g., ground and air)
     *
     * @param p a position
     * @return the set of units at position p
     */
    public Set<Unit> getUnitsAt(Position p) {
        Set<Unit> units = new HashSet<>();
        for (Unit u : gameUnits[p.getX()][p.getY()]) {
            if (u != null) {
                units.add(u);
            }
        }
        return units;
    }

    /**
     * Returns the set of all monsters in this board.
     *
     * @return the set of all monsters
     */
    public Set<Monster> getMonsters() {
        HashSet<Monster> monsters = new HashSet<>();
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                for (Unit u : gameUnits[row][col]) {
                    if (u instanceof Monster) {
                        monsters.add((Monster) u);
                    }
                }
            }
        }
//        System.out.println("MONSTER SIZE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!: " + monsters.size());
        return monsters;
    }

    /**
     * Returns the set of all towers in this board.
     *
     * @return the set of all towers
     */
    public Set<Tower> getTowers() {
        HashSet<Tower> towers = new HashSet<>();
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Unit u = gameUnits[row][col][0];
                if (u instanceof Tower) {
                    towers.add((Tower) u);
                }
            }
        }
        return towers;
    }

    /**
     * Returns the position of a given unit
     *
     * @param obj a unit
     * @return the position of obj
     */
    public Position getPosition(Unit obj) {
        Position p = null;
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                for (Unit u : gameUnits[row][col]) {
                    if (u != null && u.equals(obj)) {
                        p = new Position(col, row);
                    }
                }
            }
        }
        return p;
    }

    /**
     * Proceeds one round of a game. The behavior of this method is as follows:
     * (1) Every monster at the goal position escapes from the game.
     * (2) Each tower attacks nearby remaining monsters (using the attack method).
     * (3) All remaining monsters (neither escaped nor attacked) moves (using the goal method).
     */
    public void step() {
        for (Monster m : getMonsters()) {
            if (m.getPosition().equals(getGoalPosition())) {
                monsterEscape(m);
            }
        }
        for (Tower t : getTowers()) {
            monsterKilled(t.attack());
        }
        for (Monster m : getMonsters()) {
            placeUnit(m, m.move());

        }
    }

    /**
     * Checks whether the following invariants hold in the current game board:
     * (a) All units are in the boundaries.
     * (b) Different ground units cannot be on the same tile.
     * (c) Different air units cannot be on the same tile.
     *
     * @return true if there is no problem. false otherwise.
     */
    public boolean isValid() {
        return true;
    }

    /**
     * Returns the number of the monsters in this board.
     *
     * @return the number of the monsters
     */
    public int getNumMobs() {
        return getMonsters().size();
    }

    /**
     * Returns the number of the towers in this board.
     *
     * @return the number of the towers
     */
    public int getNumTowers() {
        return getTowers().size();
    }

    /**
     * Returns the number of the monsters removed so far in this game.
     * This number will be reset to 0 by the clear method.
     *
     * @return the number of the monsters removed
     */
    public int getNumMobsKilled() {
        return numMobsKilled;
    }

    /**
     * Returns the number of the monsters escaped so far in this game
     * This number will be reset to 0 by the clear method.
     *
     * @return the number of the monsters escaped
     */
    public int getNumMobsEscaped() {
        return numMobsEscaped;
    }

    /**
     * Returns the width of this board.
     *
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of this board.
     *
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the goal position where the monster can escape.
     *
     * @return the goal position of this game
     */
    public Position getGoalPosition() {
        return goal;
    }

    public Unit[][][] getGameUnits() {
        return gameUnits;
    }

    public boolean isOccupied(int x, int y, int index) {
        if (x != width && y != height && gameUnits[y][x][index] == null ) {
            return false;
        }
        return true;
    }

    private void monsterEscape(Monster monster) {
        Position p = getPosition(monster);
        int x = 0, y = 0, i = 0;
        if (monster.isGround()) {
            gameUnits[getGoalPosition().getY()][getGoalPosition().getX()][0] = null;
            x = getGoalPosition().getX();
            y = getGoalPosition().getY();
        } else {
            gameUnits[getGoalPosition().getY()][getGoalPosition().getX()][1] = null;
        }
        numMobsEscaped++;
    }

    public void monsterKilled(Set<Monster> monsters) {
        if (monsters.size() == 0) {
            return;
        }
        for (Monster m : monsters) {
            Position p = getPosition(m);
            if (m.isGround()) {
                gameUnits[p.getY()][p.getX()][0] = null;
            } else {
                gameUnits[p.getY()][p.getX()][1] = null;
            }
        }
        numMobsKilled += monsters.size();
    }
}
