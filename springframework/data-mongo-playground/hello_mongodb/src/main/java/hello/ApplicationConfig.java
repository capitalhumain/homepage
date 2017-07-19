package hello;

import com.mongodb.Mongo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.net.UnknownHostException;

/**
 * Created by tzuyichao on 17/07/2017.
 */
@Configuration
@EnableMongoRepositories
public class ApplicationConfig extends AbstractMongoConfiguration {
//    @Bean
//    public MongoClientFactoryBean mongo() {
//        MongoClientFactoryBean mongo = new MongoClientFactoryBean();
//        mongo.setHost("localhost");
//        mongo.setPort(30000);
//        return mongo;
//    }

    @Override
    public Mongo mongo() throws UnknownHostException {
        return new Mongo("localhost", 30000);
    }

    @Override
    protected String getDatabaseName() {
        return "test";
    }
}
