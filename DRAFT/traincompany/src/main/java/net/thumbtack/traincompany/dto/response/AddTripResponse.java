package net.thumbtack.traincompany.dto.response;

import lombok.*;
import net.thumbtack.traincompany.dto.DayTripDto;
import net.thumbtack.traincompany.dto.TrainDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AddTripResponse {
    private long id;
    private String fromStation;
    private List<String> durationStations = new ArrayList<>();
    private String toStation;
    private TrainDto trainDto;
    private BigDecimal price;
    private String start;
    private String duration;
    private List<DayTripDto> dayTrips;
    private boolean approved;

}
