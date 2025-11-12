package trany.testpack;

import api.models.Account;
import jdk.jfr.Description;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@Tag("PARAM")
public class TranyTests {

    /*   Параметры из метода   */
    private static Stream<Arguments> testObjs() {
        return Stream.of(Arguments.of(new Account()),
                Arguments.of(new Account()),
                Arguments.of(new Account()));
    }

    @Test
    @DisplayName("Отображение названия теста. Тест отключен.")
    // Внимание!!!  отключен
    @Disabled("russianTextTest Отключен. Запускается нажатием на треугольник." +
            "см. аннотации")
    public void russianTextTest() {
        String russ = "Привет. рашен. как ты покрашен.";
        String expected = "russ";
        Assertions.assertEquals(russ, expected, "Тут расположен ё текст для проверки русской раскладки.");
    }

    @ParameterizedTest
    @DisplayName("Параметризованный тест, параметры тут же, не из файла")
    @CsvSource({"OneT", "TwoT", "ThreeT"})
    public void paramsTest(String paramText) {
        System.out.println(paramText);
    }

    @ParameterizedTest
    @DisplayName("Параметризованный тест,  из файла, делиметр по умолчанию запятая")
    @Description("""
             / означает корневую директорию каталога ресурсов (src/test/resources).
            Вам нужно перенести этот файл в каталог ресурсов 
            (src/test/resources/myfolder/datafile.txt),
            чтобы сделать его доступным для загрузки через стандартные механизмы JUnit.
            """)
    @CsvFileSource(resources = "/tranyResources/params.csv")
    public void paramsFromFileTest(String paramText) {
        System.out.println(paramText);
    }

    @ParameterizedTest
    @MethodSource("testObjs")
    public void paramsFromMethodTest(Account paramText) {
        System.out.println(paramText.getName());
    }


}
