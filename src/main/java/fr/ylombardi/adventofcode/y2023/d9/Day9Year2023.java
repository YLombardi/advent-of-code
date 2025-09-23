package fr.ylombardi.adventofcode.y2023.d9;

import fr.ylombardi.adventofcode.utils.InputParsingUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day9Year2023 {

    int part1(String fileName) {
        List<List<Integer>> lines = parseInput(fileName);

        return lines.stream().map(this::findNextElement)
                .reduce(0, Integer::sum);
    }

    int part2(String fileName) {
        List<List<Integer>> lines = parseInput(fileName);

        Stream<Integer> previousIntegers = lines.stream().map(this::findPreviousElement);

        // reduce the stream to a single value
        return previousIntegers.reduce(0, Integer::sum);
    }

    private Integer findNextElement(List<Integer> line) {
        // Calculer la liste des différences entre les éléments consécutifs
        List<Integer> differences = new ArrayList<>();
        for (int i = 1; i < line.size(); i++) {
            differences.add(line.get(i) - line.get(i - 1));
        }

        // Si la liste ne contient que des 0, la valeur suivante est la dernière valeur de la ligne
        if (differences.stream().allMatch(diff -> diff == 0)) {
            return line.get(line.size() - 1);
        }

        // Sinon, on appelle la méthode de façon récursive pour trouver la différence entre la valeur suivante
        // et la dernière valeur de la ligne
        Integer nextDifference = findNextElement(differences);
        return line.get(line.size() - 1) + nextDifference;
    }

    private Integer findPreviousElement(List<Integer> line) {
        // Calculer la liste des différences entre les éléments consécutifs
        List<Integer> differences = new ArrayList<>();
        for (int i = 1; i < line.size(); i++) {
            differences.add(line.get(i) - line.get(i - 1));
        }

        // Si la liste ne contient que des 0, la valeur précédente est la première valeur de la ligne
        if (differences.stream().allMatch(diff -> diff == 0)) {
            return line.get(0);
        }

        // Sinon, on appelle la méthode de façon récursive pour trouver la différence entre la valeur précédente
        // et la première valeur de la ligne
        Integer previousDifference = findPreviousElement(differences);
        return line.get(0) - previousDifference;
    }

    private List<List<Integer>> parseInput(String fileName) {
        return InputParsingUtils.inputToListOfListOfIntegers(fileName);
    }

}
