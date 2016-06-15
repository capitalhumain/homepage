package ch5;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrDocument;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.io.PrintStream;

public class ExampleQuery {
  static SolrDocumentList method1(SolrClient solr, String query, int rows) throws SolrServerException, IOException {
    SolrQuery solrQuery = new SolrQuery(query);
    solrQuery.setRows(rows);
    QueryResponse resp = solr.query(solrQuery);
    SolrDocumentList hits = resp.getResults();
    return hits;
  }

  static SolrDocumentList method2(SolrClient solr, String core, String query, int rows) throws SolrServerException, IOException {
    SolrQuery solrQuery = new SolrQuery(query);
    solrQuery.setRows(rows);
    QueryResponse resp = solr.query(core, solrQuery);
    SolrDocumentList hits = resp.getResults();
    return hits;
  }

  static void prettyPrint(PrintStream out, SolrDocument doc) {
    List<String> sortedFieldNames = new ArrayList<String>(doc.getFieldNames());
    Collections.sort(sortedFieldNames);
    out.println();
    for(String field : sortedFieldNames) {
      out.println(String.format("\t%s: %s", field, doc.getFieldValue(field)));
    }
    out.println();
  }

  public static void main(String[] args) throws Exception {
    String type = (args != null && args.length > 0) ? args[0] : "1";
    SolrClient solr = null;
    SolrDocumentList result = null;
    switch(type) {
      case "1":
        System.out.println("Method 1");
        solr = new HttpSolrClient("http://localhost:8983/solr/core1");
        result = method1(solr, "*:*", 10);
        break;
      case "2":
        System.out.println("Method 2");
        solr = new HttpSolrClient("http://localhost:8983/solr");
        result = method2(solr, "core1", "*:*", 10);
        break;
      default:
        System.out.println("Type not support");
        System.exit(-1);
    }
    if(result != null) {
      for(SolrDocument doc : result)
        prettyPrint(System.out, doc);
    }
  }
}
