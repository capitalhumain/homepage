package opennlp;

import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.tokenize.Tokenizer;
import org.junit.Test;

import java.util.Arrays;

public class TokenizerPlayground {
    @Test
    public void happy_SimpleTokenizer() {
        Tokenizer tokenizer = SimpleTokenizer.INSTANCE;
        String passage = "NSA agent walks into a bar. Bartender says, " +
                "'Hey, I have a new joke for you.' agent says, 'heard it.'.";
        Arrays.stream(tokenizer.tokenize(passage)).forEach(System.out::println);
    }
}
