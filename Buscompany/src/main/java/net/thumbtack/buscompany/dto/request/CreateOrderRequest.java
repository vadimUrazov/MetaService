package net.thumbtack.buscompany.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Data;
import net.thumbtack.buscompany.validate.Date;

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

    @JsonCreator
    public CreateOrderRequest() {
    }
}
