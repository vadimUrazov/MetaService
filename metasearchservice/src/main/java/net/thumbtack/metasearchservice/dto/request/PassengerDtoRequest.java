package net.thumbtack.metasearchservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDtoRequest {

    private String firstName;
    private String lastName;
    private long passport;
    private int place;
}
