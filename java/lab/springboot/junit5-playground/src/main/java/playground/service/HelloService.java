package playground.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class HelloService {
    public String greeting(String name) {
        if(StringUtils.isEmpty(name)) {
            return "Hello World";
        } else {
            return String.format("Hello, %", name);
        }
    }
}
