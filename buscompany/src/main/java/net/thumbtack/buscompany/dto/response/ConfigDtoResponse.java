package net.thumbtack.buscompany.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
