package rulesys;

import org.apache.jena.reasoner.rulesys.ClauseEntry;
import org.apache.jena.reasoner.rulesys.Rule;
import org.apache.log4j.Logger;

/**
 * 
 * [ rdfs2: (?x ?p ?y) (?p rdfs:domain ?c) -> (?x rdf:type ?c) ]
 * 
 * Axiom example:
 * -> (rdf:type      rdfs:range rdfs:Class).
 * 
 * @author tzuyichao
 *
 */
public class ParseRule {
    private static Logger log = Logger.getLogger(ParseRule.class);
    
    public static void inspect(final Rule rule) {
    	log.info(rule);
    	log.info(String.format("Name: %s", rule.getName()));
	    log.info(String.format("is Axiom: %b", rule.isAxiom()));
	    log.info(String.format("is Backward: %b", rule.isBackward()));
	    // 看head!!! head 如果是functor -> 且是monotonically function或沒有functor => return true
	    // else return false
	    log.info(String.format("is Monotonically: %b", rule.isMonotonic()));
	    log.info("Head (Consequents) Clause Entries:");
	    // consequents (head) 結果
	    for(ClauseEntry clauseEntry : rule.getHead()) {
	    	log.info(clauseEntry);
	    }
	    // Antecedents (body) 前因
	    log.info("Body (Antecedents) Clause Entries:");
	    for(ClauseEntry clauseEntry : rule.getBody()) {
	    	log.info(clauseEntry);
	    }
    }
    
	public static void main(String[] args) {
		if(args.length < 1) {
			System.out.println("Usage: java rulesys.ParseRule [rule]");
			return;
		}
		
		String ruleStr = args[0];
		try {
		    Rule rule = Rule.parseRule(ruleStr);
		    inspect(rule);
		} catch(Rule.ParserException e) {
			log.error("Parse Exception", e);
		}
	}

}
