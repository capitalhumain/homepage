package tokenizer;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;
import java.io.Reader;

public class PlusSignTokenizer extends Tokenizer {
    protected CharTermAttribute charTermAttribute = addAttribute(CharTermAttribute.class);

    protected String stringToTokenize;

    protected int position = 0;

    public PlusSignTokenizer() {}

    public PlusSignTokenizer(Reader reader) {
        setReader(reader);
        int numChars;
        char[] buffer = new char[1024];
        StringBuilder stringBuilder = new StringBuilder();
        try {
            while((numChars = reader.read(buffer, 0, buffer.length)) != -1) {
                stringBuilder.append(buffer, 0, numChars);
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        this.stringToTokenize = stringBuilder.toString();
    }

    @Override
    public final boolean incrementToken() throws IOException {
        this.charTermAttribute.setEmpty();

        if(this.stringToTokenize == null) {
            reset();
        }

        int nextIndex = this.stringToTokenize.indexOf('+', this.position);

        if(nextIndex != -1) {
            String nextToken = this.stringToTokenize.substring(this.position, nextIndex);
            this.charTermAttribute.append(nextToken);
            this.position = nextIndex + 1;
            return true;
        } else if(this.position < this.stringToTokenize.length()) {
            String nextToken = this.stringToTokenize.substring(this.position);
            this.charTermAttribute.append(nextToken);
            this.position = this.stringToTokenize.length();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void reset() throws IOException {
        super.reset();
        if(this.stringToTokenize == null) {
            int numChars;
            char[] buffer = new char[1024];
            StringBuilder stringBuilder = new StringBuilder();
            try {
                while((numChars = this.input.read(buffer, 0, buffer.length)) != -1) {
                    stringBuilder.append(buffer, 0, numChars);
                }
            } catch(IOException e) {
                throw new RuntimeException(e);
            }
            this.stringToTokenize = stringBuilder.toString();
        }
        this.position = 0;
    }
}
