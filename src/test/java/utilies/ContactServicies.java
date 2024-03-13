package utilies;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import version_1_3.api.jsonschemas.FullContact;
import version_1_3.api.models.Contact;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public class ContactServicies {
    private static final Faker faker = new Faker();
    private static final Random random = new Random();
    public static String JsonSchema =
            """
                    {
                        "type": "object",
                        "properties": {
                            "@odata.context": {"type": "string"},
                            "value": {
                                "type": "array",
                                "items": {
                                    "type": "object",
                                    "properties": {
                                        "Id": {"type": "string"},
                                        "Name": {"type": "string"}
                                    },
                                    "required": ["Id", "Name"]
                                }
                            }
                        },
                        "required": ["@odata.context", "value"]
                    }""";

    public static void main(String[] args) {
        Auth auth = new Auth();
//        auth.authHttpORHttps("urlframework");

        //     ContactServicies.getNameOfContactById(auth, "410006e1-ca4e-4502-a9ec-e54d922d2c00");
        ContactServicies.getContactById(auth, "410006e1-ca4e-4502-a9ec-e54d922d2c00");
    }

    @Step("Генерация рандомного контакта")
    public static Contact GenerateRandomContact() {
        int randomNumber = Math.abs(random.nextInt());
        String name = faker.name().fullName();
        return Contact.builder()
                .name(name + randomNumber)
                .email(name.replaceAll("\\s", "") + randomNumber + "@test.ru")
                .build();
    }

    @Step("Добавление нового рандомного Контакта")
    public static Response addRandomContact(Auth auth) {
        Contact contact = ContactServicies.GenerateRandomContact();

        auth.authHttpORHttps("urlframework");

        return given()
                .when()
                //   .header("ForceUseSession", "true")
                .header("Accept", "application/json;odata=verbose")
                .header("BPMCSRF", auth.cookiesMap.get("BPMCSRF"))
                .header("Cookie", auth.cookiesString)
                .contentType(ContentType.JSON)
                .body(contact)
                .baseUri(auth.selectUrl("urlframework"))
                .post("/0/odata/Contact")
                .then().log().all()
                .statusCode(201)
                .extract().response();
    }

    @Step("Нахождение Контакта по Id")
    public static Response getContactById(Auth auth, String id) {
        auth.authHttpORHttps("urlframework");

        return
                given()
                        .when()
                        //   .header("ForceUseSession", "true")
                        .header("Accept", "application/json;odata=verbose")
                        .header("BPMCSRF", auth.cookiesMap.get("BPMCSRF"))
                        .header("Cookie", auth.cookiesString)

                        .baseUri(auth.selectUrl("urlframework"))
                        /*     {{BaseURI}}/0/odata/{{CollectionName1}}({{ObjectId1}})/{{FieldName1}}/$value */
                        .get("/0/odata/Contact(" + id + ")")
                        .then().log().all()
                        .statusCode(200)
                        .extract().response();
                        /* не забыть обрезать пробел  (ZWNBSP)  str.replace("\uFEFF", "").replace("\u200B", "")
                        , чтобы удалить все невидимые пробелы из ответа */
    }

    @Step("Нахождение Name по Id")
    public static Response getNameOfContactById(Auth auth, String id) {
        auth.authHttpORHttps("urlframework");

        return
                given()
                        .when()
                        //   .header("ForceUseSession", "true")
                        .header("Accept", "application/json;odata=verbose")
                        .header("BPMCSRF", auth.cookiesMap.get("BPMCSRF"))
                        .header("Cookie", auth.cookiesString)

                        .baseUri(auth.selectUrl("urlframework"))
                        /*     {{BaseURI}}/0/odata/{{CollectionName1}}({{ObjectId1}})/{{FieldName1}}/$value */
                        .get("/0/odata/Contact(" + id + ")/Name/$value")
                        .then().log().all()
                        .statusCode(200)
                        .extract().response();
                        /* не забыть обрезать пробел  (ZWNBSP)  StringUtils.strip(response) или str.strip();
                        метод `strip` из `commons-lang3` библиотеки, чтобы удалить все невидимые пробелы из ответа */
    }

    @Step("Получение всех контактов с проверкой соответствия json schema")
    public static Response getAllContacts(Auth auth) {

        auth.authHttpORHttps("urlframework");

        return given()
                .when()
                //   .header("ForceUseSession", "true")
                .header("Accept", "application/json;odata=verbose")
                .header("BPMCSRF", auth.cookiesMap.get("BPMCSRF"))
                .header("Cookie", auth.cookiesString)
                .contentType(ContentType.JSON)
                .baseUri(auth.selectUrl("urlframework"))
                .get("/0/odata/Contact")
                .then().log().all()
                .statusCode(200)
                .assertThat()
                .body(matchesJsonSchema(FullContact.FullContactSchema))
                .extract().response();
    }

}
