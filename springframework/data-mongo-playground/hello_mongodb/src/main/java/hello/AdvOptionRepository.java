package hello;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by tzuyichao on 17/07/2017.
 */
public interface AdvOptionRepository extends MongoRepository<AdvOption, String> {
}
