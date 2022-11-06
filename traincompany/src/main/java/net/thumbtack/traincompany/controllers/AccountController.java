package net.thumbtack.traincompany.controllers;

import lombok.extern.slf4j.Slf4j;
import net.thumbtack.traincompany.dto.request.UserDto;
import net.thumbtack.traincompany.exception.ServiceException;
import net.thumbtack.traincompany.service.AccountService;
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
    public int deleteUser(@Validated @Argument long id) throws ServiceException {

        service.deleteUser(id);
        return 0;
    }

    @QueryMapping
    @PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
    public UserDto getUser(@Validated @Argument long id) throws ServiceException {
        return service.getUser(id);
    }
}
