package net.thumbtack.metasearchservice.controller;

import lombok.extern.slf4j.Slf4j;
import net.thumbtack.metasearchservice.dto.request.GetPathsDtoRequest;
import net.thumbtack.metasearchservice.dto.response.GetPathDtoResponse;
import net.thumbtack.metasearchservice.service.MetaSearchService;
import net.thumbtack.metasearchservice.validate.PathValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Controller
@Validated
@Slf4j
public class ServiceController {


    private final MetaSearchService transportService;

    private final PathValidator validator;

    @Autowired
    public ServiceController(MetaSearchService metasearchService, PathValidator validator) {
        this.transportService = metasearchService;
        this.validator = validator;
    }

    @QueryMapping
    public GetPathDtoResponse getPaths(@Valid @Argument GetPathsDtoRequest request) throws Exception {
        if (validator.validate(request).isEmpty()) {
            throw new IllegalArgumentException();
        }
        return transportService.getPath(request);
    }


}
