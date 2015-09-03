package model;

import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.vocabulary.RDF;

public class GetGraph {
    public static final String NS = "http://www.example.org/example#";

    public static void main(String[] args) {
        Model model = ModelFactory.createDefaultModel();

        Resource subject = model.createResource(NS.concat("Shakespeare"));
        Property predicate = model.createProperty(NS.concat("wrote"));
        Resource object = model.createResource(NS.concat("Hamlet"));
        Statement triple = ResourceFactory.createStatement(subject, predicate, object);

        model.add(triple);

        Resource subject2 = model.createResource(NS.concat("Matsumoto"));
        Property predicate2 = model.createProperty(NS.concat("create"));
        Resource object2 = model.createResource(NS.concat("Ruby"));
        Statement triple2 = ResourceFactory.createStatement(subject2, predicate2, object2);

        model.add(triple2);

        Resource subject3 = model.createResource(NS.concat("Terence"));
//        Property predicate3 = model.createProperty(RDF.url.concat("type"));
        Resource object3 = model.createResource(NS.concat("Programmer"));
        Statement triple3 = ResourceFactory.createStatement(subject3, RDF.type, object3);

        model.add(triple3);

        model.write(System.out, "TURTLE");

        System.out.println("======= Get Graph =======");
        System.out.println(model.getGraph());
    }
}
