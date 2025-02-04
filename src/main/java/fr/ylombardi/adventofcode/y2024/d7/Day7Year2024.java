package fr.ylombardi.adventofcode.y2024.d7;

import fr.ylombardi.adventofcode.utils.InputParsingUtils;
import fr.ylombardi.adventofcode.utils.WTFException;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day7Year2024 {

    public BigInteger part1(String fileName, boolean includeConcatenation) {
        BigInteger total = BigInteger.ZERO;

        // Lecture de l'entrée
        List<Input> input = readInput(fileName);

        for (Input i : input) {
            // Pour chaque équation, générez toutes les combinaisons possibles d'opérateurs (+, * et ||) et d'opérandes.
            // Évaluez chaque combinaison pour voir si elle produit le test value.
            List<BigInteger> results = new ArrayList<>();
            generateCombinations(results, i.operands(), 1, i.operands().get(0), includeConcatenation);
            // Ajoutez les totaux des équations qui peuvent être rendues vraies.
            if (results.contains(i.total())) {
                total = total.add(i.total());
            }
        }

        return total;
    }


    private void generateCombinations(List<BigInteger> alLResults, List<BigInteger> operands, int index, BigInteger currentResult, boolean includeConcatenation) {
        if (index == operands.size()) {
            alLResults.add(currentResult);
            return;
        }

        // Addition
        generateCombinations(alLResults, operands, index + 1, currentResult.add(operands.get(index)), includeConcatenation);

        // Multiplication
        generateCombinations(alLResults, operands, index + 1, currentResult.multiply(operands.get(index)), includeConcatenation);

        // Concaténation
        if (includeConcatenation) {
            generateCombinations(alLResults, operands, index + 1, concatenate(currentResult, operands.get(index)), includeConcatenation);
        }
    }

    // Combine 2 BigIntegers
    // Example : 15 + 26 = 1526
    private BigInteger concatenate(BigInteger a, BigInteger b) {
        return new BigInteger(a.toString() + b.toString());
    }

    public List<Input> readInput(String fileName) {
        List<String> strings = InputParsingUtils.inputToListOfStrings(fileName);
        return strings.stream().map(s -> {
            // Chaque ligne est de la forme "total: value1 value2 value3"
            String[] parts = s.split(":");
            if (parts.length == 2) {
                // Récupération du total
                BigInteger total = new BigInteger(parts[0].trim());
                // Récupération des valeurs
                List<BigInteger> values = Arrays.stream(parts[1].trim().split(" "))
                        .map(i -> BigInteger.valueOf(Integer.parseInt(i)))
                        .toList();
                return new Input(total, values);
            }
            throw new WTFException();
        }).toList();
    }
}
