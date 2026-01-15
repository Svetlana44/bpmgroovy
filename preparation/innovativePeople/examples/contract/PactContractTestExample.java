package examples.contract;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * Пример контрактного тестирования с использованием Pact
 * 
 * Контрактное тестирование проверяет совместимость между сервисами
 * через контракты (API контракты), не требуя запуска реальных сервисов.
 * 
 * Consumer (потребитель) - сервис, который использует API
 * Provider (поставщик) - сервис, который предоставляет API
 * 
 * См. также: ../../docs/PREPARATION_PLAN.md для подробной информации
 */
@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "UserService", port = "8080")
public class PactContractTestExample {

    /**
     * Вспомогательный метод для создания заголовков (совместимость с Java 8+)
     */
    private Map<String, String> createHeaders(String key, String value) {
        Map<String, String> headers = new HashMap<>();
        headers.put(key, value);
        return headers;
    }

    /**
     * Определение контракта между Consumer и Provider
     * 
     * Этот метод описывает ожидаемое взаимодействие:
     * - Consumer отправляет GET запрос на /api/users/1
     * - Provider должен вернуть статус 200 и JSON с данными пользователя
     */
    @Pact(consumer = "TestClient")
    public RequestResponsePact getUserPact(PactDslWithProvider builder) {
        return builder
                .given("user with id 1 exists")  // Состояние провайдера
                .uponReceiving("a request for user with id 1")
                .path("/api/users/1")
                .method("GET")
                .willRespondWith()
                .status(200)
                .headers(createHeaders("Content-Type", "application/json"))
                .body("{\n" +
                        "  \"id\": 1,\n" +
                        "  \"name\": \"John Doe\",\n" +
                        "  \"email\": \"john@example.com\"\n" +
                        "}")
                .toPact();
    }

    /**
     * Тест, который проверяет, что Consumer может работать с Provider
     * согласно определенному контракту
     */
    @Test
    @PactTestFor
    void testGetUser(MockServer mockServer) {
        // Настраиваем RestAssured для работы с мок-сервером Pact
        RestAssured.baseURI = mockServer.getUrl();

        // Выполняем запрос и проверяем ответ
        given()
                .when()
                .get("/api/users/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("name", equalTo("John Doe"))
                .body("email", equalTo("john@example.com"));
    }

    /**
     * Пример контракта для POST запроса
     */
    @Pact(consumer = "TestClient")
    public RequestResponsePact createUserPact(PactDslWithProvider builder) {
        return builder
                .given("no user exists")
                .uponReceiving("a request to create a user")
                .path("/api/users")
                .method("POST")
                .headers(createHeaders("Content-Type", "application/json"))
                .body("{\n" +
                        "  \"name\": \"Jane Doe\",\n" +
                        "  \"email\": \"jane@example.com\"\n" +
                        "}")
                .willRespondWith()
                .status(201)
                .headers(createHeaders("Content-Type", "application/json"))
                .body("{\n" +
                        "  \"id\": 2,\n" +
                        "  \"name\": \"Jane Doe\",\n" +
                        "  \"email\": \"jane@example.com\"\n" +
                        "}")
                .toPact();
    }

    @Test
    @PactTestFor
    void testCreateUser(MockServer mockServer) {
        RestAssured.baseURI = mockServer.getUrl();

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
                .body("id", equalTo(2))
                .body("name", equalTo("Jane Doe"));
    }
}
