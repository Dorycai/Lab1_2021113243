package org.example;

import java.util.*;

public class ShortestPath {
    private Graph graph;

    public ShortestPath(Graph graph) {
        this.graph = graph;
    }

    public String findShortestPath(String start, String end) {
        if (!graph.getAdjacencyList().containsKey(start) || !graph.getAdjacencyList().containsKey(end)) {
            return "One or both words are not in the graph!";
        }

        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> predecessors = new HashMap<>();
        PriorityQueue<Map.Entry<String, Integer>> queue = new PriorityQueue<>(Map.Entry.comparingByValue());

        for (String vertex : graph.getAdjacencyList().keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
            predecessors.put(vertex, null);
        }

        distances.put(start, 0);
        queue.add(new AbstractMap.SimpleEntry<>(start, 0));

        while (!queue.isEmpty()) {
            String current = queue.poll().getKey();
            if (current.equals(end)) {
                break;
            }

            int currentDistance = distances.get(current);
            if (graph.getAdjacencyList().containsKey(current)) {
                for (Map.Entry<String, Integer> neighbor : graph.getAdjacencyList().get(current).entrySet()) {
                    String next = neighbor.getKey();
                    int weight = neighbor.getValue();
                    int distanceThroughCurrent = currentDistance + weight;
                    if (distanceThroughCurrent < distances.getOrDefault(next, Integer.MAX_VALUE)) {
                        distances.put(next, distanceThroughCurrent);
                        predecessors.put(next, current);
                        queue.add(new AbstractMap.SimpleEntry<>(next, distanceThroughCurrent));
                    }
                }
            }
        }

        if (distances.get(end) == Integer.MAX_VALUE) {
            return "No path exists between " + start + " and " + end;
        }

        // Reconstruct the path
        LinkedList<String> path = new LinkedList<>();
        for (String at = end; at != null; at = predecessors.get(at)) {
            path.addFirst(at);
        }

        return "Shortest path from " + start + " to " + end + " is: " + String.join(" → ", path) +
                " with total distance: " + distances.get(end);
    }

}
