package net.thumbtack.traincompany.config;

import net.thumbtack.traincompany.dto.request.CreateOrderRequest;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class KafkaConsumerOrder {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerOrder.class);
    private CountDownLatch latch = new CountDownLatch(1);
    private CreateOrderRequest dtoRequest = null;

    @KafkaListener(topics = "getTrain",groupId = "train")
    public void receive(ConsumerRecord<?, CreateOrderRequest> consumerRecord) {
        LOGGER.info("received payload='{}'", consumerRecord.toString());
        setDtoRequest(consumerRecord.value());
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public CreateOrderRequest getDtoRequest() {
        return dtoRequest;
    }

    private void setDtoRequest(CreateOrderRequest dtoRequest) {
        this.dtoRequest = dtoRequest;
    }

}
