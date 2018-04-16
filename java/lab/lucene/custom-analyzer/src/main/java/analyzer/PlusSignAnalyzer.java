package analyzer;

import filter.EmptyStringTokenFilter;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.Tokenizer;
import tokenizer.PlusSignTokenizer;

public class PlusSignAnalyzer extends Analyzer {
    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        Tokenizer tokenizer = new PlusSignTokenizer();
        TokenFilter filter = new EmptyStringTokenFilter(tokenizer);
        return new TokenStreamComponents(tokenizer, filter);
    }
}
