package net.thumbtack.adapter.config;

import net.thumbtack.adapter.dto.cities.GetCitiesBusDto;
import net.thumbtack.adapter.dto.cities.GetCitiesShipDto;
import net.thumbtack.adapter.dto.cities.GetCitiesTrainDto;
import net.thumbtack.adapter.service.delivery.BusDeliveryService;
import net.thumbtack.adapter.service.delivery.Delivery;
import net.thumbtack.adapter.service.delivery.ShipDeliveryService;
import net.thumbtack.adapter.service.delivery.TrainDeliveryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableRetry
@EnableAsync
public class AdapterConfig {

    @Bean
    @Qualifier("getBus")
    public Delivery<GetCitiesBusDto> getBus() {
        return new BusDeliveryService();
    }

    @Bean
    @Qualifier("getTrain")
    public Delivery<GetCitiesTrainDto> getTrain() {
        return new TrainDeliveryService();
    }

    @Bean
    @Qualifier("getShip")
    public Delivery<GetCitiesShipDto> getShip() {
        return new ShipDeliveryService();
    }

    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();

        FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
        fixedBackOffPolicy.setBackOffPeriod(100);
        retryTemplate.setBackOffPolicy(fixedBackOffPolicy);

        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(3);
        retryTemplate.setRetryPolicy(retryPolicy);

        retryTemplate.registerListener(new DefaultListenerSupport());
        return retryTemplate;
    }
}
