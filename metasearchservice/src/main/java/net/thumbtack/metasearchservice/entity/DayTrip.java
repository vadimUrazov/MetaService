package net.thumbtack.metasearchservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data

@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DayTrip {

    private LocalDate date;


}
