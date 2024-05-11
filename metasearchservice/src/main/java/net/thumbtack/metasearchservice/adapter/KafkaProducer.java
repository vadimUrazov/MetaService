package net.thumbtack.metasearchservice.adapter;

import net.thumbtack.metasearchservice.dto.request.ChoosePlaceRequest;
import net.thumbtack.metasearchservice.dto.request.CreateOrderRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, CreateOrderRequest> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, ChoosePlaceRequest> kafkaTemplatePlace;


    public void send(CreateOrderRequest request,String topic) {
        LOGGER.info("sending payload='{}' to topic='{}'", request, topic);
        kafkaTemplate.send(topic, request);
    }

    public void send(ChoosePlaceRequest request,String topic) {
        LOGGER.info("sending choose place payload='{}' to topic='{}'", request, topic);
        kafkaTemplatePlace.send(topic, request);
    }
}
