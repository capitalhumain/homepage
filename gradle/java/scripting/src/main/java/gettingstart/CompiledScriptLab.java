package gettingstart;

import javax.script.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

/**
 * Created by tzuyichao on 14/06/2017.
 */
public class CompiledScriptLab {
    public static void main(String[] args) throws FileNotFoundException, ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("groovy");
        if(engine instanceof Compilable) {
            System.out.println("Groovy Script Engine is compilable");
            // FileNotFoundException
            Reader reader = new FileReader("hello.groovy");
            CompiledScript script = ((Compilable)engine).compile(reader);
            if(script != null) {
                script.eval();
            } else {
                engine.eval(reader);
            }
        } else {
            System.out.println("Groovy Script Engine is NOT compilable");
        }
    }
}
