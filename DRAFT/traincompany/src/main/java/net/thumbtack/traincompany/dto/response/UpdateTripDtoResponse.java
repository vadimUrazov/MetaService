package net.thumbtack.traincompany.dto.response;

import lombok.*;
import net.thumbtack.traincompany.dto.DayTripDto;
import net.thumbtack.traincompany.dto.TrainDto;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UpdateTripDtoResponse {
    private long id;
    private String fromStation;
    private String toStation;
    private TrainDto bus;
    private BigDecimal price;
    private String start;
    private String duration;
    private List<DayTripDto> dayTrips;
    private boolean approved;
}
