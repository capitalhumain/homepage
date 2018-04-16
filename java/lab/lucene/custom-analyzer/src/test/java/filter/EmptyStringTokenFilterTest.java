package filter;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.junit.Test;
import tokenizer.PlusSignTokenizer;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import static org.junit.Assert.fail;

public class EmptyStringTokenFilterTest {
    @Test
    public void testHappy() throws IOException {
        String text = "This+is++some+text";
        Tokenizer tokenizer = new PlusSignTokenizer(new StringReader(text));
        TokenFilter filter = new EmptyStringTokenFilter(tokenizer);
        try {
            while (filter.incrementToken()) {
                System.out.println(filter.getAttribute(CharTermAttribute.class).toString());
            }
        } catch(IOException e) {
            fail("Exception occurred");
        } finally {
            filter.close();
            tokenizer.close();
        }
    }

    @Test
    public void testWithAnalyzer() {
        String text = "This+is++some+text";

        Analyzer analyzer = new Analyzer() {
            @Override
            protected TokenStreamComponents createComponents(String fieldName) {
                Tokenizer tokenizer = new PlusSignTokenizer();
                TokenFilter filter = new EmptyStringTokenFilter(tokenizer);
                return new TokenStreamComponents(tokenizer, filter);
            }
        };

        TokenStream tokenStream = analyzer.tokenStream("testField", new StringReader(text));
        try {
            while (tokenStream.incrementToken()) {
                System.out.println(tokenStream.getAttribute(CharTermAttribute.class).toString());
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
