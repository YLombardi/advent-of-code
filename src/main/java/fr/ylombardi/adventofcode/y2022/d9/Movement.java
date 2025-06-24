package fr.ylombardi.adventofcode.y2022.d9;

public record Movement(Direction direction, int steps) {

    public static Movement of(String line) {
        String[] parts = line.split(" ");
        return new Movement(Direction.valueOf(parts[0]), Integer.parseInt(parts[1]));
    }
}
