package fr.ylombardi.adventofcode.y2024.d6.emoji;

public class PositionAndDirection {
    private int x;
    private int y;
    private Direction direction;

    public PositionAndDirection(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }
}
