package net.thumbtack.buscompany.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.thumbtack.buscompany.dto.OrderDto;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOrderResponse {

    private List<OrderDto> orders;

}
