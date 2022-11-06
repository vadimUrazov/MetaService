package net.thumbtack.shipcompany.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDto {
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    private long passport;
}
