package net.thumbtack.traincompany.service;

import net.thumbtack.traincompany.dto.request.RegisterAdminDtoRequest;
import net.thumbtack.traincompany.dto.request.UpdateAdminDtoRequest;
import net.thumbtack.traincompany.dto.response.ClientDtoResponse;
import net.thumbtack.traincompany.dto.response.GetClientsResponse;
import net.thumbtack.traincompany.dto.response.RegisterAdminDtoResponse;
import net.thumbtack.traincompany.dto.response.UpdateAdminDtoResponse;
import net.thumbtack.traincompany.entity.Admin;
import net.thumbtack.traincompany.entity.Client;
import net.thumbtack.traincompany.entity.UserType;
import net.thumbtack.traincompany.exception.ServiceException;
import net.thumbtack.traincompany.service.mappers.AdminRegisterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService extends ServiceBase {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public RegisterAdminDtoResponse registerAdmin(RegisterAdminDtoRequest request) throws ServiceException {
        var password = passwordEncoder.encode(request.getPassword());
        Admin admin = AdminRegisterMapper.INSTANCE.fromDto(request);
        admin.setUserType(UserType.ADMIN);
        admin.setPassword(password);
        Admin res = (Admin) userDao.registerUser(admin);
        RegisterAdminDtoResponse registerAdminDtoResponse = new RegisterAdminDtoResponse(res.getId(),
                res.getSurname(), res.getName(), res.getMiddlename(), res.getPosition(), "ADMIN");


        return registerAdminDtoResponse;
    }


    public UpdateAdminDtoResponse updateAdmin(long id, UpdateAdminDtoRequest request) throws ServiceException {
        Admin admin = (Admin) userDao.getUserById(id);
        admin.setSurname(request.getSurname());
        admin.setName(request.getName());
        admin.setMiddlename(request.getMiddlename());
        admin.setPassword(request.getNewPassword());
        admin.setPosition(request.getPosition());

        userDao.updateUser(admin);

        return new UpdateAdminDtoResponse(admin.getName(), admin.getSurname(), admin.getMiddlename(), admin.getPosition(), "ADMIN");
    }

    public GetClientsResponse getClients() throws ServiceException {
        List<ClientDtoResponse> result = new ArrayList<>();

        List<Client> clients = adminDao.getClients();
        for (Client client : clients) {
            result.add(new ClientDtoResponse(client.getId(), client.getSurname(), client.getName(), client.getMiddlename(), client.getEmail(), client.getPhone(), "CLIENT"));
        }

        return new GetClientsResponse(result);
    }

}
