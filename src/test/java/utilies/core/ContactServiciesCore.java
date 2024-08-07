package utilies.core;

import com.github.javafaker.Faker;
import com.github.javaparser.utils.Log;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import responsparser.IDparser;
import utilies.Auth;
import utilies.frame.AccountServiceFrame;
import version_1_3.api.jsonschemas.FullContact;
import version_1_3.api.jsonschemas.IDContact;
import version_1_3.api.jsonschemas.IDandNameContact;
import version_1_3.api.models.Contact;

import java.util.List;
import java.util.Locale;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public class ContactServiciesCore {
    private static final Logger LOG = LoggerFactory.getLogger(ContactServiciesCore.class.getName());
    private static final Faker faker = new Faker(new Locale("ru"));
    private static final Random random = new Random();
    public static String IDandNameJsonSchema = IDandNameContact.IDandNameJsonSchema;

    public static void main(String[] args) {
        Auth auth = new Auth();
//        auth.authHttpORHttps("urlwincore");

        //     parsIdFromIdResponse(ContactServicies.getAllIdOfContacts(auth)).forEach(System.out::println);
        addRandomContact(auth, generateRandomFullContact(auth));
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
    public static Contact generateRandomFullContact(Auth auth) {
        int randomNumber = Math.abs(random.nextInt(333));

        List<String> ownerIDs = IDparser.parsIdFromIdResponseToList(ContactServiciesCore.getAllIdOfContacts(auth));
        Log.info("Спарсили ownerIDs===========");
        List<String> accountIDs = IDparser.parsIdFromIdResponseToList(AccountServiceFrame.getAllIdOfAccountsCore(auth));
        Log.info("Спарсили accountIDs===========");
        int randomNumberIdOwner = Math.abs(random.nextInt(ownerIDs.size() - 1));
        System.out.println("OwnerIds=========" + ownerIDs.get(randomNumberIdOwner));
        int randomNumberIdAccount = 0;
        if (accountIDs.size() > 1) {
            randomNumberIdAccount = Math.abs(random.nextInt(accountIDs.size() - 1));
        }
        String name = faker.name().fullName();
        return Contact.builder()
                .name(name + randomNumber)
                .ownerId(ownerIDs.get(randomNumberIdOwner))
                .accountId(accountIDs.get(randomNumberIdAccount))
                .email(name.replaceAll("\\s", "") + randomNumber + "@test.ru")
                .build();
    }


    @Step("Core Добавление нового рандомного Контакта")
    public static Response addRandomContact(Auth auth, Contact contact) {
        //     Contact contact = Contact.generateRandomContact();

        auth.authHttpORHttps("urlwincore");

        return given()
                //          .relaxedHTTPSValidation()  //отключение проверки сертификатов https
                .when()
                //   .header("ForceUseSession", "true")
                .header("Accept", "application/json;odata=verbose")
                .header("BPMCSRF", auth.cookiesMap.get("BPMCSRF"))
                .header("Cookie", auth.cookiesString)
                .contentType(ContentType.JSON)
                .body(contact)
                .baseUri(auth.selectUrl("urlwincore"))
                .post("/odata/Contact")
                .then().log().all()
                .statusCode(201)
                .extract().response();
    }

    @Step("Core Нахождение Контакта по Id")
    public static Response getContactById(Auth auth, String id) {
        auth.authHttpORHttps("urlwincore");
        LOG.info("Core Нахождение Контакта по Id");
        return
                given()
                        .when()
                        //   .header("ForceUseSession", "true")
                        .header("Accept", "application/json;odata=verbose")
                        .header("BPMCSRF", auth.cookiesMap.get("BPMCSRF"))
                        .header("Cookie", auth.cookiesString)

                        .baseUri(auth.selectUrl("urlwincore"))
                        /*     {{BaseURI}}/0/odata/{{CollectionName1}}({{ObjectId1}})/{{FieldName1}}/$value */
                        .get("/odata/Contact(" + id + ")")
                        .then().log().all()
                        .statusCode(200)
                        .extract().response();
                        /* не забыть обрезать пробел  (ZWNBSP)  str.replace("\uFEFF", "").replace("\u200B", "")
                        , чтобы удалить все невидимые пробелы из ответа */
    }

    @Step("Core Запрос отсутствующего Контакта по Id")
    public static Response getContactByIdNegative(Auth auth, String id) {
        auth.authHttpORHttps("urlwincore");

        return
                given()
                        .when()
                        //   .header("ForceUseSession", "true")
                        .header("Accept", "application/json;odata=verbose")
                        .header("BPMCSRF", auth.cookiesMap.get("BPMCSRF"))
                        .header("Cookie", auth.cookiesString)

                        .baseUri(auth.selectUrl("urlwincore"))
                        /*     {{BaseURI}}/0/odata/{{CollectionName1}}({{ObjectId1}})/{{FieldName1}}/$value */
                        .get("/odata/Contact(" + id + ")")
                        .then().log().all()
                        .statusCode(404)
                        .extract().response();
                        /* не забыть обрезать пробел  (ZWNBSP)  str.replace("\uFEFF", "").replace("\u200B", "")
                        , чтобы удалить все невидимые пробелы из ответа */
    }

    @Step("Core Нахождение Name по Id")
    public static Response getNameOfContactById(Auth auth, String id) {
        auth.authHttpORHttps("urlwincore");

        return
                given()
                        .when()
                        //   .header("ForceUseSession", "true")
                        .header("Accept", "application/json;odata=verbose")
                        .header("BPMCSRF", auth.cookiesMap.get("BPMCSRF"))
                        .header("Cookie", auth.cookiesString)

                        .baseUri(auth.selectUrl("urlwincore"))
                        /*     {{BaseURI}}/0/odata/{{CollectionName1}}({{ObjectId1}})/{{FieldName1}}/$value */
                        .get("/odata/Contact(" + id + ")/Name/$value")
                        .then().log().all()
                        .statusCode(200)
                        .extract().response();
                        /* не забыть обрезать пробел  (ZWNBSP)  StringUtils.strip(response) или str.strip();
                        метод `strip` из `commons-lang3` библиотеки, чтобы удалить все невидимые пробелы из ответа */
    }

    @Step("Core Получение всех контактов с проверкой соответствия json schema")
    public static Response getAllContacts(Auth auth) {

        auth.authHttpORHttps("urlwincore");

        return given()
                .when()
                //   .header("ForceUseSession", "true")
                .header("Accept", "application/json;odata=verbose")
                .header("BPMCSRF", auth.cookiesMap.get("BPMCSRF"))
                .header("Cookie", auth.cookiesString)
                .contentType(ContentType.JSON)
                .baseUri(auth.selectUrl("urlwincore"))
                .get("/odata/Contact")
                .then().log().all()
                .statusCode(200)
                .assertThat()
                .body(matchesJsonSchema(FullContact.FullContactSchema))
                .extract().response();
    }

    @Step("Core Получение ID всех контактов с проверкой соответствия json schema")
    public static Response getAllIdOfContacts(Auth auth) {

        auth.authHttpORHttps("urlwincore");
        LOG.info("Core Получение ID всех контактов с проверкой соответствия json schema:" + auth.selectUrl("urlwincore"));
        return given()
                .when()
                //   .header("ForceUseSession", "true")
                .header("Accept", "application/json;odata=verbose")
                .header("BPMCSRF", auth.cookiesMap.get("BPMCSRF"))
                .header("Cookie", auth.cookiesString)
                .contentType(ContentType.JSON)
                .baseUri(auth.selectUrl("urlwincore"))
                .get("/odata/Contact?$select=Id")
                .then().log().all()
                .statusCode(200)
                .assertThat()
                .body(matchesJsonSchema(IDContact.IDContactSchema))
                .extract().response();
    }


    /* {{url}}/0/odata/Contact(410006e1-ca4e-4502-a9ec-e54d922d2c00) Supervisor */
    @Step("Core Удаление контакта")
    public static Response deleteContacts(Auth auth, String id) {

        auth.authHttpORHttps("urlwincore");

        return given()
                .when()
                //   .header("ForceUseSession", "true")
                .header("Accept", "application/json;odata=verbose")
                .header("BPMCSRF", auth.cookiesMap.get("BPMCSRF"))
                .header("Cookie", auth.cookiesString)
                .contentType(ContentType.JSON)
                .baseUri(auth.selectUrl("urlwincore"))
                .delete("/odata/Contact(" + id + ")")
                .then().log().all()
                .statusCode(204)
                .extract().response();
    }

    @Step("Core Удаление отсутствующего контакта")
    public static Response deleteContactNegative(Auth auth, String id) {

        auth.authHttpORHttps("urlwincore");

        return given()
                .when()
                //   .header("ForceUseSession", "true")
                .header("Accept", "application/json;odata=verbose")
                .header("BPMCSRF", auth.cookiesMap.get("BPMCSRF"))
                .header("Cookie", auth.cookiesString)
                .contentType(ContentType.JSON)
                .baseUri(auth.selectUrl("urlwincore"))
                .delete("/odata/Contact(" + id + ")")
                .then().log().all()
                .statusCode(404)
                .extract().response();
    }
}
