package fr.ylombardi.adventofcode.y2024.d6;

import fr.ylombardi.adventofcode.utils.InputParsingUtils;

public class Day6Year2024 {

    public int part1(String fileName) {
        WorldMap map = input(fileName);
        Guard guard = map.findGuard();
        map.printMap();

        while (true) {
            int nextX = guard.getNextX();
            int nextY = guard.getNextY();
            if (nextX < 0 || nextX >= map.getWidth() || nextY < 0 || nextY >= map.getHeight()) {
                // Si les prochaines positions sont en dehors de la carte, le tour de garde s'arrête
                map.markTileAsVisited(guard.getX(), guard.getY());
                break;
            }
            if (map.isObstacle(nextX, nextY)) {
                // Tourne à droite
                guard.turnRight();
            } else {
                // Le garde se déplace
                map.markTileAsVisited(guard.getX(), guard.getY());
                map.placeGuardToNextPosition(nextX, nextY, guard.getDirection());
                guard.move();
            }
        }

        map.printMap();
        return map.countNumberOfVisitedTiles();
    }

    public int part2(String fileName) {
        WorldMap map = input(fileName);
        int nbLoop = 0;

        // Sur chaque case de la carte, ajouter un obstacle et voir si le garde boucle
        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                // Si la case est libre
                if (map.isFree(x, y)) {
                    // Ajoute un obstacle
                    map.addObstacle(x, y);
//                    map.printMap();

                    // Faire la ronde pour voir si ça boucle
                    if (map.hasLoop()) {
                        nbLoop++;
                    }

                    // Enlever l'obstacle
                    map.removeObstacle(x, y);
                }
            }
        }

        return nbLoop;
    }

    public WorldMap input(String fileName) {
        return new WorldMap(InputParsingUtils.inputToCharArray(fileName));
    }
}
