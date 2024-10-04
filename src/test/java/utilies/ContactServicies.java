package utilies;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import version_1_3.api.jsonschemas.FullContact;
import version_1_3.api.jsonschemas.IDContact;
import version_1_3.api.jsonschemas.IDandNameContact;
import version_1_3.api.models.Contact;
import version_1_3.api.models.IDs;

import java.util.Locale;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public class ContactServicies {
    private static final Random random = new Random();
    static int randomNumber = Math.abs(random.nextInt(333));
    private static final Logger LOG = LoggerFactory.getLogger(ContactServicies.class.getName());
    private static final Faker faker = new Faker(new Locale("ru"));

    public static String IDandNameJsonSchema = IDandNameContact.IDandNameJsonSchema;
    static IDs ids;
    private static boolean flagGetIDs = false;

    public static void main(String[] args) {
        Auth auth1 = new Auth();
        Auth auth2 = new Auth();
//        auth.authHttpORHttps("urlframework");
//             getAllIdOfContacts(auth,"urlframework");

        for (int i = 0; i < 1; i++) {
            //         ContactServicies.addRandomContact(auth1, "urlframework", generateRandomFullContact(auth1, "urlframework"));
            ContactServicies.addRandomContact(auth2, "urlwincore", generateRandomFullContact(auth2, "urlwincore"));
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
        if (!flagGetIDs) {
            ids = new IDs(auth, typeUrl);
            flagGetIDs = true;
        }
        String name = faker.name().fullName();
        LOG.info(typeUrl + "==> Генерация рандомного Полного контакта");
        return Contact.builder()
                .name("SVET_" + name + randomNumber)
                .ownerId(ids.ownerIDs.get(ids.getRandomNumberId(ids.ownerIDs)))
                .accountId(ids.accountIDs.get(ids.getRandomNumberId(ids.accountIDs)))
                .typeId(ids.contactTypeIDs.get(ids.getRandomNumberId(ids.contactTypeIDs)))
                .email(name.replaceAll("\\s", "") + randomNumber + "@test.ru")
                .mobilePhone(faker.phoneNumber().phoneNumber())
                .countryId(ids.countryIDs.get(ids.getRandomNumberId(ids.countryIDs)))
                .build();
    }

    @Step("Добавление нового рандомного Контакта")
    public static Response addRandomContact(Auth auth, String typeUrl, Contact contact) {
        //      auth.authHttpORHttps("urlwincore");
        //      auth.authHttpORHttps("urlframework");

        auth.authHttpORHttps(typeUrl);
        String requestPath = "/odata";
        if (typeUrl.equals("urlframework")) {
            requestPath = "/0" + requestPath;
        }
        LOG.info(typeUrl + " ==> Добавление Контакта + /n"
                + contact + "//n");
        return given()
                .relaxedHTTPSValidation()  //отключение проверки сертификатов https
                .when()
                .header("ForceUseSession", "true")
                //            .header("Accept", "application/json;odata=verbose")
                .header("BPMCSRF", auth.cookiesMap.get("BPMCSRF"))
                .header("Cookie", auth.cookiesString)
                //.header("Cookie", auth.cookiesMap.get(".ASPXAUTH"))
                // 500 .header("Cookie", ".ASPXAUTH=CE145F778919033192C7A5D2FA3EF47E96629FEDFAF59D53A4AEDCDB9033DCBB8B2401B39D80BC16BA3AB5ADED03FC22BB12BF108B6EC28FC954F662746BE435D64A6B3BF8500310A05AE28B5569793A69D86EFA040A4D1B6EBF94CA7F6B99068743DD7147A6EEC3599F642161D9F64B2567C14468EB39BC09E187D218C96E19C4FD22E71553192F73F886E347E49F2EE527CF4DC495A113C96A0B3BA435620D850D272BA0F2ECFF11A82E60A3BF25C1DCD5EF53771AB95E341D970A0B5A533CB3C166DA6FD072B4C55D2196BE95F6E715EDB7F99410247F853ABFA9D40639F99C0173A8F50E7BF4E45AB6F618F24CB6524F72D298CE5DAF1466FB985D8BAE307BA0DF275B091FA3E9C91152DE5E771A8A20D653C028B213862DE370C5C5A473251F09367F789EA1A2762FA74AEE9F9F204E02D6C3289AD262460ED6132B4B43508621D4714C2FABAE2539540592C4462C209A2C5A3F3CEED291F3F3ECBC4542D4208C9D82980E9CAB4AEEBE9D8B77DA770FDAEC2F209C1031229E690CDBB926A5FA4B97184B2F217CAA84594C8A25A282E1AAA168970BF2019DD8D25A844FF94762D06E945AB061C1573D7B56826077EED8349652F34AE99A776FCE9640D8EB24CBF1C4F210A8BD9F45E76037E1262482F5DF10CF0A0869C52EC6E64E7056FCF9BFE71D3B6CB0A8638C5127FE8DCABA500DD345; BPMCSRF=jjZVd3aGq19rJdaTVTBUa.; BPMLOADER=u1j2vi4teop31jbswlowh0mf; UserName=83|117|112|101|114|118|105|115|111|114")
                .contentType(ContentType.JSON)
                .body(contact)
                .baseUri(auth.selectUrl(typeUrl))
                .post(requestPath + "/Contact")
                .then().log().all()
                .statusCode(201)
                .extract().response();
    }

    @Step("Нахождение Контакта по Id")
    public static Response getContactById(Auth auth, String typeUrl, String id) {
        auth.authHttpORHttps(typeUrl);
        String requestPath = "/odata";
        if (typeUrl.equals("urlframework")) {
            requestPath = "/0" + requestPath;
        }
        LOG.info(typeUrl + "Нахождение Контакта по Id");
        return
                given()
                        .when()
                        //   .header("ForceUseSession", "true")
                        .header("Accept", "application/json;odata=verbose")
                        .header("BPMCSRF", auth.cookiesMap.get("BPMCSRF"))
                        .header("Cookie", auth.cookiesString)

                        .baseUri(auth.selectUrl(typeUrl))
                        /*     {{BaseURI}}/0/odata/{{CollectionName1}}({{ObjectId1}})/{{FieldName1}}/$value */
                        .get(requestPath + "/Contact(" + id + ")")
                        .then().log().all()
                        .statusCode(200)
                        .extract().response();
                        /* не забыть обрезать пробел  (ZWNBSP)  str.replace("\uFEFF", "").replace("\u200B", "")
                        , чтобы удалить все невидимые пробелы из ответа */
    }

    @Step("Запрос отсутствующего Контакта по Id")
    public static Response getContactByIdNegative(Auth auth, String typeUrl, String id) {
        auth.authHttpORHttps(typeUrl);
        String requestPath = "/odata";
        if (typeUrl.equals("urlframework")) {
            requestPath = "/0" + requestPath;
        }
        LOG.info(typeUrl + "Запрос отсутствующего Контакта по Id");
        return
                given()
                        .when()
                        //   .header("ForceUseSession", "true")
                        .header("Accept", "application/json;odata=verbose")
                        .header("BPMCSRF", auth.cookiesMap.get("BPMCSRF"))
                        .header("Cookie", auth.cookiesString)

                        .baseUri(auth.selectUrl(typeUrl))
                        /*     {{BaseURI}}/0/odata/{{CollectionName1}}({{ObjectId1}})/{{FieldName1}}/$value */
                        .get(requestPath + "/Contact(" + id + ")")
                        .then().log().all()
                        .statusCode(404)
                        .extract().response();
                        /* не забыть обрезать пробел  (ZWNBSP)  str.replace("\uFEFF", "").replace("\u200B", "")
                        , чтобы удалить все невидимые пробелы из ответа */
    }

    @Step("Нахождение Name по Id")
    public static Response getNameOfContactById(Auth auth, String typeUrl, String id) {
        auth.authHttpORHttps(typeUrl);
        String requestPath = "/odata";
        if (typeUrl.equals("urlframework")) {
            requestPath = "/0" + requestPath;
        }
        LOG.info(typeUrl + "Нахождение Name по Id");
        return
                given()
                        .when()
                        //   .header("ForceUseSession", "true")
                        .header("Accept", "application/json;odata=verbose")
                        .header("BPMCSRF", auth.cookiesMap.get("BPMCSRF"))
                        .header("Cookie", auth.cookiesString)

                        .baseUri(auth.selectUrl(typeUrl))
                        /*     {{BaseURI}}/0/odata/{{CollectionName1}}({{ObjectId1}})/{{FieldName1}}/$value */
                        .get(requestPath + "/Contact(" + id + ")/Name/$value")
                        .then().log().all()
                        .statusCode(200)
                        .extract().response();
                        /* не забыть обрезать пробел  (ZWNBSP)  StringUtils.strip(response) или str.strip();
                        метод `strip` из `commons-lang3` библиотеки, чтобы удалить все невидимые пробелы из ответа */
    }

    @Step("Получение всех контактов с проверкой соответствия json schema")
    public static Response getAllContacts(Auth auth, String typeUrl) {

        auth.authHttpORHttps(typeUrl);
        String requestPath = "/odata";
        if (typeUrl.equals("urlframework")) {
            requestPath = "/0" + requestPath;
        }
        LOG.info(typeUrl + " Получение всех контактов с проверкой соответствия json schema");
        return given()
                .when()
                //   .header("ForceUseSession", "true")
                .header("Accept", "application/json;odata=verbose")
                .header("BPMCSRF", auth.cookiesMap.get("BPMCSRF"))
                .header("Cookie", auth.cookiesString)
                .contentType(ContentType.JSON)
                .baseUri(auth.selectUrl(typeUrl))
                .get(requestPath + "/Contact")
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
        String requestPath = "/odata";
        if (typeUrl.equals("urlframework")) {
            requestPath = "/0" + requestPath;
        }
        LOG.info(typeUrl + "Получение ID всех контактов с проверкой соответствия json schema");
        return given()
                .when()
                //   .header("ForceUseSession", "true")
                .header("Accept", "application/json;odata=verbose")
                .header("BPMCSRF", auth.cookiesMap.get("BPMCSRF"))
                .header("Cookie", auth.cookiesString)
                .contentType(ContentType.JSON)
                .baseUri(auth.selectUrl(typeUrl))
                .get(requestPath + "/Contact?$select=Id")
                .then().log().all()
                .statusCode(200)
                .assertThat()
                .body(matchesJsonSchema(IDContact.IDContactSchema))
                .extract().response();
    }

    /* {{url}}/0/odata/Contact(410006e1-ca4e-4502-a9ec-e54d922d2c00) Supervisor */
    @Step("Удаление контакта")
    public static Response deleteContacts(Auth auth, String typeUrl, String id) {

        auth.authHttpORHttps(typeUrl);
        String requestPath = "/odata";
        if (typeUrl.equals("urlframework")) {
            requestPath = "/0" + requestPath;
        }
        LOG.info(typeUrl + "Удаление контакта");
        return given()
                .when()
                //   .header("ForceUseSession", "true")
                .header("Accept", "application/json;odata=verbose")
                .header("BPMCSRF", auth.cookiesMap.get("BPMCSRF"))
                .header("Cookie", auth.cookiesString)
                .contentType(ContentType.JSON)
                .baseUri(auth.selectUrl(typeUrl))
                .delete(requestPath + "/Contact(" + id + ")")
                .then().log().all()
                .statusCode(204)
                .extract().response();
    }

    @Step("Удаление отсутствующего контакта")
    public static Response deleteContactNegative(Auth auth, String typeUrl, String id) {

        auth.authHttpORHttps(typeUrl);
        String requestPath = "/odata";
        if (typeUrl.equals("urlframework")) {
            requestPath = "/0" + requestPath;
        }
        LOG.info(typeUrl + "Удаление отсутствующего контакта");
        return given()
                .when()
                //   .header("ForceUseSession", "true")
                .header("Accept", "application/json;odata=verbose")
                .header("BPMCSRF", auth.cookiesMap.get("BPMCSRF"))
                .header("Cookie", auth.cookiesString)
                .contentType(ContentType.JSON)
                .baseUri(auth.selectUrl(typeUrl))
                .delete(requestPath + "/Contact(" + id + ")")
                .then().log().all()
                .statusCode(404)
                .extract().response();
    }
}
