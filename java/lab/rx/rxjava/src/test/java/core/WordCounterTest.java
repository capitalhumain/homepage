package core;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertNotNull;

public class WordCounterTest {
    @Test
    public void happy() {
        String passage = "NSA agent walks into a bar. Bartender says, " +
                "'Hey, I have a new joke for you.' agent says, 'heard it.'.";
        Map<String, Integer> count = (new WordCounter()).countWords(passage, "NSA", "agent", "joke");
        assertNotNull(count);
        count.forEach((k, v) -> System.out.println(k + "=" + v));
    }
}
