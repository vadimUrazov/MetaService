package net.thumbtack.adapter.service.delivery;

import lombok.RequiredArgsConstructor;
import net.thumbtack.adapter.dto.cities.GetCitiesBusDto;
import net.thumbtack.adapter.dto.cities.GetCitiesShipDto;
import net.thumbtack.adapter.dto.cities.GetCitiesTrainDto;
import net.thumbtack.adapter.dto.trips.GetTripsDto;
import net.thumbtack.adapter.mapper.ConvertProvider;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class DeliveryProvider {

    private final List<Delivery> beanList;


    public GetTripsDto getTripsDelivery(String fromStation, String toStation) throws Exception {
        GetTripsDto getTripsDto = new GetTripsDto();
        for (Delivery delivery : beanList) {
            var buf = CompletableFuture.supplyAsync(() -> delivery.send(fromStation, toStation));

            switch (delivery.getTypeCompany()) {
                case "Bus":
                    var buses = (GetCitiesBusDto) buf.get();
                    getTripsDto.getFromBuses().addAll(ConvertProvider.getTripsFromBus(buses));
                    getTripsDto.getToBuses().addAll(ConvertProvider.getTripsToBus(buses));
                    break;
                case "Train":
                    var trains = (GetCitiesTrainDto) buf.get();
                    getTripsDto.getFromTrains().addAll(ConvertProvider.getTripsFromTrain(trains));
                    getTripsDto.getToTrains().addAll(ConvertProvider.getTripsToTrain(trains));
                    break;
                case "Ship":
                    var ships = (GetCitiesShipDto) buf.get();
                    getTripsDto.getFromShips().addAll(ConvertProvider.getTripsFromShip(ships));
                    getTripsDto.getToShips().addAll(ConvertProvider.getTripsToShip(ships));
                    break;
            }
            CompletableFuture.allOf(buf).join();
        }

        return getTripsDto;
    }


}
