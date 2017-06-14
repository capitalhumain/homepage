package gettingstart;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import javax.script.Bindings;

public class ScriptEngineLab {
    public static void main(String[] args) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("groovy");
        System.out.println(engine.toString());
        try {
            engine.eval("println('hello from groovy')");
            engine.put("b", 100);
            Bindings scope1 = engine.createBindings();
            Bindings scope2 = engine.createBindings();
            scope1.put("b", 10);
            scope2.put("b", 20);
            engine.eval("println('global b:' + b)");
            engine.eval("println('scope1 b:' + b)", scope1);
            engine.eval("println('scope2 b:' + b)", scope2);
        } catch(ScriptException | NullPointerException e) {
            e.printStackTrace();
        } 
    }
}
