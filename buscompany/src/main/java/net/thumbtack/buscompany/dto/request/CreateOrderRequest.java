package net.thumbtack.buscompany.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Data;
import net.thumbtack.buscompany.validate.Date;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class CreateOrderRequest {
    private long idOrder;
    private long idClient;
    private String fromStation;
    private String toStation;
    @NotEmpty
    @Date
    private String date;
    private List<PassengerDto> passengers;
    private List<CargoDto> cargoDtos;
    private String orderType = "PASS";

    public CreateOrderRequest(long idClient, String date, String fromStation, String toStation,
                              List<PassengerDto> passengers,List<CargoDto> cargoDtos, String orderType) {
        this.idClient = idClient;
        this.date = date;
        this.fromStation = fromStation;
        this.toStation = toStation;
        if(orderType.equals("CARGO")){
            passengers = new ArrayList<>();
            this.cargoDtos = cargoDtos;
        }
        if(orderType.equals("PASS")){
            this.cargoDtos = new ArrayList<>();
            this.passengers = passengers;
        }
        this.orderType = orderType;
    }


    @JsonCreator
    public CreateOrderRequest() {
    }
}
