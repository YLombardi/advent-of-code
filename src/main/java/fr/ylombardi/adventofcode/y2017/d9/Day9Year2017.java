package fr.ylombardi.adventofcode.y2017.d9;

import fr.ylombardi.adventofcode.utils.WTFException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day9Year2017 {

    public int part1(String fileName) {
        String input = input(fileName);

        findGarbageAndGroups(input);

        Group g = parseGroup(input);
        return computeScore(g, 0);
    }

    public static void findGarbageAndGroups(String input) {
        // Regex pour identifier les Garbages
        Pattern garbagePattern = Pattern.compile("<(.*?)(?<!\\!)>");
        Matcher garbageMatcher = garbagePattern.matcher(input);

        while (garbageMatcher.find()) {
            System.out.println("Garbage trouvé: " + garbageMatcher.group());
        }

        String inputWithoutGarbages = garbageMatcher.replaceAll("");

        // Regex pour identifier les Groups
        // TODO : Ca ne prend pas en compte les garbages
        Pattern groupPattern = Pattern.compile("\\{([^{}]*)\\}");
        Matcher groupMatcher = groupPattern.matcher(inputWithoutGarbages);

        while (groupMatcher.find()) {
            System.out.println("Group trouvé: " + groupMatcher.group());
        }
    }

    public int computeScore(Group g, int depth) {
        AtomicInteger score = new AtomicInteger();
        g.content().forEach(c -> {
            if (c instanceof Group) {
                score.addAndGet(computeScore((Group) c, depth + 1));
            }
        });
        return score.get();
    }

    private Group parseGroup(String input) {
        String content = input.substring(1, input.length() - 1);
        return new Group(parseContent(content));
    }

    private List<Garbage> parseContent(String content) {
        // Spliter par elements en prenant en compte les !
        // TODO : Il faudrait d'abord trouver les Garbages, puis trouver le groupe parent
        String[] elements = content.split(",");
        List<Garbage> groupContent = new ArrayList<>();
        for (String element : elements) {
            if (element.startsWith("<")) {
                // Handle garbage
                String garbageContent = element.substring(1, element.length() - 1);
                groupContent.add(new Garbage(garbageContent));
            } else {
                // Handle group
                groupContent.add(parseGroup(element));
            }
        }
        return groupContent;
    }

    private String input(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            return br.readLine();
        } catch (IOException ignore) {
            throw new WTFException();
        }
    }

}
