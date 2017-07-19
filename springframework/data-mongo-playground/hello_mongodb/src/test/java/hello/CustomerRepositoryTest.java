package hello;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={Application.class})
public class CustomerRepositoryTest {
    @Autowired
    CustomerRepository repository;

    @Test
    public void test() {
        repository.deleteAll();

        List<Customer> allCustomer = repository.findAll();
        assertThat(allCustomer, notNullValue());
        assertThat(allCustomer.size(), is(0));
    }
}
