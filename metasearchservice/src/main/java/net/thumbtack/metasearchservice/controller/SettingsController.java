package net.thumbtack.metasearchservice.controller;

import lombok.extern.slf4j.Slf4j;
import net.thumbtack.metasearchservice.dto.request.CreateOrderRequest;
import net.thumbtack.metasearchservice.dto.response.GetSettingsResponse;
import net.thumbtack.metasearchservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Controller
@Validated
@Slf4j
public class SettingsController {
    @Value("${server.port}")
    private int port;


    @QueryMapping
    public GetSettingsResponse getSettings() throws Exception {


        return new GetSettingsResponse(port);
    }

}
