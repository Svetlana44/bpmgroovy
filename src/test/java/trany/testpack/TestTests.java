package trany.testpack;

import org.junit.jupiter.api.BeforeEach;

public class TestTests {
    public String user;

    public static void Test1(String user) {

    }

    public static void Test2(String user) {

    }

    public static void Test3(String user) {

    }

    @BeforeEach
    public void Beforr() {
        switch (user) {
            case "1":
                Test1(user);
                break;
            case "2":
                Test2(user);
                break;
            default:
                break;
        }
    }
}
