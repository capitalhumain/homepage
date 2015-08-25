package noise;

public interface DBPediaResultExtractor<R> {
    public String getName();
    public String responseType();
    public void apply(String source, String fieldName);
    public R result();
}
