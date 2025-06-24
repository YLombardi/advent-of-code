package fr.ylombardi.adventofcode.y2022.d9;

import java.util.Objects;

public class Position {

    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void move(Direction direction) {
        switch (direction) {
            case U -> y++;
            case D -> y--;
            case L -> x--;
            case R -> x++;
        }
    }

    public void follow(Position head) {
        int deltaX = head.x - this.x;
        int deltaY = head.y - this.y;

        if (Math.abs(deltaX) > 1 || Math.abs(deltaY) > 1) {
            if (deltaX > 0) {
                x++;
            } else if (deltaX < 0) {
                x--;
            }

            if (deltaY > 0) {
                y++;
            } else if (deltaY < 0) {
                y--;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
