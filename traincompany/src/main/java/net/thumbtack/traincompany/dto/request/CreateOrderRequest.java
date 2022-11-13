package net.thumbtack.traincompany.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Data;
import net.thumbtack.traincompany.validate.Date;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@AllArgsConstructor
public class CreateOrderRequest {

    private long idClient;
    private long tripId;
    private String fromStation;
    private String toStation;
    @NotEmpty
    @Date
    private String date;

    private List<CargoDto> cargoDtos;
    private List<PassengerDto> passengers;
    private String orderType = "CARGO";


    public CreateOrderRequest(long idClient, long tripId, String date, String moskow, String s, List<PassengerDto> passengers) {
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
