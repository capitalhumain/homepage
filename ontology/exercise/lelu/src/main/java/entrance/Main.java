package entrance;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import lang.ExecutionContext;
import lang.LeluErrorListener;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import parser.ImportLabListener;
import parser.LeluLexer;
import parser.LeluParser;

/**
 *
 * @author tzuyichao
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String inputFile = null;
        if ( args.length>0 ) inputFile = args[0];
        InputStream is = System.in;
        if ( inputFile!=null ) {
            is = new FileInputStream(inputFile);
        }
        ANTLRInputStream input = new ANTLRInputStream(is);
        
        LeluLexer lexer = new LeluLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LeluParser parser = new LeluParser(tokens);
        ExecutionContext ctx = new ExecutionContext();
        parser.addErrorListener(new LeluErrorListener(ctx));
        LeluParser.ProgContext progCtx = parser.prog();
        if(!ctx.hasSyntaxError()) {
            List<ParseTree> trees = progCtx.children;

            ParseTreeWalker walker = new ParseTreeWalker();
            ImportLabListener listener = new ImportLabListener(parser, ctx);

            for(ParseTree tree : trees) {
                walker.walk(listener, tree);
            }
        } else {
            System.out.println("Syntax Error");
            ctx.showErrorMsg(System.err);
        }
    }
}
