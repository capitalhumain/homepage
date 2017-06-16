package hello;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by tzuyichao on 16/06/2017.
 */
public class HelloJob implements Job {
    private static final Logger logger = Logger.getLogger(HelloJob.class);

    @Autowired
    private HelloService helloService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("Entering HelloJob...");
        helloService.sayHello();
        logger.info("HelloJob Completed.");
    }
}
