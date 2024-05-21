package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class RandomWalk {
    private Graph graph;
    private Random random = new Random();

    public RandomWalk(Graph graph) {
        this.graph = graph;
    }

    public String performRandomWalk() {
        List<String> visitedEdges = new ArrayList<>();
        Set<String> visitedVertices = new HashSet<>();

        // Randomly select a starting node
        List<String> vertices = new ArrayList<>(graph.getAdjacencyList().keySet());
        if (vertices.isEmpty()) return "The graph is empty.";

        String current = vertices.get(random.nextInt(vertices.size()));
        visitedVertices.add(current);

        StringBuilder path = new StringBuilder(current);

        while (true) {
            if (!graph.getAdjacencyList().containsKey(current) || graph.getAdjacencyList().get(current).isEmpty()) {
                break; // Stop if there are no outgoing edges
            }

            List<String> possibleNextNodes = new ArrayList<>(graph.getAdjacencyList().get(current).keySet());
            String next = possibleNextNodes.get(random.nextInt(possibleNextNodes.size()));
            String edge = current + " → " + next;

            if (visitedEdges.contains(edge)) {
                path.append(" (repeated edge)");
                break; // Stop if the edge is already visited
            }

            visitedEdges.add(edge);
            visitedVertices.add(next);
            path.append(" → ").append(next);
            current = next; // Move to the next node
        }

        return path.toString();
    }

    public void writeRandomWalkToFile(String path, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filename)))) {
            writer.write(path);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
