package hello;

import com.mongodb.Mongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {
    public static final int READ = 0b00000000000000000000000000000100;
    public static final int WRITE = 0b00000000000000000000000000000010;
    public static final int EXECUTE = 0b00000000000000000000000000000001;

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private AdvOptionRepository advOptionRepository;

    @Autowired
    private ApplicationContext applicationContext;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void  run(String... args) throws Exception {
//        Mongo mongo = applicationContext.getBean(Mongo.class);
//        System.out.println(String.format("=====> Connect Point: %s", mongo.getConnectPoint()));

        repository.deleteAll();
        // save customers
        repository.save(new Customer("Alice", "Smith", 6));
        repository.save(new Customer("Bob", "Smith", 7));
        // fetch all
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for(Customer customer : repository.findAll()) {
            System.out.println(customer.toString());
            if((customer.getPrivilege() & READ) == READ) {
                System.out.println(String.format("%s has READ", customer.toString()));
            }
            if((customer.getPrivilege() & WRITE) == WRITE) {
                System.out.println(String.format("%s has READ", customer.toString()));
            }
            if((customer.getPrivilege() & EXECUTE) == EXECUTE) {
                System.out.println(String.format("%s has READ", customer.toString()));
            }
        }
        // fetch an individual customer
        System.out.println("Customer found with findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByFirstName("Alice"));
        System.out.println("Customers found with findByLastName('Smith'):");
        System.out.println("--------------------------------");
        for(Customer customer: repository.findByLastName("Smith")) {
            System.out.println(customer.toString());
        }
        System.out.println("Find by firstName and lastName");
        System.out.println("--------------------------------");
        System.out.println(repository.findByFirstNameAndLastName("Bob", "Smith"));

        System.out.println("AdvOpton Section");
        System.out.println("--------------------------------");
        advOptionRepository.deleteAll();
        // save AdvOption
        AdvOption option1 = new AdvOption();
        option1.setModule("VisitReport");
        option1.setSection("BasicInformation");
        BitSet priv = new BitSet(5);
        priv.set(3, true);
        option1.getPrivilege().xor(priv);
        List<AdvOptionSchema> custom = new ArrayList<>();
        AdvOptionSchema customer_background = new AdvOptionSchema();
        customer_background.setName("background");
        customer_background.setType("richtext");
        customer_background.setRequired(true);
        custom.add(customer_background);
        option1.getSchemas().put("customer", custom);
        advOptionRepository.save(option1);

        for(AdvOption opt : advOptionRepository.findAll()) {
            System.out.println(opt);
        }
    }
}
