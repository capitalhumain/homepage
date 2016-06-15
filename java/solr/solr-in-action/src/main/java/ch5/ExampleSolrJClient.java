package ch5;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrDocument;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.io.PrintStream;

public class ExampleSolrJClient {
  public static void main(String[] args) {
    String serverUrl = (args != null && args.length > 0)?args[0]:"http://localhost:8983/solr/core1";

    SolrClient solr = new HttpSolrClient(serverUrl);
    try {
      SolrInputDocument doc1 = new SolrInputDocument();

      doc1.setField("id", "3");
      doc1.setField("screen_name_s", "@thelabdude");
      doc1.setField("type_s", "post");
      doc1.setField("lang_s", "en");
      doc1.setField("timestamp_tdt", "2012-05-22T09:30:22Z/HOUR");
      doc1.setField("favorites_count_ti", "10");
      doc1.setField("text_t", "#Yummm :) Drinking a latte at Caffe Grecco in SF's historic North Beach... Learning text analysis with #SolrInAction by @ManningBooks on my i-Pad");

      solr.add(doc1);

      SolrInputDocument doc2 = new SolrInputDocument();

      doc2.setField("id", "4");
      doc2.setField("screen_name_s", "@thelabdude");
      doc2.setField("type_s", "post");
      doc2.setField("lang_s", "en");
      doc2.setField("timestamp_tdt", "2012-05-23T09:30:22Z/HOUR");
      doc2.setField("favorites_count_ti", "10");
      doc2.setField("text_t", "Just downloaded the ebook of #SolrInAction from @ManningBooks http://bit.ly/T3eGYG to learn more about #Solr http://bit.ly/3ynriE");
      doc2.setField("link_ss", "http://manning.com/grainger/");
      doc2.setField("link_ss", "http://lucene.apache.org/solr/");

      solr.add(doc2);

      // normal commit, commit 之後才能被搜尋到
      solr.commit(true, true);
    } catch(SolrServerException e) {
      e.printStackTrace();
    } catch(IOException e) {
      e.printStackTrace();
    }

    try {
      for(SolrDocument next : simpleSolrQuery(solr, "*:*", 10)) {
        prettyPrint(System.out, next);
      }
    } catch(SolrServerException e) {
      e.printStackTrace();
    } catch(IOException e) {
      e.printStackTrace();
    }
  }

  static SolrDocumentList simpleSolrQuery(SolrClient solr, String query, int rows) throws SolrServerException, IOException {
    SolrQuery solrQuery = new SolrQuery(query);
    solrQuery.setRows(rows);
    QueryResponse resp = solr.query(solrQuery);
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
}
