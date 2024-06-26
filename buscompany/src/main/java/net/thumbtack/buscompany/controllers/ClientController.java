package net.thumbtack.buscompany.controllers;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;
import net.thumbtack.buscompany.dto.request.RegisterClientDtoRequest;
import net.thumbtack.buscompany.dto.request.UpdateClientDtoRequest;
import net.thumbtack.buscompany.dto.response.GetClientsResponse;
import net.thumbtack.buscompany.dto.response.RegisterClientDtoResponse;
import net.thumbtack.buscompany.dto.response.UpdateClientDtoResponse;
import net.thumbtack.buscompany.exception.ServiceException;
import net.thumbtack.buscompany.service.AdminService;
import net.thumbtack.buscompany.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

@Controller
@Validated
@Slf4j
public class ClientController {

    private final ClientService service;
    private final AdminService adminService;


    @Autowired
    public ClientController(ClientService service, AdminService adminService) {
        this.service = service;
        this.adminService = adminService;
    }

    @MutationMapping
    public RegisterClientDtoResponse registerClient(@Argument @Valid RegisterClientDtoRequest request)
            throws Exception {
        return service.registerClient(request);
    }

    @MutationMapping
    @PreAuthorize("hasRole('CLIENT')")
    public UpdateClientDtoResponse updateClient(@Argument long id,
                                                @Argument @Valid UpdateClientDtoRequest request) throws Exception {

        return service.updateClient(id, request);
    }

    @QueryMapping
    @PreAuthorize("hasRole('ADMIN')")
    public GetClientsResponse getClients() throws ServiceException {
        return adminService.getClients();
    }


}
