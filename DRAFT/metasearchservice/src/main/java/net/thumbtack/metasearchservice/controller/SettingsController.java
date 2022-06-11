package net.thumbtack.metasearchservice.controller;

import lombok.extern.slf4j.Slf4j;
import net.thumbtack.metasearchservice.dto.response.GetSettingsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

@Controller
@Validated
@Slf4j
public class SettingsController {
    @Value("${server.port}")
    private int port;

    @QueryMapping
    public GetSettingsResponse getSettings() {
        return new GetSettingsResponse(port);
    }
}
