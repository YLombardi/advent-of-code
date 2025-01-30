package fr.ylombardi.adventofcode.y2024.d5;

import fr.ylombardi.adventofcode.utils.InputParsingUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Day5Year2024 {

    public int part1(String fileName) {
        Input i = input(fileName);

        AtomicInteger sum = new AtomicInteger();
        i.updates().forEach(update -> {
            // Pour chaque update, on vérifie que TOUTES les règles sont correctement appliqué
            if (i.rules().stream().allMatch(update::apply)) {
                // Si oui, on récupère le nombre du milieu de la règle
                sum.addAndGet(update.middlePage());
            }
        });

        return sum.get();
    }

    public int part2(String fileName) {
        Input i = input(fileName);

        AtomicInteger sum = new AtomicInteger();
        i.updates().forEach(update -> {
            int middle = 0;
            // Tant qu'une règle ne s'applique pas, on tri toutes les pages
            while (i.rules().stream().anyMatch(update::notApply)) {
                i.rules().forEach(update::sort);
                middle = update.middlePage();
            }
            // A la fin des tri, s'il y en a eu, on récupère le nombre du milieu de l'update
            sum.getAndAdd(middle);
        });

        return sum.get();
    }

    public Input input(String fileName) {
        List<String> inputLines = InputParsingUtils.inputToListOfStrings(fileName);
        List<Rule> rules = new ArrayList<>();
        List<Update> updates = new ArrayList<>();
        inputLines.forEach(line -> {
            if (line.contains("|")) {
                // On est sur une règle
                String[] split = line.split("\\|");
                rules.add(new Rule(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
            } else if (line.contains(",")) {
                // On est sur une update
                String[] split = line.split(",");
                List<Integer> pages = Arrays.stream(split).map(Integer::parseInt).collect(Collectors.toList());
                updates.add(new Update(pages));
            }
        });
        return new Input(rules, updates);
    }
}
