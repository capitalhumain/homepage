package opennlp;

import opennlp.tools.stemmer.PorterStemmer;
import opennlp.tools.stemmer.Stemmer;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.tokenize.Tokenizer;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PorterStemmingPlayground {
    @Test
    public void happy_running() {
        Stemmer stemmer = new PorterStemmer();
        String passage = "NSA agent walks into a bar. Bartender says, " +
                "'Hey, I have a new joke for you.' agent says, 'heard it.'.";
        Arrays.stream(passage.split(" ")).map(str -> stemmer.stem(str)).forEach(System.out::println);
    }

    @Test
    public void running_with_SimpleTokeninzer() {
        Tokenizer tokenizer = SimpleTokenizer.INSTANCE;
        Stemmer stemmer = new PorterStemmer();
        String passage = "NSA agent walks into a bar. Bartender says, " +
                "'Hey, I have a new joke for you.' agent says, 'heard it.'.";
        Arrays.stream(tokenizer.tokenize(passage)).map(str -> stemmer.stem(str)).forEach(System.out::println);
    }

    @Test
    public void running_wordCounter() {
        Tokenizer tokenizer = SimpleTokenizer.INSTANCE;
        Stemmer stemmer = new PorterStemmer();
        Map<String, Integer> wordCounts = new HashMap<>();
        String[] words = {"NSA", "agent", "joke"};
        Arrays.stream(words).forEach(str -> wordCounts.put(stemmer.stem(str).toString(), 0));
        String passage = "NSA agent walks into a bar. Bartender says, " +
                "'Hey, I have a new joke for you.' agent says, 'heard it.'.";
        Arrays.stream(tokenizer.tokenize(passage))
                .map(str -> stemmer.stem(str))
                .forEach(cs -> wordCounts.computeIfPresent(cs.toString(), (k, v) -> v+1));
        wordCounts.forEach((k, v) -> System.out.println(k + "=" + v));
    }
}
