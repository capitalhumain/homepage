package parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import lang.ExecutionContext;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

public class ImportLabListener extends LeluBaseListener {
    LeluParser parser;
    ExecutionContext executionCtx;
    
    public ImportLabListener(LeluParser parser, ExecutionContext executionCtx) {
        this.parser = parser;
        this.executionCtx = executionCtx;
    }
    
    @Override
    public void enterImportDeclaration(LeluParser.ImportDeclarationContext ctx) { 
        String qName = ctx.qualifiedName().getText();
        System.out.println(String.format("Qualified Name: %s", qName));
        String fileName = String.format("./%s.ttl", qName);
        File f = new File(fileName);
        if(f.exists()) {
            System.out.println(f);
            try {
                FileInputStream in = new FileInputStream(f);
                Model model = ModelFactory.createDefaultModel();
                model.read(in, null, "TTL");
//                model.write(System.out, "TURTLE");
                executionCtx.setGlobal(model);
            } catch(FileNotFoundException e) {
            }
        } else {
            System.out.println("File does not exist");
        }
    }
    
    @Override
    public void enterListCommand(LeluParser.ListCommandContext ctx) { 
        executionCtx.printGlobal();
    }
    
    @Override
    public void enterCreateTboxExpr(LeluParser.CreateTboxExprContext ctx) {
        System.out.println("Create TBox: " + ctx.createTboxCommand().Identifier());
        // 
//        System.out.println(ctx.exception);
    }
    
    @Override
    public void enterSaveCommand(LeluParser.SaveCommandContext ctx) {
        System.out.println("SAVE called");
        // if identifier is null -> save
        // else -> save identifier
    }
}
