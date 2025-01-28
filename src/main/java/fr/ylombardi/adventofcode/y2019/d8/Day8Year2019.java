package fr.ylombardi.adventofcode.y2019.d8;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class Day8Year2019 {

    public long exo(String filePath, int cols, int lines) {
        // Récupération de la chaine d'input
        String input = getValues(filePath);
        List<Layer> layers = parseInput(cols, lines, input);

        // Cherche la couche qui a le moins de 0
        Layer layerWithLessO = layers.stream().min(Comparator.comparingLong(Layer::getNumberOf0)).orElseThrow();

        // Partie 1 : On multiplie le nombre de 1 et le nombre de 2 dans cette couche
        long resultPart1 = layerWithLessO.count('1') * layerWithLessO.count('2');

        // Superposition des couches
        List<String> renderedLayers = getRenderedLayers(cols, lines, layers);

        // Affichage du résultat
        printResult(cols, lines, renderedLayers);

        return resultPart1;
    }

    private static void printResult(int cols, int lines, List<String> renderedLayers) {
        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(renderedLayers.get(i * cols + j));
            }
            System.out.println();
        }
    }

    private static List<String> getRenderedLayers(int cols, int lines, List<Layer> layers) {
        List<String> renderedLayers = new ArrayList<>();
        IntStream.range(0, cols * lines).boxed().forEach(i -> {
            renderedLayers.add(null);
            layers.forEach(t -> {
                if (renderedLayers.get(i) == null) {
                    renderedLayers.set(i, t.charAt(i));
                }
            });
        });
        return renderedLayers;
    }

    private static List<Layer> parseInput(int cols, int lines, String input) {
        List<Layer> results = new ArrayList<>();
        int length = input.length();
        // Je découpe l'input en plusieurs couches dont la longueur est le nombre de colonnes * le nombre de lignes
        for (int i = 0; i < length; i += cols * lines) {
            String substring = input.substring(i, Math.min(length, i + cols * lines));
            results.add(new Layer(substring));
        }
        return results;
    }

    public static String getValues(String filePath) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) {
            return reader.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return "WTF";
    }

}