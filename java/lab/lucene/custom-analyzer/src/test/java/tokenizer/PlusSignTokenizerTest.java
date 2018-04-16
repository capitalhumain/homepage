package tokenizer;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.*;

public class PlusSignTokenizerTest {
    @Test
    public void testHappy() throws IOException {
        Tokenizer tokenizer = new PlusSignTokenizer(new StringReader("This+is++some+text"));
        while(tokenizer.incrementToken()) {
            System.out.println(tokenizer.getAttribute(CharTermAttribute.class).toString());
        }
        tokenizer.close();
    }

    @Test
    public void testHappy2() throws IOException {
        String text = "This";
        Tokenizer tokenizer = new PlusSignTokenizer(new StringReader(text));
        assertTrue(tokenizer.incrementToken());
        assertEquals(text, tokenizer.getAttribute(CharTermAttribute.class).toString());
        assertFalse(tokenizer.incrementToken());
        tokenizer.close();
    }
}
