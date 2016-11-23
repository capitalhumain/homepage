package com.delta.cs.rms.fuseki.filter;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import static org.apache.jena.fuseki.Fuseki.serverLog;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

/**
 * Apache Shiro Custom Whitelist IP Authentication Filter
 * 
 * @author tzuyichao
 */
public class ShiroIPFilter extends BasicHttpAuthenticationFilter {
    private Set<String> whitelist = Collections.EMPTY_SET;
    
    public void setWhitelist(String list) {
        whitelist = new HashSet<>();
        Collections.addAll(whitelist, list.split(","));
        serverLog.debug("set white list");
    }
    
    @Override
    protected boolean isEnabled(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        serverLog.debug(">>>>>>>" + req.getRemoteAddr());
        if(whitelist.contains(req.getRemoteAddr())) {
            return false;
        }
        return super.isEnabled(req, res);
    }
}
