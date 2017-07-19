package hello;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    public Customer findByFirstName(String firstName);
    public Customer findByFirstNameAndLastName(String firstName, String lastName);
    public List<Customer> findByLastName(String lastName);
}
