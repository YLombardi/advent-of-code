package fr.ylombardi.adventofcode.y2024.d4;

public enum Direction {
    UP          (-1, 0),
    UP_RIGHT    (-1, 1),
    RIGHT       (0, 1),
    DOWN_RIGHT  (1, 1),
    DOWN        (1, 0),
    DOWN_LEFT   (1, -1),
    LEFT        (0, -1),
    UP_LEFT     (-1, -1);

    private final int rowShift;
    private final int colSHift;

    Direction(int rowShift, int colSHift) {
        this.rowShift = rowShift;
        this.colSHift = colSHift;
    }

    public int rowIndex() {
        return rowShift;
    }

    public int colIndex() {
        return colSHift;
    }
}
