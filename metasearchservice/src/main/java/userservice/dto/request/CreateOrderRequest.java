package userservice.dto.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateOrderRequest {
  private List<OrderDto> orderDtos;

}
