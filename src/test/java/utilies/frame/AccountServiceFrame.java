package utilies.frame;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilies.Auth;
import version_1_3.api.jsonschemas.IDContact;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public class AccountServiceFrame {
    private static final Logger LOG = LoggerFactory.getLogger(AccountServiceFrame.class.getName());

    //  AccountId
    @Step("Получение ID всех Компаний с проверкой соответствия json schema")
    public static Response getAllIdOfAccountsFrame(Auth auth) {
        auth.authHttpORHttps("urlframework");
        LOG.info("Frame===> Получение ID всех Компаний с проверкой соответствия IDContactSchema");
        return given()
                .when()
                //   .header("ForceUseSession", "true")
                .header("Accept", "application/json;odata=verbose")
                .header("BPMCSRF", auth.cookiesMap.get("BPMCSRF"))
                .header("Cookie", auth.cookiesString)
                .contentType(ContentType.JSON)
                .baseUri(auth.selectUrl("urlframework"))
                .get("/0/odata/Account?$select=Id")
                .then().log().all()
                .statusCode(200)
                .assertThat()
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

    public static void main(String[] args) {
        Auth auth = new Auth();
        getAllIdOfAccountsFrame(auth);

    }
}
