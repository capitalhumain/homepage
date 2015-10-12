package lang;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.jena.graph.Triple;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

/**
 *
 * @author tzuyichao
 */
public class ExecutionContext {
    public static final String GLOBAL = "global";
    public static final String LOCAL = "local";
    private boolean sytaxError = false;
    private List<String> syntaxErrorMsgList = new ArrayList<String>();
    
    private final Map<String, Model> symbolTable; 
    
    public ExecutionContext() {
        symbolTable = new HashMap<String, Model>();
        symbolTable.put(LOCAL, ModelFactory.createDefaultModel());
    }
    
    public void setGlobal(Model model) {
        if(null == model) {
            throw new IllegalArgumentException("model is null");
        }
        // 直接蓋掉 >"<
        symbolTable.put(GLOBAL, model);
    }
    
    public void addLocalTriple(Triple triple) {
        if(null == triple) {
            throw new IllegalArgumentException("triple is null");
        }
        symbolTable.get(LOCAL).getGraph().add(triple);
    }
    
    public void printGlobal() {
        Model model = symbolTable.get(GLOBAL);
        if(model == null) {
            System.out.println("Gloabl not load");
        } else {
            model.write(System.out, "TURTLE");
        }
    }
    
    public void setSyntaxError() {
        sytaxError = true;
    }
    
    public boolean hasSyntaxError() {
        return sytaxError;
    }
    
    public void setSyntaxErrorMessage(int linenum, int charnum, String msg) {
        syntaxErrorMsgList.add(String.format("Line: %d Character: %d [%s]", linenum, charnum, msg));
    }
    
    public void showErrorMsg(PrintStream out) {
        if(syntaxErrorMsgList.isEmpty()) {
            out.println("No synatax error message in record");
        } else {
            for(String msg : syntaxErrorMsgList) {
                out.println(msg);
            }
        }
    }
}
