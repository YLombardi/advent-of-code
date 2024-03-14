package fr.ylombardi.adventofcode.y2018.d7;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class GraphTest {

    @Test
    void vertexIsChild() {
        Graph graph = new Graph();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");

        Assertions.assertTrue(graph.vertexIsChild("B"));
        Assertions.assertFalse(graph.vertexIsChild("A"));
    }

    @Test
    void vertexWithoutParent() {
        Graph graph = new Graph();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");

        Assertions.assertEquals("A", graph.vertexWithoutParent(List.of("A", "B")));
    }

    @Test
    void getFirstVertex() {
        Graph graph = new Graph();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("D", "C");

        Assertions.assertEquals("A", graph.getFirstVertex());
    }

    @Test
    void getNodesWithoutParent() {
        Graph graph = new Graph();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("D", "C");

        Assertions.assertLinesMatch(List.of("A", "D"), graph.getNodesWithoutParent());
    }
}