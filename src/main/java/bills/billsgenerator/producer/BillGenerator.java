package bills.billsgenerator.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

public class BillGenerator implements Runnable {


    private static final Logger LOGGER = LoggerFactory.getLogger(BillGenerator.class);
    @Value("${kafka.topic.billstopic}")
    private String BILLS_TOPIC;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, String payload) {
        LOGGER.info("sending payload='{}' to topic='{}'", payload, topic);
        kafkaTemplate.send(topic, payload);
    }

    @Override
    public void run() {
        kafkaTemplate.send(BILLS_TOPIC,"text"  + Thread.currentThread().getName());
    }
}
