package playground.app;

import org.apache.catalina.filters.RequestDumperFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"playground"})
@EntityScan("playground.model")
@EnableJpaRepositories("playground.repository")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public FilterRegistrationBean requestDumpFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();

        filterRegistrationBean.setFilter(new RequestDumperFilter());
        filterRegistrationBean.setName("requestdumper");
        filterRegistrationBean.addUrlPatterns("*");
        filterRegistrationBean.setOrder(1);

        return filterRegistrationBean;
    }
}
