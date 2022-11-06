package net.thumbtack.shipcompany.controllers;

import net.thumbtack.shipcompany.dao.UserDao;
import net.thumbtack.shipcompany.dto.request.RegisterClientDtoRequest;
import net.thumbtack.shipcompany.dto.response.ClientDtoResponse;
import net.thumbtack.shipcompany.service.AccountService;
import net.thumbtack.shipcompany.service.ClientService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
public class TestAccountController extends AbstractControllerTest {


    @SpyBean
    ClientService service;

    @SpyBean
    AccountService accountService;

    @Autowired
    @Qualifier("DaoUser")
    UserDao userDao;


    @Test
    public void testGetUser() throws Exception {
        RegisterClientDtoRequest request = new RegisterClientDtoRequest("Иванов", "Иван", "Иванович", "ivanov@mail.ru", "8-916-621-32-64", "ivanovIvanv", "12s223dfghj");


        var res = service.registerClient(request);
        ClientDtoResponse response = (ClientDtoResponse) accountService.getUser(res.getId());

        assertNotNull(response);
        assertTrue(response.getId() > 0);
        assertEquals(request.getSurname(), response.getSurname());
        assertEquals(request.getName(), response.getName());
        assertEquals(request.getMiddlename(), response.getMiddlename());
        assertEquals(request.getEmail(), response.getEmail());
        assertEquals(request.getPhone().replace("-", ""), response.getPhone());
        assertEquals("CLIENT", response.getType());

    }
}
