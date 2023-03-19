package net.thumbtack.metasearchservice.controller;

import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import net.thumbtack.metasearchservice.dto.request.GetPathsDtoRequest;
import net.thumbtack.metasearchservice.dto.response.GetPathDtoResponse;
import net.thumbtack.metasearchservice.service.MetaSearchService;
import net.thumbtack.metasearchservice.validate.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

@Controller
@Validated
@Slf4j
public class ServiceController {


    private final MetaSearchService transportService;


    @Autowired
    public ServiceController(MetaSearchService metasearchService) {
        this.transportService = metasearchService;
    }

    @QueryMapping
    @Path
    public GetPathDtoResponse getPaths(@Valid @Argument GetPathsDtoRequest request) throws Exception {
        return transportService.getPath(request);
    }


}
