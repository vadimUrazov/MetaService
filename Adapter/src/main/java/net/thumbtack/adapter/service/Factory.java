package net.thumbtack.adapter.service;

import lombok.RequiredArgsConstructor;
import net.thumbtack.adapter.dto.cities.GetCitiesBusDto;
import net.thumbtack.adapter.dto.cities.GetCitiesTrainDto;
import net.thumbtack.adapter.dto.GetTripsDto;
import net.thumbtack.adapter.mapper.ConvertProvider;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class Factory {

    private  final List<Delivery> beanList;

    private Delivery<GetCitiesBusDto> fromBus = new BusService();

    private Delivery<GetCitiesTrainDto> fromTrain = new TrainService();

    public GetTripsDto getTripsDelivery(String fromStation, String toStation) {
        GetTripsDto getTripsDto = new GetTripsDto();

        for (Delivery delivery : beanList) {
            var buf = CompletableFuture.supplyAsync(() -> delivery.send(fromStation, toStation));
            CompletableFuture.allOf(buf).join();
        }





        return getTripsDto;
    }

}
