package net.thumbtack.buscompany.controllers;

import lombok.extern.slf4j.Slf4j;
import net.thumbtack.buscompany.dto.response.ConfigDtoResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

@Controller
@Validated
@Slf4j
public class SettingsController {

    @Value("${min_password_length}")
    private int minPasswordLength;

    @Value("${max_name_length}")
    private int maxNameLength;

    @Value("${server.port}")
    private int port;

    @Value("${user_idle_timeout}")
    private int userIdleTimeout;

    @QueryMapping
    public ConfigDtoResponse getSettings() {
        return new ConfigDtoResponse(maxNameLength, minPasswordLength, port, userIdleTimeout);
    }

}
