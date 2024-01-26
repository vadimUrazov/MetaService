package net.thumbtack.adapter.service.users;

import net.thumbtack.adapter.dto.users.ClientDtoResponse;
import net.thumbtack.adapter.dto.users.RegisterClientDtoRequest;

import java.util.concurrent.Future;

public interface UserService<T> {
    String getTypeCompany();

     Future<T> registerUser(RegisterClientDtoRequest request);

    ClientDtoResponse getUserByLogin(String login);
}
