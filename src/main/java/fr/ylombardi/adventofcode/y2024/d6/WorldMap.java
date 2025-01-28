package fr.ylombardi.adventofcode.y2024.d6;

public class WorldMap {
    private final char[][] map;

    public WorldMap(char[][] map) {
        this.map = map;
    }

    public char get(int x, int y) {
        return map[y][x];
    }

    public int getWidth() {
        return map[0].length;
    }

    public int getHeight() {
        return map.length;
    }

    public boolean isObstacle(int x, int y) {
        return get(x, y) == '#';
    }

    public boolean isFree(int x, int y) {
        return get(x, y) == '.';
    }

    public Guard findGuard() {
        // find the starting position and direction represented by a '^', 'v', '<' or '>'
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                char c = get(x, y);
                if (c == '^' || c == 'v' || c == '<' || c == '>') {
                    return new Guard(x, y, Direction.fromChar(c));
                }
            }
        }
        throw new IllegalArgumentException("WTF, the guard isn't working today?");
    }

    public void markTileAsVisited(int x, int y) {
        // Change visited position to 'X'
        map[y][x] = 'X';
    }

    public void placeGuardToNextPosition(int nextX, int nextY, Direction direction) {
        // Move the guard to the next position
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
                if (map[y][x] == 'X') {
                    count++;
                }
            }
        }
        return count;
    }

    public void addObstacle(int x, int y) {
        // Add an obstacle represented by a '#'
        map[y][x] = '#';
    }

    public boolean hasLoop() {
        Guard guard = findGuard();

        char[][] copy = new char[getHeight()][getWidth()];
        for (int y = 0; y < getHeight(); y++) {
            System.arraycopy(map[y], 0, copy[y], 0, getWidth());
        }

        // Check if the guard loops
        while (true) {
            int nextX = guard.getNextX();
            int nextY = guard.getNextY();
            if (nextX < 0 || nextX >= getWidth() || nextY < 0 || nextY >= getHeight()) {
                // Fin de la ronde
                return false;
            }
            if (isObstacle(nextX, nextY)) {
                guard.turnRight();
            } else {
                guard.move();
                // Si le garde revient sur une position déjà visitée, alors ça boucle
                if (nextX == guard.getX() && nextY == guard.getY() && copy[nextY][nextX] == guard.getDirection().getSymbol()) {
                    return true;
                }
                copy[nextY][nextX] = guard.getDirection().getSymbol();
            }
        }
    }

    public void removeObstacle(int x, int y) {
        // Remove an obstacle
        map[y][x] = '.';
    }
}
