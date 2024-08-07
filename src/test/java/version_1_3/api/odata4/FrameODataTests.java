package version_1_3.api.odata4;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utilies.Auth;
import utilies.frame.ContactServiciesFrame;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utilies.frame.ContactServiciesFrame.IDandNameJsonSchema;

/*  только для .NET   в пути есть нуль
{{BaseURI}}/0/ServiceModel/EntityDataService.svc
а для Core должно быть тоже самое только без нуля
{{BaseURI}}/ServiceModel/EntityDataService.svc   */
public class FrameODataTests {
    Auth auth = new Auth();
    /* JsonSchema в RestAssured - это инструмент, позволяющий проверить,
     что возвращаемый JSON-ответ соответствует определенной схеме (JSON-схеме).
     JSON-схема определяет ожидаемую структуру данных в JSON-ответе, включая типы данных,
     обязательные и дополнительные поля, ограничения на значения и другие правила.
     RestAssured позволяет использовать JSON-схему для автоматической проверки соответствия JSON-ответа этой схеме,
     что помогает обеспечить правильность данных, возвращаемых из API.
     // https://mvnrepository.com/artifact/io.rest-assured/json-schema-validator
             implementation 'io.rest-assured:json-schema-validator:5.4.0'

     RestAssured.given()
                .contentType(ContentType.JSON)
                .get("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY")
                .then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(schema));   */


    @Test
    public void authFrame() {
        auth.authHttpORHttps("urlframework");
    }

    /*   {{BaseURI}}/0/odata/{{CollectionName1}}   */
    @Test
    public void getObjectCollectionInstancesPositive() {
        ContactServiciesFrame.getAllContacts(auth);
    }

    /*  {{BaseURI}}/0/odata/{{CollectionName1}}({{ObjectId1}})/{{FieldName1}}/$value
    $value - это специальный параметр,
     нужно получить значение конкретного поля (FieldName1) объекта (ObjectId1) из коллекции (CollectionName1)
     слово "value" не нужно ни чем заменять, писать как есть.   */
    @Test
    public void getNameOfContactById() {
        String name = ContactServiciesFrame
                .getNameOfContactById(auth, "410006e1-ca4e-4502-a9ec-e54d922d2c00")
                .body().asPrettyString()
                .replace("\uFEFF", "").replace("\u200B", "");
        Assertions.assertEquals("Supervisor", name);
    }

    @Test
    public void getObjectCollectionInstancesSelectParametrsPositiveShema() {
        auth.authHttpORHttps("urlframework");

        Response responseGet =
                given()
                        .when()
                        //   .header("ForceUseSession", "true")
                        .header("Accept", "application/json;odata=verbose")
                        .header("BPMCSRF", auth.cookiesMap.get("BPMCSRF"))
                        .header("Cookie", auth.cookiesString)

                        .queryParam("$select", "Id,Name")

                        .baseUri(auth.selectUrl("urlframework"))
                        /*     .get("/0/odata/Contact?%24select=Id%2CName")  */
                        .get("/0/odata/Contact")
                        .then().log().all()
                        .statusCode(200)
                        .assertThat()
                        .body(matchesJsonSchema(IDandNameJsonSchema))
                        .extract().response();
    }


    /* http://qa026wfmb.rnd.omnichannel.ru:8500/0/odata/Contact?$select=Id,Name&BPMCSRF={{BPMCSRF}}&ForceUseSession=true  */
    @Test
    public void getObjectCollectionInstancesSelectParametrsPositive() {
        auth.authHttpORHttps("urlframework");

        Response responseGet =
                given()
                        .when()
                        //   .header("ForceUseSession", "true")
                        .header("Accept", "application/json;odata=verbose")
                        .header("BPMCSRF", auth.cookiesMap.get("BPMCSRF"))
                        .header("Cookie", auth.cookiesString)

                        .queryParam("$select", "Id,Name")

                        .baseUri(auth.selectUrl("urlframework"))
                        /*     .get("/0/odata/Contact?%24select=Id%2CName")  */
                        .get("/0/odata/Contact")
                        .then().log().all()
                        .statusCode(200)
                        //                       .assertThat()
//                        .body("value", hasItem("Id"))
//                        .body("value", containsString("Name"))
//                        .body("value", not(containsString("OwnerId")))
                        .extract().response();
        assertTrue(responseGet.asPrettyString().contains("Id"));
        assertTrue(responseGet.asPrettyString().contains("Name"));
        Assertions.assertFalse(responseGet.asPrettyString().contains("OwnerId"));
    }

