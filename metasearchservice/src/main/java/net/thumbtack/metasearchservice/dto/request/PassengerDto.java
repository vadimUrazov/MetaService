package net.thumbtack.metasearchservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDto {
    private String firstName;
    private String lastName;
    private long passport;
}
