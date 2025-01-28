package fr.ylombardi.adventofcode.y2024.d6;

public class Guard {
    private int x;
    private int y;
    private Direction direction;

    public Guard(int x, int y, Direction direction) {
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

    public int getNextX() {
        return x + direction.getDx();
    }

    public int getNextY() {
        return y + direction.getDy();
    }

    public void turnRight() {
        direction = switch (direction) {
            case UP -> Direction.RIGHT;
            case DOWN -> Direction.LEFT;
            case LEFT -> Direction.UP;
            case RIGHT -> Direction.DOWN;
        };
    }

    public void move() {
        x += direction.getDx();
        y += direction.getDy();
    }
}
