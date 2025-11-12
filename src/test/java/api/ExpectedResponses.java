package api;

/**
 * Класс для хранения ожидаемых ответов от API.
 * Используется для централизованного хранения JSON-ответов, которые могут быть использованы в тестах.
 */
public class ExpectedResponses {

    /**
     * Ожидаемый успешный ответ от API аутентификации.
     * заменён restassured.response.Response response = given()
                .relaxedHTTPSValidation()  //отключение проверки сертификатов https
                .header("ForceUseSession", "true")
                .header("Content-Type", "application/json")
                .body(user)
                .baseUri(url)
                .post("/ServiceModel/AuthService.svc/Login")
                .then().log().all()
                .statusCode(200)
                .body("Code", equalTo(0))
                .body("Message", equalTo(""))
                .body("Exception", nullValue())
                .body("PasswordChangeUrl", nullValue())
                .body("RedirectUrl", nullValue())
                .extract().response(); в классах Auth, AuthCore и GetZipPackagesAPITests.
     */
    public static final String AUTH_SUCCESS_RESPONSE = """
            {
                "Code": 0,
                "Message": "",
                "Exception": null,
                "PasswordChangeUrl": null,
                "RedirectUrl": null
            }
            """;

    private ExpectedResponses() {
        // Приватный конструктор для предотвращения создания экземпляров класса
    }
}

