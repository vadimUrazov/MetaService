package net.thumbtack.metasearchservice.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class CreateOrderRequest {
    private long idOrder;
    private long idClient;
    private String fromStation;
    private String toStation;
    private String date;
    private  int price;
    private List<PassengerDto> passengers;
    private List<CargoDto> cargoDtos;
    private String orderType = "CARGO";
    public CreateOrderRequest(long idOrder,long idClient, String date, String fromStation, String toStation, int price,
                              List<PassengerDto> passengers, List<CargoDto> cargoDtos, String orderType) {
      this.idOrder=idOrder;
        if(orderType.equals("CARGO")){
            passengers = new ArrayList<>();
            this.cargoDtos = cargoDtos;
        }
        this.idClient = idClient;
        this.date = date;
        this.price = price;
        this.fromStation=fromStation;
        this.toStation=toStation;
        if(orderType.equals("PASS")){
            this.cargoDtos = new ArrayList<>();
            this.passengers = passengers;
        }
        this.orderType=orderType;
    }

    @JsonCreator
    public CreateOrderRequest() {
    }
}