    /*  Add object collection instance Contact Copy
    post("http://{{BaseURL}}/0/odata/Contact")  */
    @Test
    public void postAddObjectCollectionInstanceContactPositive() {
        Response response = ContactServiciesFrame.addRandomContact(auth, ContactServiciesFrame.generateRandomSimpleContact());
        String id = response.path("Id");
        String name = response.path("Name");
        /* нужно проверить гетом, что есть контакт */
        String actualName = ContactServiciesFrame
                .getNameOfContactById(auth, id)
                .body().asPrettyString()
                .replace("\uFEFF", "").replace("\u200B", "");
        Assertions.assertEquals(name, actualName);
    }

    /*  {{BaseURI}}/0/odata/{{CollectionName1}}({{ObjectId1}})  */
    @Test
    public void patchModifyObjectCollectionInstanceContactPositive() {
        Response response = ContactServiciesFrame.addRandomContact(auth, ContactServiciesFrame.generateRandomSimpleContact());

        String id = response.path("Id");
        System.out.println("========" + id);

        given()
                .when()
                //   .header("ForceUseSession", "true")
                .header("Accept", "application/json;odata=verbose")
                .header("BPMCSRF", auth.cookiesMap.get("BPMCSRF"))
                .header("Cookie", auth.cookiesString)

                .contentType(ContentType.JSON)
                .body("""
                        {
                            "MobilePhone": "+7 999 123 1234"
                        }""")

                .baseUri(auth.selectUrl("urlframework"))
                .patch("/0/odata/Contact(" + id + ")")
                .then().log().all()
                .statusCode(204);

        Response responseModify = ContactServiciesFrame.getContactById(auth, id);
        Assertions.assertEquals("+7 999 123 1234", responseModify.path("MobilePhone"));
        /* .assertThat().body("MobilePhone", equalTo("+7 999 123 1234")) */

    }

