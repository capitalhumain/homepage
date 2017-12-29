package playground.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import playground.app.Application;
import playground.service.HelloService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes={Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerTests {
    @Mock
    HelloService helloService;

    @BeforeEach
    void init() {
        when(helloService.greeting(null)).thenReturn("Hello World");
    }

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testAbout() {
        String message = this.restTemplate.getForObject("/about", String.class);
        assertEquals("JUnit 5 and Spring Boot Example", message);
    }

    @Disabled
    @Test
    public void testFailed() {
        fail("This is disabled test method");
    }

    @Tag("slow")
    @Test
    public void testTagSlow() {
        fail("slow tag test method");
    }
}
