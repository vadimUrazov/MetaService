package net.thumbtack.metasearchservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String from;
    private String to;
    private LocalDate date;
    private List<Passenger> passengers;
}
