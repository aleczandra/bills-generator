package bills.billsgenerator.config;

import java.util.HashMap;
import java.util.Map;

import bills.billsgenerator.producer.BillGenerator;
import bills.billsgenerator.utils.AvroSerializer;
import model.avro.Bill;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

/**
 * This class will contain configuration for the producer. Configurations are mapped from application.yml.
 */
@Configuration
public class BillGeneratorConfig {

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        // list of host:port pairs used for establishing the initial connections to the Kakfa cluster
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, AvroSerializer.class);

        return props;
    }

    @Bean
    public ProducerFactory<String, Bill> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, Bill> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public BillGenerator getBillGenerator() {
        return new BillGenerator();
    }
}
