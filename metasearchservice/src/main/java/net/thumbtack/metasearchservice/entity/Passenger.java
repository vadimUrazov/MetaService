package net.thumbtack.metasearchservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Passenger {
    private String firstName;
    private String lastName;
    private long passport;
    private int place;
}
