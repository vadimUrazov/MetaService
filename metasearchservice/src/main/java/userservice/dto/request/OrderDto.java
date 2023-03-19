package userservice.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDto {
  private long idClient;
  private long tripId;
  @NotEmpty
  private String date;
  private List<PassengerDto> passengers;
  private List<CargoDto> cargoDtos;
  private String orderType = "CARGO";

  public OrderDto(long idClient, long tripId, String date,
      List<PassengerDto> passengers) {
    this.idClient = idClient;
    this.tripId = tripId;
    this.date = date;
    this.passengers = passengers;
    this.orderType = "PASS";
  }

  @JsonCreator
  public OrderDto() {
  }
}
