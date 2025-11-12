package trany.testpack;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import trany.models.People;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Tag("Jackson")
public class JacksonSerialzFileTests {

    @DisplayName("Десериализация из json и в string формата json")
    @SneakyThrows
    @Test
    public void JsonTest() {
        ObjectMapper objectMapper = new ObjectMapper();
        File json = new File("src/test/resources/tranyResources/StasJackson.json");
        File jsonList = new File("src/test/resources/tranyResources/Peoples.json");
        People stas = objectMapper.readValue(json, People.class);

        People stringPeople = new People("sasha", 20);
        String sasha = objectMapper.writeValueAsString(stringPeople);

        List<People> namesList = objectMapper.readValue(jsonList, new TypeReference<ArrayList<People>>() {
        });
        System.out.println(stas.name);
        System.out.println(sasha);
        System.out.println("+++++++++++++++++++++++++++ /n");
        namesList.forEach(name -> {
            System.out.println(name.toString());
        });
    }
}
