package net.thumbtack.traincompany.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ConfigDtoResponse {
    private int maxNameLength;
    private int minPasswordLength;
    private int port;
    private int userIdleTimeout;

}
