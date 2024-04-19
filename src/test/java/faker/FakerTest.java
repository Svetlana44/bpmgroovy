package faker;

import com.github.javafaker.Faker;

import java.util.Locale;

public class FakerTest {
    public static void main(String[] args) {
        //   Faker faker = new Faker(new Locale("ru"));
        Faker faker = new Faker(new Locale("ru"));
        String fullName = faker.name().fullName();
        String address = faker.address().fullAddress();
        System.out.println(fullName + "\n  " + address);
    }
}
