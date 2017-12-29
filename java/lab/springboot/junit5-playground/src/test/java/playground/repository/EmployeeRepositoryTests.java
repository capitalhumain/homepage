package playground.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import playground.model.Employee;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


/**
 * http://www.baeldung.com/spring-boot-testing
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class EmployeeRepositoryTests {
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    void whenFindByName_thenReturnEmployee() {
        // given
        Employee alex = new Employee("alex");
        entityManager.persist(alex);
        entityManager.flush();

        // when
        Employee found = employeeRepository.findByName(alex.getName());

        // then
        assertThat(found.getName())
                .isEqualTo(alex.getName());
    }
}
