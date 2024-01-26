package net.thumbtack.buscompany.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.thumbtack.buscompany.dto.response.CargoDtoResponse;
import net.thumbtack.buscompany.dto.response.PassengerDtoResponse;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

  private long orderId;
  private long tripId;
  private String fromStation;
  private String toStation;
  private String busName;
  private String date;
  private String start;
  private String duration;
  private BigDecimal price;
  private BigDecimal totalPrice;
  private List<PassengerDtoResponse> passengers;
  private List<CargoDtoResponse> cargoDtos;
}
