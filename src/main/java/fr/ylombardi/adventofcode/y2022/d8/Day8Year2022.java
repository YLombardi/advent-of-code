package fr.ylombardi.adventofcode.y2022.d8;

import fr.ylombardi.adventofcode.utils.InputParsingUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

public class Day8Year2022 {

    int part1(String fileName) {
        Integer[][] input = input(fileName);

        int count = 0;
        for (int row = 0; row < input.length; row++) {
            for (int col = 0; col < input[row].length; col++) {
                boolean isVisible = isVisible(input, row, col);
                count += isVisible ? 1 : 0;
                System.out.print(isVisible ? "♣" : "♢");
            }
            System.out.println();
        }

        return count;
    }

    int part2(String fileName) {
        Integer[][] input = input(fileName);
        
        int maxScore = 0;
        for (int row = 0; row < input.length; row++) {
            for (int col = 0; col < input[row].length; col++) {
                // Pour chaque case, calculer le score vers le haut, le bas, la gauche et la droite
                // Parcours vers le haut
                int scoreTop = 0;
                for (int index = row-1; index >= 0; index--) {
                    if (input[index][col] < input[row][col]) {
                        // Si la case est plus petite, on incrémente le score
                        scoreTop++;
                    } else {
                        // Si même taille ou plus grande, on compte l'arbre et on s'arrête
                        scoreTop++;
                        break;
                    }
                }
                // Parcours vers le bas
                int scoreBottom = 0;
                for (int index = row+1; index < input[row].length; index++) {
                    if (input[index][col] < input[row][col]) {
                        // Si la case est plus petite, on incrémente le score
                        scoreBottom++;
                    } else {
                        // Si même taille ou plus grande, on compte l'arbre et on s'arrête
                        scoreBottom++;
                        break;
                    }
                }
                // Parcours vers la gauche
                int scoreLeft = 0;
                for (int index = col-1; index >= 0; index--) {
                    if (input[row][index] < input[row][col]) {
                        // Si la case est plus petite, on incrémente le score
                        scoreLeft++;
                    } else {
                        // Si même taille ou plus grande, on compte l'arbre et on s'arrête
                        scoreLeft++;
                        break;
                    }
                }
                // Parcours vers la droite
                int scoreRight = 0;
                for (int index = col+1; index < input.length; index++) {
                    if (input[row][index] < input[row][col]) {
                        // Si la case est plus petite, on incrémente le score
                        scoreRight++;
                    } else {
                        // Si même taille ou plus grande, on compte l'arbre et on s'arrête
                        scoreRight++;
                        break;
                    }
                }

                // Calcul du score total
                int currentScore = scoreTop * scoreBottom * scoreLeft * scoreRight;
                if (currentScore > maxScore) {
                    maxScore = currentScore;
                }
            }
        }

        return maxScore;
    }

    private static boolean isVisible(Integer[][] input, int row, int col) {
        Integer currentTree = input[row][col];
        // Check row before
        boolean visibleFromTop = isVisibleFromTop(col, input, row, currentTree);
        // Check row after
        boolean visibleFromBottom = isVisibleFromBottom(col, input, row, currentTree);
        // Check column before
        boolean visibleFromLeft = isVisibleFromLeft(row, input, col, currentTree);
        // Check column after
        boolean visibleFromRight = isVisibleFromRight(row, input, col, currentTree);
        return visibleFromTop || visibleFromBottom || visibleFromLeft || visibleFromRight;
    }

    private static boolean isVisibleFromRight(int row, Integer[][] input, int col, Integer currentTree) {
        boolean visibleFromRight = true;
        for (int index = row; index < input.length; index++) {
            if (input[index][col] >= currentTree && index != row) {
                visibleFromRight = false;
                break;
            }
        }
        return visibleFromRight;
    }

    private static boolean isVisibleFromLeft(int row, Integer[][] input, int col, Integer currentTree) {
        boolean visibleFromLeft = true;
        for (int index = 0; index < row; index++) {
            if (input[index][col] >= currentTree) {
                visibleFromLeft = false;
                break;
            }
        }
        return visibleFromLeft;
    }

    private static boolean isVisibleFromBottom(int col, Integer[][] input, int row, Integer currentTree) {
        boolean visibleFromBottom = true;
        for (int index = col; index < input[row].length; index++) {
            if (input[row][index] >= currentTree && index != col) {
                visibleFromBottom = false;
                break;
            }
        }
        return visibleFromBottom;
    }

    private static boolean isVisibleFromTop(int col, Integer[][] input, int row, Integer currentTree) {
        boolean visibleFromTop = true;
        for (int index = 0; index < col; index++) {
            if (input[row][index] >= currentTree) {
                visibleFromTop = false;
                break;
            }
        }
        return visibleFromTop;
    }

    public Integer[][] input(String fileName) {
        return InputParsingUtils.inputToIntegerArray(fileName);
    }

}
