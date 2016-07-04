import com.fasterxml.jackson.annotation.JsonProperty

public class Person {
  private String name
  Person(@JsonProperty("name") String name) {
    this.name = name
  }

  public String getName() {
    name
  }
}
