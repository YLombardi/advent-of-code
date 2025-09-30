package fr.ylombardi.adventofcode.y2018.d10;

import fr.ylombardi.adventofcode.utils.WTFException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("java:S106")
public class Day10Year2018 {

    // position=<-41214, -10223> velocity=< 4,  1>
    private static final String REGEX = "position=<\\s*(?<x>-?\\d+),\\s*(?<y>-?\\d+)> velocity=<\\s*(?<vx>-?\\d+),\\s*(?<vy>-?\\d+)>";
    private final Pattern pattern = Pattern.compile(REGEX);

    /**
     * Affiche l'évolution des lucioles sur nbRounds tours à partir du fichier donné.
     * @param filePath chemin du fichier d'entrée
     * @param nbRounds nombre de tours à simuler
     */
    public void part1(String filePath, int nbRounds) {
        // Parser l'input sous forme de liste de lucioles
        List<Firefly> fireflies = parseInput(filePath);

        print(fireflies, 0);

        for (int round = 1; round < nbRounds; round++) {
            // Effectuer le movement des lucioles
            fireflies.forEach(Firefly::move);
            print(fireflies, round);
        }

    }

    /**
     * Affiche la grille des lucioles à un tour donné.
     * Utilise un StringBuilder pour optimiser l'affichage.
     * @param fireflies liste des lucioles
     * @param round numéro du tour
     */
    private static void print(List<Firefly> fireflies, int round) {
        // Determine les bornes de la grille
        int minX = fireflies.stream().map(Firefly::getX).min(Integer::compareTo).orElse(0);
        int maxX = fireflies.stream().map(Firefly::getX).max(Integer::compareTo).orElse(0);
        int minY = fireflies.stream().map(Firefly::getY).min(Integer::compareTo).orElse(0);
        int maxY = fireflies.stream().map(Firefly::getY).max(Integer::compareTo).orElse(0);
        int sizeX = maxX - minX + 1;
        int sizeY = maxY - minY + 1;

        // On n'affiche que si la zone est raisonnable
        if (sizeX > 100 || sizeY > 100) {
            return;
        }

        // Initialiser les cases avec un point
        String[][] grid = new String[sizeY][sizeX];
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                grid[y][x] = ".";
            }
        }

        // Afficher les positions des lucioles avec un #
        // Les positions sont décalées de minY et minX pour les ramener sur un référentiel de 0,0
        fireflies.forEach(firefly -> grid[firefly.getY() - minY][firefly.getX() - minX] = "#");

        // Afficher la grille
        System.out.println("After " + round + " seconds:");
        for (String[] line : grid) {
            StringBuilder sb = new StringBuilder();
            for (String s : line) {
                sb.append(s);
            }
            System.out.println(sb);
        }
    }

    /**
     * Parse le fichier d'entrée et retourne la liste des lucioles.
     * @param fileName chemin du fichier
     * @return liste des lucioles
     */
    public List<Firefly> parseInput(String fileName) {
        List<Firefly> fireflies = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.lines().forEach(line -> {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    String x = matcher.group("x");
                    String y = matcher.group("y");
                    String vx = matcher.group("vx");
                    String vy = matcher.group("vy");
                    fireflies.add(new Firefly(Integer.parseInt(x), Integer.parseInt(y), Integer.parseInt(vx), Integer.parseInt(vy)));
                } else {
                    System.err.println("Ligne ignorée (format invalide) : " + line);
                }
            });
        } catch (IOException ignore) {
            throw new WTFException();
        }
        return fireflies;
    }
}
