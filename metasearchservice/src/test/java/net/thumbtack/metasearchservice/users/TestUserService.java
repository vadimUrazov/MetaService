package net.thumbtack.metasearchservice.users;

import net.thumbtack.metasearchservice.dto.request.*;
import net.thumbtack.metasearchservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
public class TestUserService {

    @SpyBean
    private UserService service;

    @Test
    public void testCreateOrder() throws Exception {
        RegisterClientDtoRequest ivanov=new RegisterClientDtoRequest("Ivanov", "Ivan","Ivanovich","ivan.ivanov@mail.ru","89132455645","ivan200","knckjcn");
        RegisterClientDtoRequest petrov=new RegisterClientDtoRequest("Petrov", "Ivan","Petrovich","petr.petnrov@mail.ru","8913343456","petr800","kjefvnjfv");
        service.registerUser(ivanov);
        service.registerUser(petrov);
        CreateOrderDtoRequest requestIvanov =
                new CreateOrderDtoRequest(
                        ivanov.getLogin(), 1, "2024-03-02",  245,List.of(
                        new PassengerDtoRequest("Ivanov", "Ivan", 222,1)),new ArrayList<>(),"PASS");
        CreateOrderDtoRequest requestPetrov =
                new CreateOrderDtoRequest(
                        petrov.getLogin(),  2,"2024-03-02",245  ,List.of(
                        new PassengerDtoRequest("Petrov", "Ivan", 223,2)),new ArrayList<>(),"PASS");
        var resIvanov = service.createOrder(requestIvanov);
        var resPetrov = service.createOrder(requestPetrov);
        assertNotNull(resIvanov);
        assertNotNull(resPetrov);

    }

    @Test
    public void testCreateOrderFail() throws Exception {
        RegisterClientDtoRequest ivanov=new RegisterClientDtoRequest("Ivanov", "Ivan","Ivanovich","ivan.ivanov@mail.ru","89132455645","ivan200","knckjcn");
        RegisterClientDtoRequest petrov=new RegisterClientDtoRequest("Petrov", "Ivan","Petrovich","petr.petnrov@mail.ru","8913343456","petr800","kjefvnjfv");
        service.registerUser(ivanov);
        service.registerUser(petrov);
        CreateOrderDtoRequest requestIvanov =
                new CreateOrderDtoRequest(
                        ivanov.getLogin(), 1, "2024-03-02",  245,List.of(
                        new PassengerDtoRequest("Ivanov", "Ivan", 222,1)),new ArrayList<>(),"PASS");
        CreateOrderDtoRequest requestPetrov =
                new CreateOrderDtoRequest(
                        petrov.getLogin(),  2,"2024-03-02",245  ,List.of(
                        new PassengerDtoRequest("Petrov", "Ivan", 223,1)),new ArrayList<>(),"PASS");
        var resIvanov = service.createOrder(requestIvanov);
        var resPetrov = service.createOrder(requestPetrov);
        assertNotNull(resIvanov);
        assertNull(resPetrov);
    }
}
