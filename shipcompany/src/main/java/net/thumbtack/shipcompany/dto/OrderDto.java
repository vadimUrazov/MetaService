package net.thumbtack.shipcompany.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.thumbtack.shipcompany.dto.response.CargoDtoResponse;
import net.thumbtack.shipcompany.dto.response.PassengerDtoResponse;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private long orderId;
    private long tripId;
    private String fromStation;
    private String toStation;
    private String shipName;
    private String date;
    private String start;
    private String duration;
    private BigDecimal price;
    private BigDecimal totalPrice;
    private List<PassengerDtoResponse> passengers;
    private List<CargoDtoResponse> cargoDtos;

}
