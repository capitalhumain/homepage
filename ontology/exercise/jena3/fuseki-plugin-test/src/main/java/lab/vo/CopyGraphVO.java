package lab.vo;

/**
 *
 * @author tzuyichao
 */
public class CopyGraphVO {
    public String sourceDataset;
    public String targetDataset;
    
    public boolean isValid() {
        return !(null == sourceDataset || null == targetDataset || "".equals(sourceDataset) || "".equals(targetDataset));
    }
}
