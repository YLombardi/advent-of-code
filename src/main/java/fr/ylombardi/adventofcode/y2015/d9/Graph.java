package fr.ylombardi.adventofcode.y2015.d9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public record Graph(List<String> vertices, List<Edge> edges) {

    public int shortestPath() {
        // Fill the distance matrix
        int[][] distanceMatrix = getDistanceMatrix();
        // Generate all permutations of cities
        List<List<Integer>> permutations = generatePermutations();
        // Calculate the shortest distance
        return getDistance(permutations, distanceMatrix, false);
    }

    public int longestPath() {
        // Fill the distance matrix
        int[][] distanceMatrix = getDistanceMatrix();
        // Generate all permutations of cities
        List<List<Integer>> permutations = generatePermutations();
        // Calculate the longest distance
        return getDistance(permutations, distanceMatrix, true);
    }

    /**
     * Fill the distance matrix
     * @return Distance matrix
     */
    private int[][] getDistanceMatrix() {
        int n = vertices.size();
        int[][] distanceMatrix = new int[n][n];
        for (Edge edge : edges) {
            int i = vertices.indexOf(edge.from());
            int j = vertices.indexOf(edge.to());
            distanceMatrix[i][j] = edge.distance();
            distanceMatrix[j][i] = edge.distance();
        }
        return distanceMatrix;
    }

    private List<List<Integer>> generatePermutations() {
        List<List<Integer>> permutations = new ArrayList<>();
        List<Integer> initial = new ArrayList<>();
        for (int i = 0; i < vertices.size(); i++) {
            initial.add(i);
        }
        permute(initial, 0, permutations);
        return permutations;
    }

    private void permute(List<Integer> arr, int k, List<List<Integer>> permutations) {
        for (int i = k; i < arr.size(); i++) {
            Collections.swap(arr, i, k);
            permute(arr, k + 1, permutations);
            Collections.swap(arr, k, i);
        }
        if (k == arr.size() - 1) {
            permutations.add(new ArrayList<>(arr));
        }
    }

    /**
     * Get the shortest (reverse = false) or longest distance between cities (reverse = true)
     * @param permutations List of permutations
     * @param distanceMatrix Distance matrix
     * @param reverse If true, get the longest distance
     * @return The shortest or longest distance between cities
     */
    private int getDistance(List<List<Integer>> permutations, int[][] distanceMatrix, boolean reverse) {
        int distance = reverse ? 0 : Integer.MAX_VALUE;

        // Calculate the distance for each permutation
        for (List<Integer> perm : permutations) {
            int currentDistance = 0;
            for (int i = 0; i < vertices.size() - 1; i++) {
                currentDistance += distanceMatrix[perm.get(i)][perm.get(i + 1)];
            }
            //currentDistance += distanceMatrix[perm.get(n - 1)][perm.get(0)]; // Return to the starting city
            if (reverse) {
                distance = Math.max(distance, currentDistance);
            } else {
                distance = Math.min(distance, currentDistance);
            }
        }
        return distance;
    }
}
