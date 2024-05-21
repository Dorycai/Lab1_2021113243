package org.example;

import java.io.*;
import java.util.Scanner;

import static org.example.Graph.createGraphFromFile;

public class GraphApp {
    private static Graph graph;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the Graph Text Processor!");
        System.out.println("Please enter the path to the text file:");
        String filePath = scanner.nextLine();
        graph = Graph.createGraphFromFile(filePath);

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Show Directed Graph");
            System.out.println("2. Query Bridge Words");
            System.out.println("3. Generate New Text");
            System.out.println("4. Calculate Shortest Path");
            System.out.println("5. Random Walk");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline left-over

            switch (choice) {
                case 1:
                    graph.showDirectedGraph();
                    break;
                case 2:
                    queryBridgeWords();
                    break;
                case 3:
                    generateNewText();
                    break;
                case 4:
                    calculateShortestPath();
                    break;
                case 5:
                    randomWalk();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private static void showDirectedGraph() {
        graph.printGraph();
    }


    private static void queryBridgeWords() {
        System.out.println("Enter first word:");
        String word1 = scanner.nextLine();
        System.out.println("Enter second word:");
        String word2 = scanner.nextLine();
        BridgeWord bridgeWord = new BridgeWord(graph);
        String result = bridgeWord.queryBridgeWords(word1, word2);
        System.out.println(result);
    }


    private static void generateNewText() {
        System.out.println("Enter a line of text:");
        String inputText = scanner.nextLine();
        BridgeWord bridgeWord = new BridgeWord(graph);
        String newText = bridgeWord.generateNewText(inputText);
        System.out.println("Generated text with bridge words: " + newText);
    }

    private static void calculateShortestPath() {
        System.out.println("Enter the starting word:");
        String start = scanner.nextLine();
        System.out.println("Enter the ending word:");
        String end = scanner.nextLine();
        ShortestPath shortestPath = new ShortestPath(graph);
        String result = shortestPath.findShortestPath(start, end);
        System.out.println(result);
    }


    private static void randomWalk() {
        RandomWalk randomWalk = new RandomWalk(graph);
        String walkResult = randomWalk.performRandomWalk();
        System.out.println("Random walk path: " + walkResult);
        //"src/main/resources/RandomResult"
        randomWalk.writeRandomWalkToFile(walkResult, "src/main/resources/RandomResult/randomWalkResult.txt");
        System.out.println("Random walk path has been written to 'randomWalkResult.txt'.");
    }





}
