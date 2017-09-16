package bills.billsgenerator;

import bills.billsgenerator.config.SenderConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"bills.billsgenerator"})
public class BillsGeneratorApplication {

	public static void main(String[] args) {

		System.out.println(SenderConfig.bootstrapServers);
		SpringApplication.run(BillsGeneratorApplication.class, args);
	}
}
