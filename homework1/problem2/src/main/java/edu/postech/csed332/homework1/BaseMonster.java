package edu.postech.csed332.homework1;


import java.util.HashSet;

public abstract class BaseMonster implements Monster {

    protected boolean isGround;
    protected GameBoard board;
    protected int stokeCount;

    public BaseMonster(GameBoard board, boolean isGround) {
        this.isGround = isGround;
        this.board = board;
    }

    @Override
    public Position move() {
        if (stokeCount > 4) {
            HashSet<Monster> s = new HashSet<>();
            s.add(this);
            getBoard().monsterKilled(s);
            return null;
        }
        int indexToCheck = (isGround) ? 0 : 1;
        Position currentPosition = getBoard().getPosition(this);
        if ( currentPosition.getX() < getBoard().getWidth()
                && currentPosition.getX() < getBoard().getGoalPosition().getX()
                && !getBoard().isOccupied(currentPosition.getX() + 1, currentPosition.getY(), indexToCheck)) {
            stokeCount = 0;
            return new Position(currentPosition.getX() + 1, currentPosition.getY());
        }
        if (currentPosition.getY() < getBoard().getHeight()
                && currentPosition.getY() < getBoard().getGoalPosition().getY()
                && !getBoard().isOccupied(currentPosition.getX(), currentPosition.getY() + 1, indexToCheck)) {
            stokeCount = 0;
            return new Position(currentPosition.getX(), currentPosition.getY() + 1);
        }
        if (currentPosition.getX() > 0
                && currentPosition.getX() > getBoard().getGoalPosition().getX()
                && !getBoard().isOccupied(currentPosition.getX() - 1, currentPosition.getY(), indexToCheck)) {
            stokeCount = 0;
            return new Position(currentPosition.getX() - 1, currentPosition.getY());
        }
        if (currentPosition.getY() > 0
                && currentPosition.getY() > getBoard().getGoalPosition().getY()
                && !getBoard().isOccupied(currentPosition.getX(), currentPosition.getY() - 1, indexToCheck)) {
            stokeCount = 0;
            return new Position(currentPosition.getX(), currentPosition.getY() - 1);
        }
        stokeCount++;
        return null;
    }

    @Override
    public boolean isGround() {
        return isGround;
    }

    @Override
    public GameBoard getBoard() {
        return board;
    }
}
