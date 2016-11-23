package com.delta.cs.rms.fuseki.vo;

/**
 *
 * @author tzuyichao
 */
public class ReplaceGraphVO {
    public String target;
    public ReplaceGraphItem[] sourceItems;
    
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Target: ").append(target).append("\n");
        for(ReplaceGraphItem item: sourceItems) {
            result.append(item).append("\n");
        }
        return result.toString();
    }
}
