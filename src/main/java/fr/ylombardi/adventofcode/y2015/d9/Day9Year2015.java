package fr.ylombardi.adventofcode.y2015.d9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day9Year2015 {

    int part1(String fileName) {
        List<Edge> edges = input(fileName);
        Set<String> cities = new HashSet<>();
        edges.forEach(edge -> {
            cities.add(edge.from());
            cities.add(edge.to());
        });
        Graph graph = new Graph(new ArrayList<>(cities), edges);

        return graph.shortestPath();
    }

    int part2(String fileName) {
        List<Edge> edges = input(fileName);
        Set<String> cities = new HashSet<>();
        edges.forEach(edge -> {
            cities.add(edge.from());
            cities.add(edge.to());
        });
        Graph graph = new Graph(new ArrayList<>(cities), edges);

        return graph.longestPath();
    }

    public List<Edge> input(String fileName) {
        String regex = "(?<origin>\\w+) to (?<destination>\\w+) = (?<distance>\\d+)";
        Pattern pattern = Pattern.compile(regex);

        ArrayList<Edge> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    Edge e = new Edge(matcher.group("origin"), matcher.group("destination"), Integer.parseInt(matcher.group("distance")));
                    lines.add(e);
                }
            }
        } catch (IOException ignore) {
        }
        return lines;
    }

}
