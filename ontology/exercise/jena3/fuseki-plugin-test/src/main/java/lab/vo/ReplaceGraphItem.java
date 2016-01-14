package lab.vo;

/**
 *
 * @author tzuyichao
 */
public class ReplaceGraphItem {
    public String sourceDataset;
    public String namedGraph;
    
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("[sourceDataset: ").append(sourceDataset).append(", namedGraph: ").append(namedGraph).append("]");
        return result.toString();
    }
}
