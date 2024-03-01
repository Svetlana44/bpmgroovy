package version_1_3.api.odata;

import org.junit.jupiter.api.Test;
import utilies.Auth;
import version_1_3.api.models.AuthUser;

public class SimpleTests {
    Auth auth = new Auth();
    AuthUser user = AuthUser.builder()
            .userName(auth.getLogin())
            .userPassword(auth.getPass())
            .build();

    /*   {{BaseURI}}/0/odata/{{CollectionName1}}   */
    @Test
    public void GetObjectCollectionInstancesPositive() {
        auth.authHttpsTest();
//        ResponseBodyExtractionOptions body = given()
//                //        .relaxedHTTPSValidation()  /* отключить проверку сертификатов  */
//                .header("Accept", "application/json")
//                .header("Content-Type", "application/json; charset=utf-8; IEEE754Compatible=true")
//                .header("ForceUseSession", "true")
//                //          .header("BPMCSRF", "<значение аутентификационного cookie>")
//                .header("BPMCSRF", auth.bpmcsrf)
//                .contentType(ContentType.JSON)
//                .when()
//                .baseUri(auth.getUrl())
//                .get("/0/odata/Contact?$select=Id,Name")
//
//                .then()
//                .log().all()
//                .statusCode(200)
//
//                .extract()
//                .body();

        System.out.println(auth.bpmcsrf);
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