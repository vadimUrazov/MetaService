package net.thumbtack.metasearchservice.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class CreateOrderDtoRequest {
    private String login;
    private long idPath;
    private String date;
    private  int price;
    private List<PassengerDtoRequest> passengers;
    private List<CargoDto> cargoDtos;
    private String orderType="PASS";

    public CreateOrderDtoRequest(String login, long idPath, String date, int price,
                                 List<PassengerDtoRequest> passengers,
                                 List<CargoDto> cargoDtos, String orderType) {
        if(orderType.equals("CARGO")){
            passengers = new ArrayList<>();
            this.cargoDtos = cargoDtos;
        }
        this.login = login;
        this.idPath = idPath;
        this.date = date;
        this.price = price;

        if(orderType.equals("PASS")){
        this.cargoDtos = new ArrayList<>();
        this.passengers = passengers;
        }
        this.orderType=orderType;
    }
    @JsonCreator
    public CreateOrderDtoRequest() {
    }
}
