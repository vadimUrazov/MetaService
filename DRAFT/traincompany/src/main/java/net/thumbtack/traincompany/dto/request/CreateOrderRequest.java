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
    private List<PassengerDto> passengers;


    public CreateOrderRequest(long idClient, long tripId, String date, List<PassengerDto> passengers) {
        this.idClient = idClient;
        this.tripId = tripId;
        this.date = date;
        this.passengers = passengers;
    }

    @JsonCreator
    public CreateOrderRequest() {
    }

}
