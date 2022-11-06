package net.thumbtack.adapter.config;

import net.thumbtack.adapter.dto.cities.GetCitiesBusDto;
import net.thumbtack.adapter.dto.cities.GetCitiesShipDto;
import net.thumbtack.adapter.dto.cities.GetCitiesTrainDto;
import net.thumbtack.adapter.service.BusService;
import net.thumbtack.adapter.service.Delivery;
import net.thumbtack.adapter.service.ShipService;
import net.thumbtack.adapter.service.TrainService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdapterConfig {

    @Bean
    @Qualifier("getBus")
    public Delivery<GetCitiesBusDto> getBus() {
        return new BusService();
    }

    @Bean
    @Qualifier("getTrain")
    public Delivery<GetCitiesTrainDto> getTrain() {
        return new TrainService();
    }

    @Bean
    @Qualifier("getShip")
    public Delivery<GetCitiesShipDto> getShip() {
        return new ShipService();
    }


}
