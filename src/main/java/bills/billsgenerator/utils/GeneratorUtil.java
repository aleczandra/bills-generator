package bills.billsgenerator.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import model.avro.Bill;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class GeneratorUtil {


    ObjectMapper mapper = new ObjectMapper();

    public String readBills (String file) throws IOException {
        String path = getClass().getClassLoader().getResource(file).getPath();
        return new String(Files.readAllBytes(Paths.get(path)));
    }

    public List<Bill> convertToBillsList(String bills) throws IOException {
        return mapper.readValue(bills, new TypeReference<List<Bill>>(){});

    }





}
