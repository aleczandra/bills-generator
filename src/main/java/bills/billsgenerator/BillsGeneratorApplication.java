package bills.billsgenerator;

import java.util.List;

import bills.billsgenerator.producer.BillGenerator;
import bills.billsgenerator.utils.GeneratorUtil;
import model.avro.Bill;
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

	private GeneratorUtil util = new GeneratorUtil();


	@Override
	public void run(String... strings) throws Exception {

		List<Bill> bills = util.convertToBillsList(util.readBills("mock/producer1-bills.json"));
		//from this method we will try to simulate more bills generators that will write into the kafka queue
		billGenerator.setMockDataFile("mock/producer1-bills.json");
		Thread thread = new Thread(billGenerator, "BillGenerator1");
		thread.start();
		Thread.sleep(2000);
		billGenerator.setMockDataFile("mock/producer2-bills.json");
		Thread thread2 = new Thread(billGenerator, "BillGenerator2");
		thread2.start();
		Thread.sleep(2000);
		billGenerator.setMockDataFile("mock/producer3-bills.json");
		Thread thread3 = new Thread(billGenerator, "BillGenerator3");
		thread3.start();
	}

	public static void main(String[] args) {
		SpringApplication.run(BillsGeneratorApplication.class, args);
	}
}
