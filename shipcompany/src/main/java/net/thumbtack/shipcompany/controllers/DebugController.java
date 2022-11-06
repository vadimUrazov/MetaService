package net.thumbtack.shipcompany.controllers;

import lombok.extern.slf4j.Slf4j;
import net.thumbtack.shipcompany.exception.ServiceException;
import net.thumbtack.shipcompany.service.DebugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

@Controller
@Validated
@Slf4j
public class DebugController {
    private final DebugService debugService;

    @Autowired
    public DebugController(DebugService debugService) {
        this.debugService = debugService;
    }

    @MutationMapping
    public int clear() throws ServiceException {
        debugService.clear();
        return 0;
    }

}
