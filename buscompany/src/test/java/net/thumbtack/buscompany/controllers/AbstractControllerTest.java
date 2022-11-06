package net.thumbtack.buscompany.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.RestAssured;
import net.thumbtack.buscompany.AbstractDaoTest;
import net.thumbtack.buscompany.Application;
import net.thumbtack.buscompany.config.TestJdbcConfig;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;


@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = "spring.main.allow-bean-definition-overriding=true", classes = {Application.class, TestJdbcConfig.class})
@Transactional
public abstract class AbstractControllerTest extends AbstractDaoTest {

    @Autowired
    protected ApplicationContext applicationContext;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;


    @LocalServerPort
    private int port;


    protected void set() {
        RestAssured.port = port;
    }
}
