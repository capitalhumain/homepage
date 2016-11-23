package com.delta.cs.rms.fuseki.vo;

/**
 *
 * @author tzuyichao
 */
public class PostTtlVO {
    public String targetDataset;
    public String content;
    
    public boolean isValid() {
        return targetDataset != null && content != null && !"".equals(targetDataset) && !"".equals(content);
    }
}
