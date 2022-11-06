package net.thumbtack.shipcompany.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PassengerDtoResponse {
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    private long passport;
}
