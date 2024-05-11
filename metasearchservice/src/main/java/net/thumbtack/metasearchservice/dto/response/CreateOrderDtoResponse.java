package net.thumbtack.metasearchservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.thumbtack.metasearchservice.dto.TripDto;
import net.thumbtack.metasearchservice.dto.request.PassengerDto;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDtoResponse{
    private long idOrder;
    private long idClient;
    private String date;
    private List<TripDto> path;
    private int price;
    private List<PassengerDtoResponse> passengers;
    private List<CargoDtoResponse> cargoDtos;
}
