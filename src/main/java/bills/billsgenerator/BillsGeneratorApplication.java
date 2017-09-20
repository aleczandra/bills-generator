package bills.billsgenerator;

import bills.billsgenerator.producer.BillGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"bills.billsgenerator"})
public class BillsGeneratorApplication implements CommandLineRunner {

	@Value("${kafka.topic.billstopic}")
	private String BILLS_TOPIC;


	@Autowired
	private BillGenerator billGenerator;


	@Override
	public void run(String... strings) throws Exception {
		//from this method we will try to simulate more bills generators that will write into the kafka queue
		System.out.println("This is the application started");
		Thread thread = new Thread(billGenerator, "BillGenerator1");
		thread.start();
		Thread thread2 = new Thread(billGenerator, "BillGenerator2");
		thread2.start();
	}

	public static void main(String[] args) {
		SpringApplication.run(BillsGeneratorApplication.class, args);
	}
}
