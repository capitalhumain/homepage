package noise;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
  Example for Virtuoso SPARQL query result
  <pre>
{
  head: {
    link: [ ],
    vars: [
      "aliasURI"
    ]
  },
  results: {
    distinct: false,
    ordered: true,
    bindings: [
      {
        aliasURI: {
          type: "uri",
          value: "http://dbpedia.org/resource/Big_Four_(tennis)"
        }
      }
    ]
  }
}
 </pre>
 */
public class VirtuosoQueryResult {
  @JsonProperty("head")
  public VirtuosoQueryResultHeader head;
  @JsonProperty("results")
  public VirtuosoQueryResultResult result;
}
