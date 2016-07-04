@Grapes ([
  @Grab('io.ratpack:ratpack-groovy:1.3.3'),
  @Grab('org.slf4j:slf4j-log4j12:1.7.21')
])

import ratpack.http.Status
import ratpack.parse.Parse
import com.fasterxml.jackson.databind.JsonNode
import static ratpack.groovy.Groovy.ratpack
import static ratpack.jackson.Jackson.jsonNode
import static ratpack.jackson.Jackson.fromJson
import static ratpack.util.Types.listOf;

import static ratpack.jackson.Jackson.json

def helloHandler(promise, errorAction, successAction) {
  promise
    .onError(errorAction)
    .then(successAction)
}

// https://ratpack.io/manual/current/jackson.html#reading_json_requests
ratpack {
  handlers {
    get {
      render(json(new Person("test")))
    }
    post("jsonNode") {
      def promise = parse(jsonNode()).map { n -> n.get("name").asText() }
      helloHandler(promise, {Throwable t ->
        println t.message
        response.status(Status.of(500))
        render "Error"
      }, { String result ->
        render result
      })
    }
    post("person") {
      def promise = parse(fromJson(Person.class)).map { p -> p.getName() }
      helloHandler(promise, {Throwable t ->
        println t.message
        response.status(Status.of(500))
        render "Error"
      }, { String result ->
        render result
      })
    }
    post("personList") {
      def promise = parse(fromJson(listOf(Person.class))).map { arr -> arr.get(0).getName() }
      helloHandler(promise, {Throwable t ->
        println t.message
        response.status(Status.of(500))
        render "Error"
      }, { String result ->
        render result
      })
    }
  }
}
