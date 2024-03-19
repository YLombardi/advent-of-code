package fr.ylombardi.adventofcode.y2018.d7;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7Year2018 {

    public String part1(String filePath) {
        // Lire l'input
        Graph graph = initGraph(filePath);

        // Trouver les noeuds qui ne sont un enfant d'aucun autre
        var firstNodes = graph.getNodesWithoutParent();
        System.out.println(firstNodes);

        // Parcourir le graph par ordre alphabetique à partir de ces premiers noeuds
        var visited = graphTraversal(graph, firstNodes);

        return String.join("", visited);
    }

    int part2(String filePath, int nbWorkers, int timeOffset) {
        Graph graph = initGraph(filePath);

        // Trouver les noeuds qui ne sont un enfant d'aucun autre
        var firstNodes = graph.getNodesWithoutParent();

        // Tant que le graph n'est pas vide (donc pas entièrement traité)
        Workers workers = new Workers(nbWorkers);
        int time = 0;
        TreeSet<String> nextNodes = new TreeSet<>(firstNodes);
        while (!graph.isEmpty()) {
            // Tant qu'il reste des workers libres
            while (workers.hasFreeWorker() && !nextNodes.isEmpty()) {
                // Trouver le noeud suivant qui n'a plus de parent à traiter
                String vertex = graph.vertexWithoutParent(nextNodes);
                if (vertex != null) {
                    // Assigner node
                    workers.assignTask(vertex, timeOffset);
                    // Supprimer du next
                    nextNodes.remove(vertex);
                } else {
                    break;
                }
            }

            // Les workers travaillent
            workers.work();
            System.out.println(time + " " + workers.workOn());
            time++;

            // Lorsqu'un worker a fini
            workers.nodeDone().forEach(nodeDone -> {
                // On regarde la liste des noeuds suivants
                nextNodes.addAll(graph.getAdjVertices(nodeDone));
                // On supprime du graph le noeud déjà visité
                graph.removeVertex(nodeDone);
            });

        }

        return time;
    }

    private Graph initGraph(String filePath) {
        // Lire l'input
        var input = getValues(filePath);

        // Parser input en objet noeud => suivant
        var edges = input.stream().map(this::parseString).toList();

        // Construire le graph
        // https://www.baeldung.com/java-graphs
        Graph graph = new Graph();
        edges.forEach(e -> {
            graph.addVertex(e.node());
            graph.addVertex(e.child());
            graph.addEdge(e.node(), e.child());
        });
        return graph;
    }

    List<String> graphTraversal(Graph graph, List<String> roots) {
        List<String> visitedNodes = new ArrayList<>();
        List<String> nextNodes = new ArrayList<>(roots);
        while (!nextNodes.isEmpty()) {
            nextNodes.sort(Comparator.naturalOrder());
            // On regarde le suivant qui n'a plus de parent dans le graph
            String vertex = graph.vertexWithoutParent(nextNodes);
            // On l'enlève de la liste des noeuds à visiter
            nextNodes.remove(vertex);
            if (!visitedNodes.contains(vertex)) {
                // On regarde la liste des noeuds suivants
                nextNodes.addAll(graph.getAdjVertices(vertex));
                visitedNodes.add(vertex);
                // On supprime du graph le noeud déjà visité
                graph.removeVertex(vertex);
            }
        }
        return visitedNodes;
    }

    public Edge parseString(String s) {
        String regex = "Step ([A-Z]) must be finished before step ([A-Z]) can begin.";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        if (matcher.find()) {
            return new Edge(matcher.group(1), matcher.group(2));
        }
        return null;
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
