package com.delta.cs.rms.fuseki.helper;

import com.delta.cs.rms.fuseki.vo.ClientIdSupportVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author tzuyichao
 */
public class RequestHelper {
    /**
     * 
     * @param request
     * @return
     * @throws IOException 
     * @throws NullPointerException 如果request或request#getInputStream()是null的時候
     */
    public static ClientIdSupportVO convertClientIdSupportVO(HttpServletRequest request) throws IOException {
        Objects.requireNonNull(request);
        InputStream inputStream = request.getInputStream();
        Objects.requireNonNull(inputStream);
        
        ObjectMapper objectMapper = new ObjectMapper();
        ClientIdSupportVO vo = objectMapper.readValue(inputStream, ClientIdSupportVO.class);
        
        return vo;
    }
}
