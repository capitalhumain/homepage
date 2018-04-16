package analyzer;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.fail;

public class PlusSignAnalyzerTest {
    @Test
    public void testHappy() {
        final String text = "This+is++some+text";

        PlusSignAnalyzer analyzer = new PlusSignAnalyzer();
        TokenStream tokenStream = analyzer.tokenStream("testField", new StringReader(text));
        try {
            int position = 0;
            while (tokenStream.incrementToken()) {
                System.out.println(tokenStream.getAttribute(CharTermAttribute.class).toString());
                position += tokenStream.getAttribute(PositionIncrementAttribute.class).getPositionIncrement();
                System.out.println(String.format("Position: %d", position));
            }
        } catch(IOException e) {
            fail(e.getMessage());
        } finally {
            try {
                tokenStream.close();
            } catch(IOException e) {}
        }
    }
}
