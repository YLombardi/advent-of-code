package fr.ylombardi.adventofcode.y2020.d7;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7Year2020 {

    public void exo(String filePath) {
        var source = getValues(filePath);
        var allBags = source.stream().map(this::parseString).toList();
        //allBags.forEach(System.out::println);

        String contenu = "shiny gold";

        Set<String> result = new TreeSet<>();
        result.add(contenu);

        AtomicBoolean bagFound = new AtomicBoolean(true);
        while (bagFound.get()) {
            Set<String> resultIntermediaire = new TreeSet<>();
            bagFound.set(false);
            // Trouver les sacs qui contiennent le contenu
            result.forEach(bag -> allBags.forEach(e -> {
                if (!result.contains(e.name) && e.contains(bag)) {
                    //System.out.println(e.name + " bags contain " + bag);
                    resultIntermediaire.add(e.name);
                    bagFound.set(true);
                }
            }));
            result.addAll(resultIntermediaire);
        }

        result.remove(contenu);
        System.out.println(result.size());

        /***
         * Partie 2
         ***/

        Map<String, Integer> totalBags = new HashMap<>();
        Map<String, Integer> nextBags = new HashMap<>();
        nextBags.put("shiny gold", 1);

        while (!nextBags.isEmpty()) {
            Map<String, Integer> nextLoopBags = new HashMap<>();
            nextBags.forEach((key, value) -> {
                var nextBag = allBags.stream().filter(x -> key.equals(x.name)).findFirst().get();
                nextBag.content.forEach(x -> nextLoopBags.compute(x.name(), (name, quantity) -> quantity == null ? x.quantity() * value : quantity + x.quantity() * value));
            });
            // Mettre les nextBags dans TotalBags
            System.out.println(nextBags);
            nextBags.forEach((key, value) -> totalBags.compute(key, (k, v) -> v == null ? value : v + value));
            // Remplacer nextBags par nextLoopBags pour l'étape suivante
            nextBags = nextLoopBags;
        }

        // -1 car on ne compte pas le shiny gold
        System.out.println(totalBags.values().stream().reduce(-1, Integer::sum));

    }

    public Container parseString(String s) {
        String regex = "(\\S+\\s+\\S+) bags contain ([\\s\\S]+).";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);

        Container container = new Container();
        if (matcher.find()) {
            container.setName(matcher.group(1));

            String sequel = matcher.group(2);
            Arrays.stream(sequel.split(",")).forEach(x -> {
                String regexSuite = "(\\d) (\\S+\\s+\\S+) bags*";
                Pattern p = Pattern.compile(regexSuite);
                Matcher m = p.matcher(x);

                if (m.find()) {
                    container.addContent(Integer.parseInt(m.group(1)), m.group(2));
                }
            });

        }
        return container;
    }

    public static ArrayList<String> getValues(String filePath) {
        String strLine;
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) {
            while ((strLine = reader.readLine()) != null) {
                lines.add(strLine);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return lines;
    }

}