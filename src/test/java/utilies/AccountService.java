package utilies;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import version_1_3.api.jsonschemas.FullAccount;
import version_1_3.api.jsonschemas.IDContact;
import version_1_3.api.models.Account;
import version_1_3.api.models.IDs;

import java.util.Locale;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public class AccountService {
    private static final Logger LOG = LoggerFactory.getLogger(AccountService.class.getName());
    private static final Faker faker = new Faker(new Locale("ru"));
    private static final Random random = new Random();
    static int randomNumber = Math.abs(random.nextInt(333));

    static IDs ids;
    private static boolean flagGetIDs = false;

    //  AccountId
    @Step("Получение ID всех Компаний с проверкой соответствия json schema")
    public static Response getAllIdOfAccountsFrame(Auth auth, String typeUrl) {
        //       auth.authHttpORHttps("urlframework");
        auth.authHttpORHttps(typeUrl);
        LOG.info(typeUrl + "===> Получение ID всех Компаний с проверкой соответствия IDContactSchema");
        String requestPath = "/odata";
        if (typeUrl.equals("urlframework")) {
            requestPath = "/0" + requestPath;
        }
        return given()
                .when()
                //   .header("ForceUseSession", "true")
                .header("Accept", "application/json;odata=verbose")
                .header("BPMCSRF", auth.cookiesMap.get("BPMCSRF"))
                .header("Cookie", auth.cookiesString)
                .contentType(ContentType.JSON)
                .baseUri(auth.selectUrl(typeUrl))
                .get(requestPath + "/Account?$select=Id")
                .then().log().all()
                .statusCode(200)
                .assertThat()
                //схема не та, но подходит, можно скопировать схему в accountschema
                .body(matchesJsonSchema(IDContact.IDContactSchema))
                .extract().response();
    }

    @Step("CORE===> Получение ID всех Компаний с проверкой соответствия json schema")
    public static Response getAllIdOfAccountsCore(Auth auth) {
        auth.authHttpORHttps("urlwincore");
        LOG.info("CORE===> Получение ID всех Компаний с проверкой соответствия IDContactSchema");
        return given()
                .when()
                //   .header("ForceUseSession", "true")
                .header("Accept", "application/json;odata=verbose")
                .header("BPMCSRF", auth.cookiesMap.get("BPMCSRF"))
                .header("Cookie", auth.cookiesString)
                .contentType(ContentType.JSON)
                .baseUri(auth.selectUrl("urlwincore"))
                .get("/odata/Account?$select=Id")
                .then().log().all()
                .statusCode(200)
                .assertThat()
                .body(matchesJsonSchema(IDContact.IDContactSchema))
                .extract().response();
    }

    /*  сделать схему на компанию полностью, для этого нужен запрос-ответ  */
    @Step("Получение Компании по ID с проверкой соответствия json schema")
    public static Response getAccountByIdFrame(Auth auth, String typeUrl, String id) {
        auth.authHttpORHttps(typeUrl);
        LOG.info(typeUrl + "===> Получение Компании по ID с проверкой соответствия IDContactSchema");
        String requestPath = "/odata";
        if (typeUrl.equals("urlframework")) {
            requestPath = "/0" + requestPath;
        }
        return given()
                .when()
                //   .header("ForceUseSession", "true")
                .header("Accept", "application/json;odata=verbose")
                .header("BPMCSRF", auth.cookiesMap.get("BPMCSRF"))
                .header("Cookie", auth.cookiesString)
                .contentType(ContentType.JSON)
                .baseUri(auth.selectUrl(typeUrl))
                .get(requestPath + "/Account(" + id + ")")
                .then().log().all()
                .statusCode(200)
                .assertThat()
                .body(matchesJsonSchema(FullAccount.FullAccountSchema))
                .extract().response();
    }

    @Step("Генерация рандомного Полного Контрагента")
    /* нужно списки данных сделать общими на класс, чтобы быстрее делать несколько Контрагентов  класс IDs при инициализации запрашивает IDs */
    public static Account generateRandomFullAccount(Auth auth, String typeUrl) {
        /*     List<String> ownerIDs = IDparser.parsIdFromIdResponseToList(getAllIdOfContacts(auth, "urlframework"));    */

        if (!flagGetIDs) {
            ids = new IDs(auth, typeUrl);
            flagGetIDs = true;
        }
        String name = faker.company().name();
        return Account.builder()
                .name("SVET_" + name + randomNumber)
                .primaryContactId(ids.contactIDs.get(ids.getRandomNumberId(ids.contactIDs)))
                .ownerId(ids.ownerIDs.get(ids.getRandomNumberId(ids.ownerIDs)))
                .typeId(ids.accountTypeIDs.get(ids.getRandomNumberId(ids.accountTypeIDs)))
                .phone(faker.phoneNumber().phoneNumber())
                .countryId(ids.countryIDs.get(ids.getRandomNumberId(ids.countryIDs)))
                .build();
    }

    @Step("Добавление нового рандомного Контрагента")
    public static Response addRandomAccount(Auth auth, String typeUrl, Account account) {

        //     auth.authHttpORHttps("urlframework");
        auth.authHttpORHttps(typeUrl);
        String requestPath = "/odata";
        if (typeUrl.equals("urlframework")) {
            requestPath = "/0" + requestPath;
        }
        LOG.info(typeUrl + "==> Добавление нового рандомного Контрагента");
        return given()
                //          .relaxedHTTPSValidation()  //отключение проверки сертификатов https
                .when()
                //   .header("ForceUseSession", "true")
                .header("Accept", "application/json;odata=verbose")
                .header("BPMCSRF", auth.cookiesMap.get("BPMCSRF"))
                .header("Cookie", auth.cookiesString)
                .contentType(ContentType.JSON)
                .body(account)
                .baseUri(auth.selectUrl(typeUrl))
                .post(requestPath + "/Account")
                .then().log().all()
                .statusCode(201)
                .extract().response();
    }

    //  AccounTypetId
    @Step("Получение ID всех Типов компаний с проверкой соответствия json schema")
    public static Response getAllIdOfAccountsTypeFrame(Auth auth, String typeUrl) {
        //     auth.authHttpORHttps("urlframework");
        auth.authHttpORHttps(typeUrl);
        LOG.info(typeUrl + "==> Получение ID всех Типов компаний с проверкой соответствия IDContactSchema");
        String requestPath = "/odata";
        if (typeUrl.equals("urlframework")) {
            requestPath = "/0" + requestPath;
        }
        return given()
                .when()
                //   .header("ForceUseSession", "true")
                .header("Accept", "application/json;odata=verbose")
                .header("BPMCSRF", auth.cookiesMap.get("BPMCSRF"))
                .header("Cookie", auth.cookiesString)
                .contentType(ContentType.JSON)
                .baseUri(auth.selectUrl(typeUrl))
                .get(requestPath + "/AccountType?$select=Id")
                .then().log().all()
                .statusCode(200)
                .assertThat()
                //схема не та, но подходит, можно скопировать схему в accountschema
                .body(matchesJsonSchema(IDContact.IDContactSchema))
                .extract().response();
    }

    public static void main(String[] args) {
        Auth auth = new Auth();
        //        getAllIdOfAccountsFrame(auth);
        //  getAccountByIdFrame(auth, "E308B781-3C5B-4ECB-89EF-5C1ED4DA488E");
//        getAllIdOfAccountsTypeFrame(auth);

        for (int i = 0; i < 200; i++) {
            addRandomAccount(auth, "urlframework", generateRandomFullAccount(auth, "urlframework"));
        }
    }
}
