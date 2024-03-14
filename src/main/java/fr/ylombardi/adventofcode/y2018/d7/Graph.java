package fr.ylombardi.adventofcode.y2018.d7;

import java.util.*;

public class Graph {
    private final Map<String, Set<String>> adjVertices = new HashMap<>();

    void addVertex(String label) {
        adjVertices.putIfAbsent(label, new TreeSet<>());
    }

    void removeVertex(String label) {
        adjVertices.values().forEach(e -> e.remove(label));
        adjVertices.remove(label);
    }

    void addEdge(String label1, String label2) {
        adjVertices.get(label1).add(label2);
    }

    boolean vertexIsChild(String label) {
        var parent = adjVertices.values().stream().filter(childs -> childs.contains(label)).findAny();
        return parent.isPresent();
    }

    String vertexWithoutParent(Collection<String> labels) {
        var vertexWithoutParent = labels.stream().filter(l -> !vertexIsChild(l)).findFirst();
        return vertexWithoutParent.orElse(null);
    }

    String getFirstVertex() {
        // Trouver le vertex qui n'est un enfant d'aucun autre
        var firstVertex = adjVertices.keySet().stream().filter(l -> !vertexIsChild(l)).findAny();
        return firstVertex.orElse(null);
    }

    List<String> getNodesWithoutParent() {
        return adjVertices.keySet().stream().filter(l -> !vertexIsChild(l)).toList();
    }

    Set<String> getAdjVertices(String label) {
        return adjVertices.get(label);
    }

    boolean isEmpty() {
        return adjVertices.isEmpty();
    }

}
