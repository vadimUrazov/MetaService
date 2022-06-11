package net.thumbtack.metasearchservice.config;

import net.thumbtack.metasearchservice.Application;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration
@SpringBootTest(properties = "spring.main.allow-bean-definition-overriding=true", classes = {Application.class})
public class TestMetaSearchConfig {

}
