package noise;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VirtuosoQueryResultResult {
    @JsonProperty("distinct")
    public boolean distinct;
    @JsonProperty("ordered")
    public boolean ordered;
    
}
