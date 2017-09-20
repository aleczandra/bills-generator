package bills.billsgenerator.producer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bills.billsgenerator.utils.GeneratorUtil;
import model.avro.Bill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

public class BillGenerator implements Runnable {


    private static final Logger LOGGER = LoggerFactory.getLogger(BillGenerator.class);
    @Value("${kafka.topic.billstopic}")
    private String BILLS_TOPIC;
    private String mockDataFile;

    @Autowired
    private KafkaTemplate<String, Bill> kafkaTemplate;

    public void send(String topic, Bill payload) {
        LOGGER.info("sending payload='{}' to topic='{}'", payload.toString(), topic);
        kafkaTemplate.send(topic, payload);
    }

    @Override
    public void run() {

        GeneratorUtil utils = new GeneratorUtil();
        List<Bill> bills = new ArrayList<>();
        try {
            bills = utils.convertToBillsList(utils.readBills(mockDataFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Bill b : bills) {
            System.out.println("I am generator " + Thread.currentThread().getName() + " and  I send to kafka " + b.toString());
            kafkaTemplate.send(BILLS_TOPIC, b);
        }
    }

    public void setMockDataFile(String mockDataFile) {
        this.mockDataFile = mockDataFile;
    }
}
