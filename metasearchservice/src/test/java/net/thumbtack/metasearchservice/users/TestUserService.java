package net.thumbtack.metasearchservice.users;

import net.thumbtack.metasearchservice.dto.request.CreateOrderDtoRequest;
import net.thumbtack.metasearchservice.dto.request.CreateOrderRequest;
import net.thumbtack.metasearchservice.dto.request.PassengerDto;
import net.thumbtack.metasearchservice.dto.request.PassengerDtoRequest;
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

        CreateOrderDtoRequest requestIvanov =
                new CreateOrderDtoRequest(
                        1, 1, "2024-03-02",  245,List.of(
                        new PassengerDtoRequest("Ivanov", "Ivan", 222,1)),new ArrayList<>(),"PASS");
        CreateOrderDtoRequest requestPetrov =
                new CreateOrderDtoRequest(
                        2,  2,"2024-03-02",245  ,List.of(
                        new PassengerDtoRequest("Petrov", "Ivan", 223,2)),new ArrayList<>(),"PASS");
        var resIvanov = service.createOrder(requestIvanov);
        var resPetrov = service.createOrder(requestPetrov);
        assertNotNull(resIvanov);
        assertNotNull(resPetrov);

    }

    @Test
    public void testCreateOrderFail() throws Exception {
        CreateOrderDtoRequest requestIvanov =
                new CreateOrderDtoRequest(
                        1, 1, "2024-03-02",  245,List.of(
                        new PassengerDtoRequest("Ivanov", "Ivan", 222,1)),new ArrayList<>(),"PASS");
        CreateOrderDtoRequest requestPetrov =
                new CreateOrderDtoRequest(
                        2,  0,"2024-03-02",245  ,List.of(
                        new PassengerDtoRequest("Petrov", "Ivan", 223,1)),new ArrayList<>(),"PASS");
        var resIvanov = service.createOrder(requestIvanov);
        var resPetrov = service.createOrder(requestPetrov);
        assertNotNull(resIvanov);
        assertNull(resPetrov);
    }
}
