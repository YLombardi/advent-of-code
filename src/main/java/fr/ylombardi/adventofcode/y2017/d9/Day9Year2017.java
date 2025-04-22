package fr.ylombardi.adventofcode.y2017.d9;

import fr.ylombardi.adventofcode.utils.WTFException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day9Year2017 {

    public static final String GARBAGE_REGEX = "<([^>!]|!.)*?>";

    public int part1(String fileName) {
        String input = input(fileName);

        // Regex pour identifier les Garbages
        Pattern garbagePattern = Pattern.compile(GARBAGE_REGEX);
        Matcher garbageMatcher = garbagePattern.matcher(input);

        String inputWithoutGarbages = garbageMatcher.replaceAll("");

        return computeScore(inputWithoutGarbages);
    }

    /**
     * On compte les groupes en faisant +1 pour chaque { et -1 pour chaque }
     * @param groupsInput liste des groupes
     * @return le score
     */
    private int computeScore(String groupsInput) {
        int count = 0;
        int depth = 0;
        for (char c : groupsInput.toCharArray()) {
            if (c == '{') {
                depth++;
                count+= depth;
            } else if (c == '}') {
                depth--;
            }
        }
        return count;
    }

    public int part2(String fileName) {
        String input = input(fileName);
        // Regex pour identifier les Garbages
        Pattern garbagePattern = Pattern.compile(GARBAGE_REGEX);
        Matcher garbageMatcher = garbagePattern.matcher(input);

        int count = 0;
        while (garbageMatcher.find()) {
            String garbage = garbageMatcher.group();
            System.out.println("Garbage trouvé: " + garbage);
            // Supprime les caractères précédés par un !
            garbage = garbage.replaceAll("!.", "");
            count += garbage.length() - 2; // On enlève les < et >
        }

        return count;
    }

    private String input(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            return br.readLine();
        } catch (IOException ignore) {
            throw new WTFException();
        }
    }

}
