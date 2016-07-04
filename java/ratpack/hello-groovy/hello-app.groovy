@Grapes ([
  @Grab('io.ratpack:ratpack-groovy:1.3.3'),
  @Grab('org.slf4j:slf4j-log4j12:1.7.21')
])

import ratpack.http.Status
import ratpack.parse.Parse
import com.fasterxml.jackson.databind.JsonNode
import static ratpack.groovy.Groovy.ratpack
import static ratpack.jackson.Jackson.jsonNode

ratpack {
  handlers {
    get {
      render "Hello, World!"
    }
    get("foo") {
      render "Hello, Foo!"
    }
    post("bla") {
      def name = ""
      def promise = parse(jsonNode()).map { n -> n.get("name").asText() }
      promise
        .onError({Throwable t ->
          println t.message
          response.status(Status.of(500))
          render "Error"
        })
        .then({ String result ->
          name = result
          render name
        })
    }
    path("goo") {
      byMethod {
        get {
          render "Hello, Goo! - GET -"
        }
        post {
          render "Hello, Goo! - POST -"
        }
      }
    }
    prefix("products") {
      get("list") {
        render "Product list"
      }
      get("get") {
        render "Product get"
      }
      get("search") {
        render "Product Search"
      }
    }
  }
}
