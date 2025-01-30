package fr.ylombardi.adventofcode.y2023.d7;

import fr.ylombardi.adventofcode.utils.InputParsingUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7Year2023 {

    int part1(String fileName) {
        List<Hand> hands = input(fileName);
        hands.sort(new HandComparator());
        int score = 0;
        for (int i = 0; i < hands.size(); i++) {
            score += hands.get(i).bid() * (hands.size()-i);
        }
        return score;
    }

    int part2(String fileName) {
        List<Hand> hands = input(fileName);
        //hands.sort(new HandComparator());
        return 0;
    }

    public List<Hand> input(String fileName) {
        String regex = "(\\w+) (\\d+)";
        Pattern pattern = Pattern.compile(regex);
        List<Hand> hands = new ArrayList<>();

        InputParsingUtils.inputToListOfStrings(fileName)
                .stream()
                .map(pattern::matcher)
                .filter(Matcher::find)
                .forEach(matcher -> {
                    hands.add(new Hand(matcher.group(1), Integer.parseInt(matcher.group(2))));
                });

        return hands;
    }

}
