package fr.ylombardi.adventofcode.y2021.d9;

import fr.ylombardi.adventofcode.utils.InputParsingUtils;
import fr.ylombardi.adventofcode.y2024.d4.Direction;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day9Year2021 {

    public int part1(String fileName) {
        Integer[][] input = parseInput(fileName);

        int totalRiskLevel = 0;
        // Parcours de la grille
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                if (isLowPoint(input, i, j)) {
                    totalRiskLevel += input[i][j] + 1;
                }
            }
        }

        return totalRiskLevel;
    }

    private boolean isLowPoint(Integer[][] input, int i, int j) {
        int currentHeight = input[i][j];

        // Check all 4 neighbors
        if (i > 0 && input[i - 1][j] <= currentHeight) return false; // Up
        if (i < input.length - 1 && input[i + 1][j] <= currentHeight) return false; // Down
        if (j > 0 && input[i][j - 1] <= currentHeight) return false; // Left
        if (j < input[i].length - 1 && input[i][j + 1] <= currentHeight) return false; // Right

        return true;
    }

    public int part2(String fileName) {
        Integer[][] input = parseInput(fileName);

        List<Integer> basinSizes = new ArrayList<>();

        // Parcours de la grille pour trouver les bassins
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                // Trouver les low points
                if (isLowPoint(input, i, j)) {
                    // Si c'est un low point, calculer la taille du bassin
                    int basinSize = calculateBasinSize(input, i, j);
                    basinSizes.add(basinSize);
                }
            }
        }

        // Trier les tailles des bassins et retourner le produit des trois plus grands
        basinSizes.sort(Comparator.reverseOrder()); // Tri décroissant

        return basinSizes.get(0) * basinSizes.get(1) * basinSizes.get(2);
    }

    private int calculateBasinSize(Integer[][] input, int i, int j) {
        // Utilisation d'une approche DFS ou BFS pour calculer la taille du bassin
        boolean[][] visited = new boolean[input.length][input[0].length];
        // On commence le parcours à partir du point courant
        return dfs(input, i, j, visited);
    }

    // Algorithme de parcours en profondeur (DFS) pour explorer le bassin
    private int dfs(Integer[][] input, int i, int j, boolean[][] visited) {
        // Vérifier les limites et si le point a déjà été visité
        if (isOutside(input, i, j) || visited[i][j] || input[i][j] == 9) {
            return 0; // Si on sort des limites ou si c'est un 9 (non bassin)
        }

        visited[i][j] = true; // Marquer le point comme visité
        int size = 1; // Compter ce point

        // Explorer les voisins
        size += dfs(input, i - 1, j, visited); // Up
        size += dfs(input, i + 1, j, visited); // Down
        size += dfs(input, i, j - 1, visited); // Left
        size += dfs(input, i, j + 1, visited); // Right

        return size;
    }

    private static boolean isOutside(Integer[][] input, int i, int j) {
        return i < 0 || i >= input.length || j < 0 || j >= input[0].length;
    }

    private Integer[][] parseInput(String fileName) {
        return InputParsingUtils.inputToIntegerArray(fileName);
    }

}
