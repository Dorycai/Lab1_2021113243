package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class BridgeWord {
    private Graph graph;

    private Random random = new Random();

    public BridgeWord(Graph graph) {
        this.graph = graph;
    }

//    public String queryBridgeWords(String word1, String word2) {
//        if (!graph.getAdjacencyList().containsKey(word1) || !graph.getAdjacencyList().containsKey(word2)) {
//            return "No " + word1 + " or " + word2 + " in the graph!";
//        }
//
//        List<String> bridgeWords = new ArrayList<>();
//        Map<String, Integer> edgesFromWord1 = graph.getAdjacencyList().get(word1);
//        for (String candidate : edgesFromWord1.keySet()) {
//            if (graph.getAdjacencyList().containsKey(candidate) && graph.getAdjacencyList().get(candidate).containsKey(word2)) {
//                bridgeWords.add(candidate);
//            }
//        }
//
//        if (bridgeWords.isEmpty()) {
//            return "No bridge words from \"" + word1 + "\" to \"" + word2 + "\"!";
//        } else {
//            return "The bridge words from \"" + word1 + "\" to \"" + word2 + "\" are: " + String.join(", ", bridgeWords);
//        }
//    }
public String queryBridgeWords(String word1, String word2) {
    if (!graph.getAdjacencyList().containsKey(word1) || !graph.getAdjacencyList().containsKey(word2)) {
        return "No " + word1 + " or " + word2 + " in the graph!";
    }

    List<String> bridgeWords = new ArrayList<>();
    Map<String, Integer> edgesFromWord1 = graph.getAdjacencyList().get(word1);
    for (String candidate : edgesFromWord1.keySet()) {
        if (graph.getAdjacencyList().containsKey(candidate) && graph.getAdjacencyList().get(candidate).containsKey(word2)) {
            bridgeWords.add(candidate);
        }
    }

    if (bridgeWords.isEmpty()) {
        return "No bridge words from \"" + word1 + "\" to \"" + word2 + "\"!";
    } else {
        String verb = bridgeWords.size() == 1 ? "is" : "are"; // Use 'is' for single and 'are' for multiple
        return "The bridge word" + (bridgeWords.size() > 1 ? "s" : "") + " from \"" + word1 + "\" to \"" + word2 + "\" " + verb + ": " + String.join(", ", bridgeWords);
    }
}

    public String generateNewText(String inputText) {
        String[] words = inputText.split("\\s+");
        if (words.length < 2) return inputText; // If there's less than two words, return the original text

        StringBuilder newText = new StringBuilder();
        newText.append(words[0]); // Start by appending the first word directly

        for (int i = 1; i < words.length; i++) {
            String previousWord = words[i - 1];

            String currentWord = words[i];
            System.out.println("算"+ previousWord +"和" + currentWord);
            String bridgeWord = findRandomBridgeWord(previousWord, currentWord);
            System.out.println("得到" + bridgeWord);

            // Only append a bridge word if one exists
            if (!bridgeWord.isEmpty()) {
                newText.append(" ").append(bridgeWord);
            }
            newText.append(" ").append(currentWord); // Append the current word
        }

        return newText.toString();
    }


    private String findRandomBridgeWord(String word1, String word2) {
        if (!graph.getAdjacencyList().containsKey(word1)) {
            return "";
        }

        List<String> bridgeWords = new ArrayList<>();
        Map<String, Integer> edgesFromWord1 = graph.getAdjacencyList().get(word1);
        for (String candidate : edgesFromWord1.keySet()) {
            // 检查 candidate 是否有出边指向 word2
            if (graph.getAdjacencyList().containsKey(candidate) && graph.getAdjacencyList().get(candidate).containsKey(word2)) {
                bridgeWords.add(candidate);
            }
        }

        if (bridgeWords.isEmpty()) {
            return "";
        }
        return bridgeWords.get(random.nextInt(bridgeWords.size()));
    }

}
