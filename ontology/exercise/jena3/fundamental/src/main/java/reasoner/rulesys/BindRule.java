package reasoner.rulesys;

import java.io.InputStream;
import java.util.List;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.impl.InfModelImpl;
import org.apache.jena.reasoner.InfGraph;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.rulesys.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BindRule {
	private static Logger log = LoggerFactory.getLogger(BindRule.class);

	public static void main(String[] args) {
		String ruleStr = "[ rdfs2: (?x ?p ?y) (?p rdfs:domain ?c) -> (?x rdf:type ?c) ]";
		List<Rule> rules = Rule.parseRules(ruleStr);
		
		Reasoner myRuleReasoner = new MyRuleReasoner(rules, null);
		
		InputStream in = BindRule.class.getClassLoader().getResourceAsStream("fileB.ttl");
		Model model = ModelFactory.createDefaultModel();
		model.read(in, null, "TTL");
		log.info("Origin Model: ");
		model.write(System.out, "TURTLE");
		
		// bind rule, FBRuleInfGraph的instance
		// FBRuleInfGraph裡的rebind()，才叫engine去做該做的事情
		InfGraph infGraph = myRuleReasoner.bind(model.getGraph());
		log.info(infGraph.toString());
		
		// Create InfModel with new graph instance
		InfModel infModel = new InfModelImpl(infGraph);
		log.info("Inference Model: ");
		infModel.write(System.out, "TURTLE");
	}

}
