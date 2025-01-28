package fr.ylombardi.adventofcode.y2024.d6.emoji;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day6Year2024Bis {

    public int part1(String fileName) {
        WorldMap map = input(fileName);
        PositionAndDirection positionAndDirection = map.findCurrentPositionAndDirection();
        map.printMap();

        // A partir de la position de départ, on va avancer en suivant la direction
        // jusqu'à rencontrer un obstacle
        int x = positionAndDirection.getX();
        int y = positionAndDirection.getY();
        Direction direction = positionAndDirection.getDirection();

        while (true) {
            int nextX = x + direction.getDx();
            int nextY = y + direction.getDy();
            // Si les positions sont en dehors de la carte, on s'arrête
            if (nextX < 0 || nextX >= map.getWidth() || nextY < 0 || nextY >= map.getHeight()) {
                map.markTileAsVisited(x, y);
                break;
            }
            if (map.isObstacle(nextX, nextY)) {
                // Tourne à droite
                direction = switch (direction) {
                    case UP -> Direction.RIGHT;
                    case DOWN -> Direction.LEFT;
                    case LEFT -> Direction.UP;
                    case RIGHT -> Direction.DOWN;
                };
            } else {
                map.markTileAsVisited(x, y);
                map.moveToNextPosition(nextX, nextY, direction);
                x = nextX;
                y = nextY;
            }
        }

        map.printMap();
        return map.countNumberOfVisitedTiles();
    }

    public WorldMap input(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            List<char[]> map = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                map.add(line.toCharArray());
            }
            char[][] array = map.toArray(new char[0][0]);
            // convert char[][] to String[][]
            String[][] stringArray = new String[array.length][array[0].length];
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    stringArray[i][j] = String.valueOf(array[i][j]);
                }
            }
            return new WorldMap(stringArray);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