    /* {{url}}/0/odata/Contact(410006e1-ca4e-4502-a9ec-e54d922d2c00) Supervisor */
    @Test
    public void deleteContactPositive() {
        Response response = ContactServiciesFrame.addRandomContact(auth, ContactServiciesFrame.generateRandomSimpleContact());
        String id = response.path("Id");
        String name = response.path("Name");
        /* нужно проверить гетом, что есть контакт */
        String actualName = ContactServiciesFrame
                .getNameOfContactById(auth, id)
                .body().asPrettyString()
                .replace("\uFEFF", "").replace("\u200B", "");
        Assertions.assertEquals(name, actualName);
        /* теперь само удаление */
        ContactServiciesFrame.deleteContacts(auth, id);
        Response responseDel = ContactServiciesFrame.getContactByIdNegative(auth, id);
        Assertions.assertEquals(404, responseDel.getStatusCode());
        responseDel = ContactServiciesFrame.deleteContactNegative(auth, id);
        Assertions.assertEquals(404, responseDel.getStatusCode());
        Assertions.assertEquals("Not found", responseDel.path(("error.message")));

    }
 /*   @Test
    public void simpleTest() {

        Response response = given()
                .when()
                .header("ForceUseSession", "true")
                .header("Content-Type", "application/json")
                .header("Cookie", ".ASPXAUTH=CE145F778919033192C7A5D2FA3EF47E96629FEDFAF59D53A4AEDCDB9033DCBB8B2401B39D80BC16BA3AB5ADED03FC22BB12BF108B6EC28FC954F662746BE435D64A6B3BF8500310A05AE28B5569793A69D86EFA040A4D1B6EBF94CA7F6B99068743DD7147A6EEC3599F642161D9F64B2567C14468EB39BC09E187D218C96E19C4FD22E71553192F73F886E347E49F2EE527CF4DC495A113C96A0B3BA435620D850D272BA0F2ECFF11A82E60A3BF25C1DCD5EF53771AB95E341D970A0B5A533CB3C166DA6FD072B4C55D2196BE95F6E715EDB7F99410247F853ABFA9D40639F99C0173A8F50E7BF4E45AB6F618F24CB6524F72D298CE5DAF1466FB985D8BAE307BA0DF275B091FA3E9C91152DE5E771A8A20D653C028B213862DE370C5C5A473251F09367F789EA1A2762FA74AEE9F9F204E02D6C3289AD262460ED6132B4B43508621D4714C2FABAE2539540592C4462C209A2C5A3F3CEED291F3F3ECBC4542D4208C9D82980E9CAB4AEEBE9D8B77DA770FDAEC2F209C1031229E690CDBB926A5FA4B97184B2F217CAA84594C8A25A282E1AAA168970BF2019DD8D25A844FF94762D06E945AB061C1573D7B56826077EED8349652F34AE99A776FCE9640D8EB24CBF1C4F210A8BD9F45E76037E1262482F5DF10CF0A0869C52EC6E64E7056FCF9BFE71D3B6CB0A8638C5127FE8DCABA500DD345; BPMCSRF=jjZVd3aGq19rJdaTVTBUa.; BPMLOADER=u1j2vi4teop31jbswlowh0mf; UserName=83|117|112|101|114|118|105|115|111|114")
                .body("{    \r\n    \"UserName\":\"Supervisor\",\r\n    \"UserPassword\":\"Supervisor1-\"\r\n}")
                .post("http://qa026wfmb.rnd.omnichannel.ru:8500/ServiceModel/AuthService.svc/Login")
                .then().log().all()
                .extract().response();

        String cookiesBPMSESSIONID = "v4fgnegnqqal2bashjkrbtjm";
        Map<String, String> cookiesMap = response.getCookies();
        cookiesMap.forEach((k, v) -> System.out.println(k + " :" + v));
        String cookiesString = "BPMSESSIONID=" + cookiesBPMSESSIONID + "; .ASPXAUTH=" + cookiesMap.get(".ASPXAUTH") + "; BPMCSRF=" + cookiesMap.get("BPMCSRF") + "; BPMLOADER=" + cookiesMap.get("BPMLOADER") + "; UserName=" + cookiesMap.get("UserName");
        Response responseGet = given()
                .when()
                //   .header("ForceUseSession", "true")
                .header("Accept", "application/json;odata=verbose")
                .header("BPMCSRF", cookiesMap.get("BPMCSRF"))
                .header("Cookie", cookiesString)

                .get("http://qa026wfmb.rnd.omnichannel.ru:8500/0/odata/Contact?%24select=Id%2CName")
                .then().log().all()
                .extract().response();

        System.out.println(responseGet.cookie("JSESSIONID") + "+++");
    } */

}
/* ForceUseSession: Принудительное использование существующей сессии; */

/* В RestAssured вы можете отключить проверку сертификатов сайта, используя метод relaxedHTTPSValidation().

RestAssured.given()
                .relaxedHTTPSValidation()
                .when().get("https://example.com").then().statusCode(200);

В IntelliJ IDEA вы можете отключить проверку сертиикатов,
 добавив параметр -Didea.ssl.warnings=disable
 в файл idea.vmoptions.
Пример:
-Didea.ssl.warnings=disable
   */