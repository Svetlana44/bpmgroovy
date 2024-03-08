package utilies;

import com.github.javafaker.Faker;
import version_1_3.api.models.Contact;

import java.util.Random;

public class GeneratorRandomData {

    private static final Faker faker = new Faker();
    private static final Random random = new Random();

    public static Contact GenerateRandomContact() {
        int randomNumber = Math.abs(random.nextInt());
        String name = faker.name().fullName();
        return Contact.builder()
                .name(name + randomNumber)
                .email(name + randomNumber + "@test.ru")
                .build();
    }
}
