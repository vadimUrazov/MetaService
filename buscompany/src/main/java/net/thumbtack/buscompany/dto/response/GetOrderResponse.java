package net.thumbtack.buscompany.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.thumbtack.buscompany.dto.OrderDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOrderResponse {

  private List<OrderDto> orders;

}
