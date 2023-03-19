package net.thumbtack.metasearchservice.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TripDto {
    private long id;

    private String fromStation;

    private String toStation;

    private String transport;

    private int price;

    private String start;

    private String duration;

    private List<String> dayTrips = new ArrayList<>();
}
