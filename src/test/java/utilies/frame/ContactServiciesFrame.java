package utilies.frame;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilies.Auth;
import version_1_3.api.jsonschemas.FullContact;
import version_1_3.api.jsonschemas.IDContact;
import version_1_3.api.jsonschemas.IDandNameContact;
import version_1_3.api.models.Contact;
import version_1_3.api.models.IDs;

import java.util.Locale;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public class ContactServiciesFrame {
    private static final Random random = new Random();
    static int randomNumber = Math.abs(random.nextInt(333));
    private static final Logger LOG = LoggerFactory.getLogger(ContactServiciesFrame.class.getName());
    private static final Faker faker = new Faker(new Locale("ru"));

    public static String IDandNameJsonSchema = IDandNameContact.IDandNameJsonSchema;
    static IDs ids;
    private static boolean flagGetIDs = false;

    public static void main(String[] args) {
        Auth auth = new Auth();
//        auth.authHttpORHttps("urlframework");

        //     parsIdFromIdResponse(ContactServicies.getAllIdOfContacts(auth)).forEach(System.out::println);
        for (int i = 0; i < 10; i++) {
            ContactServiciesFrame.addRandomContact(auth, generateRandomFullContact(auth, "urlframework"));
        }
        //          ContactServicies.getNameOfContactById(auth, "410006e1-ca4e-4502-a9ec-e54d922d2c00");
        //      ContactServicies.getContactById(auth, "410006e1-ca4e-4502-a9ec-e54d922d2c00");
        //    ContactServicies.deleteContacts(auth, "3b0160df-cb97-42df-aa40-419f49ae9053");
    }


    @Step("Генерация рандомного контакта")
    public static Contact generateRandomSimpleContact() {
        int randomNumber = Math.abs(random.nextInt(333));
        String name = faker.name().fullName();
        return Contact.builder()
                .name(name + randomNumber)
                .email(name.replaceAll("\\s", "") + randomNumber + "@test.ru")
                .build();
    }

    @Step("Генерация рандомного Полного контакта")
    /* нужно списки данных сделать общими на класс, чтобы быстрее делать несколько контактов  класс IDs при инициализации запрашивает IDs */
    public static Contact generateRandomFullContact(Auth auth, String typeUrl) {
        /*     List<String> ownerIDs = IDparser.parsIdFromIdResponseToList(getAllIdOfContacts(auth, "urlframework"));    */

        if (!flagGetIDs) {
            ids = new IDs(auth, typeUrl);
            flagGetIDs = true;
        }
        String name = faker.name().fullName();
        return Contact.builder()
                .name(name + randomNumber)
                .ownerId(ids.ownerIDs.get(ids.randomNumberIdOwner))
                .accountId(ids.accountIDs.get(ids.randomNumberIdAccount))
                .typeId(ids.contactTypeIDs.get(ids.randomNumberIdContactType))
                .email(name.replaceAll("\\s", "") + randomNumber + "@test.ru")
                .mobilePhone(faker.phoneNumber().phoneNumber())
                .countryId(ids.countryIDs.get(ids.randomNumberIdCountry))
                .build();
    }

    @Step("Добавление нового рандомного Контакта")
    public static Response addRandomContact(Auth auth, Contact contact) {
        //     Contact contact = Contact.generateRandomContact();

        auth.authHttpORHttps("urlframework");

        return given()
                //          .relaxedHTTPSValidation()  //отключение проверки сертификатов https
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

    @Step("Запрос отсутствующего Контакта по Id")
    public static Response getContactByIdNegative(Auth auth, String id) {
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
                        .statusCode(404)
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

    @Step("Получение ID всех контактов с проверкой соответствия json schema")
    public static Response getAllIdOfContacts(Auth auth, String typeUrl) {
        /*  auth.authHttpORHttps("urlframework");  */
        auth.authHttpORHttps(typeUrl);
        LOG.info("Получение ID всех контактов с проверкой соответствия json schema");
        return given()
                .when()
                //   .header("ForceUseSession", "true")
                .header("Accept", "application/json;odata=verbose")
                .header("BPMCSRF", auth.cookiesMap.get("BPMCSRF"))
                .header("Cookie", auth.cookiesString)
                .contentType(ContentType.JSON)
                .baseUri(auth.selectUrl("urlframework"))
                .get("/0/odata/Contact?$select=Id")
                .then().log().all()
                .statusCode(200)
                .assertThat()
                .body(matchesJsonSchema(IDContact.IDContactSchema))
                .extract().response();
    }


    /* {{url}}/0/odata/Contact(410006e1-ca4e-4502-a9ec-e54d922d2c00) Supervisor */
    @Step("Удаление контакта")
    public static Response deleteContacts(Auth auth, String id) {

        auth.authHttpORHttps("urlframework");

        return given()
                .when()
                //   .header("ForceUseSession", "true")
                .header("Accept", "application/json;odata=verbose")
                .header("BPMCSRF", auth.cookiesMap.get("BPMCSRF"))
                .header("Cookie", auth.cookiesString)
                .contentType(ContentType.JSON)
                .baseUri(auth.selectUrl("urlframework"))
                .delete("/0/odata/Contact(" + id + ")")
                .then().log().all()
                .statusCode(204)
                .extract().response();
    }

    @Step("Удаление отсутствующего контакта")
    public static Response deleteContactNegative(Auth auth, String id) {

        auth.authHttpORHttps("urlframework");

        return given()
                .when()
                //   .header("ForceUseSession", "true")
                .header("Accept", "application/json;odata=verbose")
                .header("BPMCSRF", auth.cookiesMap.get("BPMCSRF"))
                .header("Cookie", auth.cookiesString)
                .contentType(ContentType.JSON)
                .baseUri(auth.selectUrl("urlframework"))
                .delete("/0/odata/Contact(" + id + ")")
                .then().log().all()
                .statusCode(404)
                .extract().response();
    }
}
