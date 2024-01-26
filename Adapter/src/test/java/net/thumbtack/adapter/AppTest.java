package net.thumbtack.adapter;

import net.thumbtack.adapter.config.AdapterConfig;
import net.thumbtack.adapter.dto.users.RegisterClientDtoRequest;
import net.thumbtack.adapter.service.users.UserProvider;
import net.thumbtack.adapter.service.users.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.net.ConnectException;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AdapterConfig.class, loader = AnnotationConfigContextLoader.class)
public class AppTest {

    @SpyBean
    UserProvider userProvider;

    List<UserService> beanList;

    @Autowired
    private RetryTemplate retryTemplate;
    @Test
    public void testRegisterUserSuccess() throws Exception {
        RegisterClientDtoRequest request = new RegisterClientDtoRequest("Иванов", "Иван", "Иванович",
                "ivanov@mail.ru", "8-916-621-32-64", "ivanovIvanv", "12s223dfghj","Bus");
        var r = userProvider.registerUser(request);

        assertNotNull(r);
    }
    @Test
    public void testRegisterUserFail() {
        RegisterClientDtoRequest request = new RegisterClientDtoRequest("Иванов", "Иван", "Иванович",
                "ivanov@mail.ru", "8-916-621-32-64", "ivanovIvanv", "12s223dfghj","Bus");

        assertThrows(ConnectException.class,
                ()->{
                    retryTemplate.execute(arg0 -> {userProvider.registerUser(request);return null;});
                });
    }
}
