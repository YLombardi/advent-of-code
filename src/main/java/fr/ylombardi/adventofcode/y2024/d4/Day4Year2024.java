package fr.ylombardi.adventofcode.y2024.d4;

import fr.ylombardi.adventofcode.utils.InputParsingUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Day4Year2024 {

    public int part1(String fileName) {
        String[][] input = input(fileName);

        AtomicInteger count = new AtomicInteger();
        for (int row = 0; row < input.length; row++) {
            for (int col = 0; col < input[row].length; col++) {
                String currentLetter = input[row][col];
                // Si le caractère est un X, je regarde les 8 caractères autour pour chercher les M
                if ("X".equals(currentLetter)) {
                    int finalRow = row;
                    int finalCol = col;
                    List.of(Direction.values()).forEach(direction -> {
                        if (search(input, finalRow, finalCol, "M", direction)) {
                            count.getAndIncrement();
                        }
                    });
                }
            }
        }

        return count.get();
    }

    public int part2(String fileName) {
        String[][] input = input(fileName);
        // Je parcours toutes les cases
        // Si la case est un A, je regarde s'il y a 2 M et 2 S dans les diagonales
        // Si c'est le cas, je compte 1
        int count = 0;
        for (int row = 0; row < input.length; row++) {
            for (int col = 0; col < input[row].length; col++) {
                String currentLetter = input[row][col];
                if ("A".equals(currentLetter)) {
                    int finalRow = row;
                    int finalCol = col;
                    long numberM = Stream.of(Direction.values())
                            .filter(d -> d.colIndex() != 0)
                            .filter(d -> d.rowIndex() != 0)
                            .filter(direction -> search2(input, finalRow, finalCol, "M", direction))
                            .count();
                    if (numberM == 2) {
                        long numberS = Stream.of(Direction.values())
                                .filter(d -> d.colIndex() != 0)
                                .filter(d -> d.rowIndex() != 0)
                                .filter(direction -> search2(input, finalRow, finalCol, "S", direction))
                                .count();
                        if (numberS == 2) {
                            count++;
                        }
                    }
                }
            }
        }

        return count;
    }

    private boolean search(String[][] input, int row, int col, String searchedLetter, Direction direction) {
        int searchedRow = row + direction.rowIndex();
        int searchedCol = col + direction.colIndex();
        // Si on sort du tableau, on ne cherche pas
        if (searchedRow < 0 || searchedCol < 0 || searchedRow >= input.length || searchedCol >= input[searchedRow].length) {
            return false;
        }
        String currentChar = input[searchedRow][searchedCol];
        if (searchedLetter.equals(currentChar)) {
            if ("M".equals(currentChar)) {
                return search(input, searchedRow, searchedCol, "A", direction);
            } else if ("A".equals(currentChar)) {
                return search(input, searchedRow, searchedCol, "S", direction);
            } else if ("S".equals(currentChar)) {
                return true;
            }
        }
        return false;
    }

    private boolean search2(String[][] input, int row, int col, String searchedLetter, Direction direction) {
        int searchedRow = row + direction.rowIndex();
        int searchedCol = col + direction.colIndex();
        // Si on sort du tableau, on ne cherche pas
        if (searchedRow < 0 || searchedCol < 0 || searchedRow >= input.length || searchedCol >= input[searchedRow].length) {
            return false;
        }
        String value = input[searchedRow][searchedCol];
        return searchedLetter.equals(value);
    }

    public String[][] input(String fileName) {
        return InputParsingUtils.inputToStringArray(fileName);
    }
}
