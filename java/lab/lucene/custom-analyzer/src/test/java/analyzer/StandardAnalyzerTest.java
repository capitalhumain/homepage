package analyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.fail;

public class StandardAnalyzerTest {

    @Test
    public void testHappy() {
        Analyzer analyzer = new StandardAnalyzer();
        // Tokenizer#setReader()是把reader設定給Tokenizer的inputPending attribute(如果給的reader不是null，
        // 而且Tokenizer的input不是ILLEGAL_STATE_READER的話
        // "this", "is" => Stop Words清單裡面
        TokenStream tokenStream = analyzer.tokenStream("dummy", new StringReader("This is  some test"));
        try {
            // 換句話說，如果第一次直接用Tokenizer#incrementToken()會炸裂（會收到 IllegalStateException的錯誤）
            // 必須先呼叫Tokenizer#reset()，把inputPending存放的reader物件設定給input
            tokenStream.reset();
            while (tokenStream.incrementToken()) {
                System.out.println(tokenStream.getAttribute(CharTermAttribute.class).toString());
            }
        } catch(IOException e) {
            fail(e.getMessage());
        } finally {
            try { tokenStream.close(); } catch(IOException e) {}
            analyzer.close();
        }
    }
}
