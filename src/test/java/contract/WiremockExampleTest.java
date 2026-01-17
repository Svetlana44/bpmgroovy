package contract;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * Пример использования Wiremock для создания заглушек (mocks/stubs)
 * 
 * Wiremock позволяет:
 * - Мокировать HTTP сервисы
 * - Изолировать тесты от внешних зависимостей
 * - Тестировать различные сценарии (успех, ошибки, таймауты)
 * - Записывать и воспроизводить запросы
 */
public class WiremockExampleTest {

    private WireMockServer wireMockServer;

    @BeforeEach
    void setUp() {
        // Запускаем Wiremock сервер на порту 8089
        wireMockServer = new WireMockServer(wireMockConfig().port(8089));
        wireMockServer.start();
        
        // Настраиваем Wiremock клиент для работы с сервером
        WireMock.configureFor("localhost", 8089);
        
        // Настраиваем RestAssured для работы с мок-сервером
        RestAssured.baseURI = "http://localhost:8089";
    }

    @AfterEach
    void tearDown() {
        wireMockServer.stop();
    }

    /**
     * Пример 1: Простая заглушка для GET запроса
     */
    @Test
    void testSimpleGetStub() {
        // Настраиваем заглушку: при GET запросе на /api/users/1 вернуть JSON
        stubFor(get(urlEqualTo("/api/users/1"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\n" +
                                "  \"id\": 1,\n" +
                                "  \"name\": \"John Doe\",\n" +
                                "  \"email\": \"john@example.com\"\n" +
                                "}")));

        // Выполняем запрос и проверяем ответ
        given()
                .when()
                .get("/api/users/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("name", equalTo("John Doe"));
    }

    /**
     * Пример 2: Заглушка для POST запроса с проверкой тела запроса
     */
    @Test
    void testPostStubWithBodyMatching() {
        // Настраиваем заглушку для POST с проверкой тела запроса
        stubFor(post(urlEqualTo("/api/users"))
                .withRequestBody(matchingJsonPath("$.name", WireMock.equalTo("Jane Doe")))
                .willReturn(aResponse()
                        .withStatus(201)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\n" +
                                "  \"id\": 2,\n" +
                                "  \"name\": \"Jane Doe\",\n" +
                                "  \"email\": \"jane@example.com\"\n" +
                                "}")));

        given()
                .contentType("application/json")
                .body("{\n" +
                        "  \"name\": \"Jane Doe\",\n" +
                        "  \"email\": \"jane@example.com\"\n" +
                        "}")
                .when()
                .post("/api/users")
                .then()
                .statusCode(201)
                .body("id", equalTo(2));
    }

    /**
     * Пример 3: Заглушка с различными ответами в зависимости от параметров
     */
    @Test
    void testStubWithQueryParameters() {
        // Заглушка для запроса с query параметрами
        stubFor(get(urlPathEqualTo("/api/users"))
                .withQueryParam("page", WireMock.equalTo("1"))
                .withQueryParam("size", WireMock.equalTo("10"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\n" +
                                "  \"page\": 1,\n" +
                                "  \"size\": 10,\n" +
                                "  \"total\": 100,\n" +
                                "  \"users\": [\n" +
                                "    {\"id\": 1, \"name\": \"User 1\"},\n" +
                                "    {\"id\": 2, \"name\": \"User 2\"}\n" +
                                "  ]\n" +
                                "}")));

        given()
                .queryParam("page", 1)
                .queryParam("size", 10)
                .when()
                .get("/api/users")
                .then()
                .statusCode(200)
                .body("page", equalTo(1))
                .body("size", equalTo(10));
    }

    /**
     * Пример 4: Заглушка для обработки ошибок (404, 500)
     */
    @Test
    void testErrorStub() {
        // Заглушка для 404 ошибки
        stubFor(get(urlEqualTo("/api/users/999"))
                .willReturn(aResponse()
                        .withStatus(404)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\n" +
                                "  \"error\": \"User not found\",\n" +
                                "  \"code\": 404\n" +
                                "}")));

        given()
                .when()
                .get("/api/users/999")
                .then()
                .statusCode(404)
                .body("error", equalTo("User not found"));
    }

    /**
     * Пример 5: Заглушка с задержкой (для тестирования таймаутов)
     */
    @Test
    void testStubWithDelay() {
        // Заглушка с задержкой 2 секунды
        stubFor(get(urlEqualTo("/api/slow-endpoint"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withFixedDelay(2000)  // Задержка в миллисекундах
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"message\": \"Delayed response\"}")));

        given()
                .when()
                .get("/api/slow-endpoint")
                .then()
                .statusCode(200)
                .body("message", equalTo("Delayed response"));
    }

    /**
     * Пример 6: Заглушка с проверкой заголовков
     */
    @Test
    void testStubWithHeaderMatching() {
        // Заглушка, которая проверяет наличие Authorization заголовка
        stubFor(get(urlEqualTo("/api/protected"))
                .withHeader("Authorization", matching("Bearer .+"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"message\": \"Authorized\"}")));

        given()
                .header("Authorization", "Bearer token123")
                .when()
                .get("/api/protected")
                .then()
                .statusCode(200)
                .body("message", equalTo("Authorized"));
    }

    /**
     * Пример 7: Проверка, что запрос был выполнен (верификация)
     */
    @Test
    void testRequestVerification() {
        stubFor(get(urlEqualTo("/api/users/1"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("{\"id\": 1}")));

        given()
                .when()
                .get("/api/users/1")
                .then()
                .statusCode(200);

        // Проверяем, что запрос был выполнен
        verify(getRequestedFor(urlEqualTo("/api/users/1")));
        
        // Проверяем количество запросов
        verify(1, getRequestedFor(urlEqualTo("/api/users/1")));
    }
}
