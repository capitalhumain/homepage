package core;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WordCounter {
    public Map<String, Integer> countWords(String passage, String... strings) {
        Map<String, Integer> wordCounts = new HashMap<>();

        Arrays.stream(strings).forEach(str -> wordCounts.put(str, 0));

        Arrays.stream(passage.split(" ")).forEach(word -> wordCounts.computeIfPresent(word, (key, val) -> val + 1));

        return wordCounts;
    }
}
