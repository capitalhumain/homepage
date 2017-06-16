package hello;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * Created by tzuyichao on 16/06/2017.
 */
@Service
public class HelloService {
    private static final Logger logger = Logger.getLogger(HelloService.class);

    public void sayHello() {
        logger.info("Hello from HelloService");
        System.out.println("Hello from HelloService");
    }
}
