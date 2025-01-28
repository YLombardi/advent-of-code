package fr.ylombardi.adventofcode.y2017.d8;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day8Year2017 {

    public int part1(String filePath) {
        // Récupérer la liste des instructions
        List<Instruction> instructions = getValues(filePath);
        // Extraire la liste des variables
        Map<String, Integer> state = instructions.stream()
                .collect(Collectors.toMap(Instruction::variable, x->0, (a, b)->a));
        // Executer les instructions
        instructions.forEach(i -> i.compute(state));
        // Afficher le resultat
        return state.values().stream().max(Integer::compareTo).orElse(0);
    }

    int part2(String filePath) {
        // Récupérer la liste des instructions
        List<Instruction> instructions = getValues(filePath);
        // Extraire la liste des variables
        Map<String, Integer> state = instructions.stream()
                .collect(Collectors.toMap(Instruction::variable, x->0, (a, b)->a));
        // Executer les instructions
        List<Integer> allMax = instructions.stream().map(i -> {
            i.compute(state);
            return state.values().stream().max(Integer::compareTo).orElse(0);
        }).toList();
        // Afficher le resultat
        return allMax.stream().max(Integer::compareTo).orElse(0);
    }

    @SuppressWarnings("java:S108")
    public static List<Instruction> getValues(String filePath) {
        String regex = "(?<variable>\\w+) (?<operation>\\w+) (?<value>-?\\d+) if (?<variable2>\\w+) (?<operation2>.+) (?<value2>-?\\d+)";
        Pattern pattern = Pattern.compile(regex);

        ArrayList<Instruction> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    Instruction instruction = getInstruction(matcher);
                    // Exemple de ligne b inc 5 if a > 1
                    // ligne = variable instruction valeur condition
                    // valeur positive ou negative
                    // condition = variable operateur valeur
                    lines.add(instruction);
                }
            }
        } catch (IOException ignore) {
        }
        return lines;
    }

    private static Instruction getInstruction(Matcher matcher) {
        String variable = matcher.group("variable");
        String operation = matcher.group("operation");
        Integer value = Integer.parseInt(matcher.group("value"));
        String variable2 = matcher.group("variable2");
        String operation2 = matcher.group("operation2");
        Integer value2 = Integer.parseInt(matcher.group("value2"));
        return new Instruction(variable, operation, value, variable2, operation2, value2);
    }

}
