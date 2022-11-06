package net.thumbtack.buscompany;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import net.thumbtack.buscompany.config.TestJdbcConfig;
import net.thumbtack.buscompany.dao.*;
import net.thumbtack.buscompany.exception.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = "spring.main.allow-bean-definition-overriding=true", classes = {Application.class, TestJdbcConfig.class})
@Transactional
public abstract class AbstractDaoTest extends AbstractTest {

    @Autowired
    protected static Gson gson;

    @Autowired
    @Qualifier("DaoUser")
    protected UserDao userDao;

    @Autowired
    @Qualifier("DaoAdmin")
    protected AdminDao adminDao;

    @Autowired
    @Qualifier("DaoAccount")
    protected AccountDao accountDao;

    @Autowired
    @Qualifier("DaoDebug")
    protected DebugDao debugDao;

    @Autowired
    @Qualifier("DaoTrip")
    protected TripDao tripDao;

    @Autowired
    protected ObjectMapper mapper;


    @BeforeEach
    public void setUp() throws ServiceException {
        debugDao.clear();
    }
}
