package net.thumbtack.metasearchservice.service;

import net.thumbtack.metasearchservice.adapter.UserProvider;
import net.thumbtack.metasearchservice.dto.PlaceDto;
import net.thumbtack.metasearchservice.dto.TripDto;
import net.thumbtack.metasearchservice.dto.request.*;
import net.thumbtack.metasearchservice.dto.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class UserService extends BaseService{
    @Autowired
    private UserProvider provider;
    private long generateId(){
        return new Random().ints(1, Integer.MAX_VALUE).
                distinct().limit(1).findAny().getAsInt();
    }
    private List<PassengerDto> convertPassengersRequest(List<PassengerDtoRequest> passengerDtos){
        List<PassengerDto> responseList=new ArrayList<>();
        for (PassengerDtoRequest p: passengerDtos){
            responseList.add(new PassengerDto(p.getFirstName(), p.getLastName(), p.getPassport()));
        }
        return responseList;
        }

    private List<PassengerDtoResponse> convertPassengers(List<PassengerDto> passengerDtos){
        List<PassengerDtoResponse> responseList=new ArrayList<>();
        for (PassengerDto p: passengerDtos){
            responseList.add(new PassengerDtoResponse(p.getFirstName(), p.getLastName(), p.getPassport()));
        }
        return responseList;
    }
    private List<CargoDtoResponse> convertCargos(List<CargoDto> cargosDtos){
        List<CargoDtoResponse> responseList=new ArrayList<>();
        for (CargoDto c: cargosDtos){
            responseList.add(new CargoDtoResponse(c.getCargoType(),c.getIdClient()));
        }
        return responseList;
    }
    public RegisterUserDtoResponse registerUser(RegisterClientDtoRequest request) throws Exception {
           request.setId(generateId());
        return provider.registerUser(request);
    }

    public LoginDtoResponse loginUser(LoginDtoRequest request) throws Exception {
        return provider.loginUser(request);
    }

    private boolean checkPlace(List<PassengerDtoRequest> passengerDtos,long idClient){
        for (PassengerDtoRequest pas: passengerDtos){
            PlaceDto pl=(PlaceDto) cacheManager.getCache("get_places").get(pas.getPlace());
            if(pl!=null && pl.getIdClient()!=idClient){
                return false;
            }
        }
        return true;
    }

    public synchronized CreateOrderDtoResponse createOrder(CreateOrderDtoRequest request) throws Exception {
        List<TripDto> path= (List<TripDto>) cacheManager.getCache("get_paths").get(request.getIdPath());
     var pass=convertPassengersRequest(request.getPassengers());
    var idOrder=generateId();
    if(!checkPlace(request.getPassengers(), request.getIdClient())){
        throw new IllegalArgumentException("This place is not free");
    }
       for (TripDto t: path){

           CreateOrderRequest orderRequest=new CreateOrderRequest(idOrder,
                   request.getIdClient(),t.getFromStation(),t.getToStation(),
                   request.getDate(),request.getPrice(),pass,request.getCargoDtos(),request.getOrderType());

             switch (t.getTransport()){
                 case "BUS": provider.createOrder(orderRequest,"getBus");break;
                 case "TRAIN": provider.createOrder(orderRequest,"getTrain");break;
                 case "SHIP": provider.createOrder(orderRequest,"getShip");break;
             }

       }
        CreateOrderDtoResponse response=new CreateOrderDtoResponse(idOrder,request.getIdClient(),request.getDate(),path,request.getPrice(),
                convertPassengers(pass), convertCargos(request.getCargoDtos()));
 for (PassengerDtoRequest p: request.getPassengers()){
   cacheManager.getCache("get_places").put(p.getPlace(),new PlaceDto(request.getIdClient(),p));
 }

        return response;
    }

    public  synchronized ChoosePlacesResponse choosePlace(ChoosePlaceDtoRequest request){
        List<TripDto> path= (List<TripDto>) cacheManager.getCache("get_paths").get(request.getIdPath());
     List<ChoosePlaceResponse> responses=new ArrayList<>();
        for (TripDto t: path){
            for (ChoosePlaceRequest r: request.getPlaces()){
            switch (t.getTransport()){
                case "BUS": provider.choosePlace(r,"getBus");break;
                case "TRAIN": provider.choosePlace(r,"getTrain");break;
                case "SHIP": provider.choosePlace(r,"getShip");break;
            }

        }
    }

      for (ChoosePlaceRequest r: request.getPlaces()){
          var ticket="Ticket"+r.getOrderId()+"_"+r.getPlace();
          responses.add(new ChoosePlaceResponse(r.getOrderId(),ticket,
                  r.getFirstName(),r.getLastName(),r.getPlace()));
      }
        ChoosePlacesResponse response=new ChoosePlacesResponse(responses);
return response;
}
}
