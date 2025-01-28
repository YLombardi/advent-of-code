package fr.ylombardi.adventofcode.y2024.d6.emoji;

public class WorldMap {
    private final String[][] map;

    public WorldMap(String[][] map) {
        this.map = map;
    }

    public String get(int x, int y) {
        return map[y][x];
    }

    public int getWidth() {
        return map[0].length;
    }

    public int getHeight() {
        return map.length;
    }

    public boolean isObstacle(int x, int y) {
        return "#".equals(get(x, y));
    }

    public PositionAndDirection findCurrentPositionAndDirection() {
        // find the starting position and direction represented by a '^', 'v', '<' or '>'
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                String c = get(x, y);
                if ("^".equals(c) || "v".equals(c) || "<".equals(c) || ">".equals(c)) {
                    return new PositionAndDirection(x, y, Direction.fromChar(c.charAt(0)));
                }
            }
        }
        throw new IllegalArgumentException("No starting position found");
    }

    public void markTileAsVisited(int x, int y) {
        // Change visited position to 'X'
//        map[y][x] = 'X';
        map[y][x] = RandomEmoji.randomEmoji();
    }

    public void moveToNextPosition(int nextX, int nextY, Direction direction) {
        // Move to the next position
        map[nextY][nextX] = direction.getSymbol();
    }

    public void printMap() {
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                System.out.print(map[y][x]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public int countNumberOfVisitedTiles() {
        // Compte le nombre de X sur la carte
        int count = 0;
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                if ("X".equals(map[y][x])) {
                    count++;
                }
            }
        }
        return count;
    }
}
