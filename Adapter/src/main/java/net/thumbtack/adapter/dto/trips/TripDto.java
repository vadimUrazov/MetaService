package net.thumbtack.adapter.dto.trips;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
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
