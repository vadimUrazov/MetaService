package net.thumbtack.shipcompany.controllers;

import lombok.extern.slf4j.Slf4j;
import net.thumbtack.shipcompany.dto.request.UserDto;
import net.thumbtack.shipcompany.exception.ServiceException;
import net.thumbtack.shipcompany.service.AccountService;
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
public class AccountController {
    private final AccountService service;


    @Autowired
    public AccountController(AccountService service) {
        this.service = service;

    }

    @MutationMapping
    @PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
    public int deleteUser(@Argument long id) throws ServiceException {
        service.deleteUser(id);
        return 0;
    }

    @QueryMapping
    @PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
    public UserDto getUser(@Argument long id) throws ServiceException {

        return service.getUser(id);
    }
}
