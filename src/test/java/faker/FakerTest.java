package faker;

import com.github.javafaker.Faker;

public class FakerTest {
    public static void main(String[] args) {
        //   Faker faker = new Faker(new Locale("ru"));
        Faker faker = new Faker();
        String fullName = faker.name().fullName();
        System.out.println(fullName);
    }
}
