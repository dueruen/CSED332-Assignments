package edu.postech.csed332.homework1;

import java.util.HashSet;
import java.util.Set;

public class BaseTower implements Tower {

    protected boolean canAttackAir;

    protected GameBoard gameBoard;

    public BaseTower(GameBoard board, boolean canAttackAir) {
        this.gameBoard = board;
        this.canAttackAir = canAttackAir;
    }

    @Override
    public Set<Monster> attack() {
        int indexToCheck = (canAttackAir) ? 1 : 0;
        Position currentPosition = getBoard().getPosition(this);
        Set<Monster> monstersToKill = new HashSet<>();
        if (indexToCheck == 1) {
            Unit u = getBoard().getGameUnits()[currentPosition.getY()][currentPosition.getX()][indexToCheck];
            if (u instanceof Monster) {
                monstersToKill.add((Monster) u);
            }
        }
        if (currentPosition.getX() + 1 < getBoard().getWidth()) {
            Unit u = getBoard().getGameUnits()[currentPosition.getY()][currentPosition.getX() + 1][indexToCheck];
            if (u instanceof Monster) {
                monstersToKill.add((Monster) u);
            }
        }
        if (currentPosition.getX() - 1 > 0) {
            Unit u = getBoard().getGameUnits()[currentPosition.getY()][currentPosition.getX() - 1][indexToCheck];
            if (u instanceof Monster) {
                monstersToKill.add((Monster) u);
            }
        }
        if (currentPosition.getY() + 1 < getBoard().getHeight()) {
            Unit u = getBoard().getGameUnits()[currentPosition.getY() + 1][currentPosition.getX()][indexToCheck];
            if (u instanceof Monster) {
                monstersToKill.add((Monster) u);
            }
        }
        if (currentPosition.getY() - 1 > 0) {
            Unit u = getBoard().getGameUnits()[currentPosition.getY() - 1][currentPosition.getX()][indexToCheck];
            if (u instanceof Monster) {
                monstersToKill.add((Monster) u);
            }
        }
        return monstersToKill;
    }

    @Override
    public GameBoard getBoard() {
        return gameBoard;
    }
}
