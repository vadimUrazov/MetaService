package net.thumbtack.shipcompany.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Data;
import net.thumbtack.shipcompany.validate.Date;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@AllArgsConstructor
public class CreateOrderRequest {
    private long idClient;
    private long tripId;
    @NotEmpty
    @Date
    private String date;
    private List<PassengerDto> passengers;
    private List<CargoDto> cargoDtos;
    private String orderType = "CARGO";

    public CreateOrderRequest(long idClient, long tripId, String date, List<PassengerDto> passengers) {
        this.idClient = idClient;
        this.tripId = tripId;
        this.date = date;
        this.passengers = passengers;
        this.orderType = "PASS";
    }

    @JsonCreator
    public CreateOrderRequest() {
    }
}
