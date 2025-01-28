package fr.ylombardi.adventofcode.y2024.d6.emoji;

public enum Direction {
    UP("^", 0, -1),
    DOWN("v", 0, 1),
    LEFT("<", -1, 0),
    RIGHT(">", 1, 0);

    private final String symbol;
    private final int dx;
    private final int dy;

    Direction(String symbol, int dx, int dy) {
        this.symbol = symbol;
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public String getSymbol() {
        return symbol;
    }

    public static Direction fromChar(char c) {
        return switch (c) {
            case '^' -> UP;
            case 'v' -> DOWN;
            case '<' -> LEFT;
            case '>' -> RIGHT;
            default -> throw new IllegalArgumentException("Invalid direction: " + c);
        };
    }
}
