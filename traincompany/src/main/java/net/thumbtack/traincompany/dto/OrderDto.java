package net.thumbtack.traincompany.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.thumbtack.traincompany.dto.response.CargoDtoResponse;
import net.thumbtack.traincompany.dto.response.PassengerDtoResponse;

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
    private String trainName;
    private String date;
    private String start;
    private String duration;
    private BigDecimal price;
    private BigDecimal totalPrice;
    private List<PassengerDtoResponse> passengers;
    private List<CargoDtoResponse> cargoDtos;

}
