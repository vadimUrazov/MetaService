package net.thumbtack.buscompany.dto.response;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.thumbtack.buscompany.dto.BusDto;
import net.thumbtack.buscompany.dto.DayTripDto;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class GetTripResponse {

  private long id;
  private String fromStation;
  private String toStation;
  private BusDto bus;
  private BigDecimal price;
  private String start;
  private String duration;
  private List<DayTripDto> dayTrips;
  private boolean approved;

}
