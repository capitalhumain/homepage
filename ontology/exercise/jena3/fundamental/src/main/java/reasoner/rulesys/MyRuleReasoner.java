package reasoner.rulesys;

import java.util.List;

import org.apache.jena.reasoner.ReasonerFactory;
import org.apache.jena.reasoner.rulesys.FBRuleReasoner;
import org.apache.jena.reasoner.rulesys.Rule;

public class MyRuleReasoner extends FBRuleReasoner {

	public MyRuleReasoner(List<Rule> ruleSet, ReasonerFactory factory) {
		super(ruleSet, factory);
	}
}
