package version_1_3.api.odata;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import utilies.Auth;
import version_1_3.api.models.AuthUser;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.sessionId;

/*  только для .NET   в пути есть нуль
{{BaseURI}}/0/ServiceModel/EntityDataService.svc
а для Core должно быть тоже самое только без нуля
{{BaseURI}}/ServiceModel/EntityDataService.svc   */
public class FrameODataTests {
    Auth auth = new Auth();
    AuthUser user = AuthUser.builder()
            .userName(auth.getLogin())
            .userPassword(auth.getPass())
            .build();

    @Test
    public void authFrame() {
        auth.authHttpORHttps("urlframework");
    }


    /*   {{BaseURI}}/0/odata/{{CollectionName1}}   */
    @Test
    public void GetObjectCollectionInstancesPositive() {
        auth.authHttpORHttps("urlframework");

        sessionId = "v4fgnegnqqal2bashjkrbtjm";
        Response body = (Response) given()
                .when()
                //        .relaxedHTTPSValidation()  /* отключить проверку сертификатов  */
                .header("Accept", "application/json")
                //       .header("Content-Type", "application/json; charset=utf-8; IEEE754Compatible=true")
                .header("ForceUseSession", "true")
                //          .header("BPMCSRF", "<значение аутентификационного cookie>")
                .header("BPMCSRF", auth.bpmcsrf)
                //           .header("Cookie", auth.cookiesMap)
                .contentType(ContentType.JSON)
                //    .when()
                .baseUri(auth.selectUrl("urlframework"))
                .get("/0/odata/Contact")
                .then()
                .log().all()
                .statusCode(200)

                .extract()
                .body();
        System.out.println("А сейчас будет тело:\n" + body.asString());

        System.out.println(auth.selectUrl("urlframework"));
        System.out.println(auth.bpmcsrf);

    }

    /* http://qa026wfmb.rnd.omnichannel.ru:8500/0/odata/Contact?$select=Id,Name&BPMCSRF={{BPMCSRF}}&ForceUseSession=true  */
    @Test
    public void GetObjectCollectionInstancesSelectParametrsPositive() {

        Response responseGet = given()
                .when()
                .header("Accept", "application/json;odata=verbose")
                .header("BPMCSRF", "v9smzUKcSDEhS9hAd3LJFO")
                .header("Cookie", "BPMSESSIONID=v4fgnegnqqal2bashjkrbtjm; .ASPXAUTH=21974135EA75E5E21772C7FE81669158D60B49977CF64C52F5F7F3A34DB4A315535627E0B3FDF5F6A28C525B15255A287765F6EFBB334E821CEF548C31E944A5AAEB3D8130D3443953024AE5F00B8B10B81C45BA43D2EA709AC1BBC20F931CD0AB09F6E233E2E404F47FF0F89EBA48818D5B87D611D280223755231D0ECA4C2EE6FEBC52A5A7DBB3C748BFC9E1D09BE1768CC9A0315BAE26B84D7A6AF38EB247BF5FD480D7F74DD918DB0EFC729827D72A1F2D7EF59E62CEA28BD15E76ABC9E3ABF2E4232D24848FC2D2C7CF49E38D0EA05E1D8B342DB540D3E9DFBD847A1BC9CF176EF8D5489BCFD90093695894D337ED8FEFAE01FC175D8197D8D80B7CCB6ACA2B90FC0A42CA2D18B42DAE62A06EAB5FC204301E308A26FB7DC5BB70B9404A53C79286A7B1A726345185AF7D5DBF977991497CAE6A3266ED7629FF3F7B97DE4BA9A3DF3A93E8C1F1024604AF5DED4B4D28899346A09774368FC7A9E11AFCF22CF2096167C344A84EA839F38BB172B58266FFF12F180C9168F5AD025DADFF4F004CB6B421AA3FFD82B2531189C884E7F0588E159FD402BC768CF1027D1E8299C32003DE6807D7D1100ADFA36DD0D6E85854F5AF5C677CE526310FC01C50426DF286E19D492B53F9A6469F7FBD961987E232AB82C71B193CD9B464B765292C7E247692927C6DDEC13491500F9A6B23B60CCF82E1; BPMCSRF=aO.MVJM5Eatyy12c1ZLzDe; BPMLOADER=u1j2vi4teop31jbswlowh0mf; UserName=83|117|112|101|114|118|105|115|111|114")

                .get("http://qa026wfmb.rnd.omnichannel.ru:8500/0/odata/Contact?%24select=Id%2CName")
                .then().log().all()
                .extract().response();
    }

    @Test
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
    }

}
/* ForceUseSession: Принудительное использование существующей сессии; */
/*RestAssured запрос:

given().header("Accept", "application/json")
       .header("Content-Type", "application/json; charset=utf-8; IEEE754Compatible=true")
       .header("ForceUseSession", "true")
       .header("BPMCSRF", "<значение аутентификационного cookie>")
       .body("{\"field1\": \"value1\", \"field2\": \"value2\"}")
       .when().post("/your-endpoint")
       .then().statusCode(200); */
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