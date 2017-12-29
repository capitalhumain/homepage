package playground.repository;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 就只會在同一個Package找Spring Boot Configuration的相關設定class
 * 所以就做一個給他
 *
 * 否則就要把Application.class弄給他啟動spring context
 *
 */
@SpringBootApplication(scanBasePackages = {"playground"})
@EntityScan("playground.model")
@EnableJpaRepositories("playground.repository")
public class RepositoryTestConfiguration {
}
