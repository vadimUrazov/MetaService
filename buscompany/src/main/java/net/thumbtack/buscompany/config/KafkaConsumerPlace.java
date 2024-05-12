package net.thumbtack.buscompany.config;

import net.thumbtack.buscompany.dto.request.ChoosePlacesRequest;
import net.thumbtack.buscompany.dto.request.CreateOrderRequest;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class KafkaConsumerPlace{

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerPlace.class);

    private CountDownLatch latch = new CountDownLatch(1);
    private ChoosePlacesRequest dtoRequest = null;

    @KafkaListener(topics = "getBus",groupId = "bus")
    public void receive(ConsumerRecord<?, ChoosePlacesRequest> consumerRecord) {
        LOGGER.info("received payload='{}'", consumerRecord.toString());
        setDtoRequest(consumerRecord.value());
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public ChoosePlacesRequest getDtoRequest() {
        return dtoRequest;
    }

    private void setDtoRequest(ChoosePlacesRequest dtoRequest) {
        this.dtoRequest = dtoRequest;
    }

}
