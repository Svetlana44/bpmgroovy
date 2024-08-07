package utilies.frame;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilies.Auth;
import version_1_3.api.jsonschemas.IDContactType;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public class ContactTypeServiceFrame {
    private static final Logger LOG = LoggerFactory.getLogger(ContactTypeServiceFrame.class.getName());

    //  ContactType
    @Step("Получение ID всех Типов контакта (всего пока 4) с проверкой соответствия json schema")
    public static Response getAllIdOfContactTypeFrame(Auth auth) {
        auth.authHttpORHttps("urlframework");
        LOG.info("Frame===> Получение ID всех Типов контакта (всего пока 4) с проверкой соответствия IDContactTypeSchema");
        return given()
                .when()
                //   .header("ForceUseSession", "true")
                .header("Accept", "application/json;odata=verbose")
                .header("BPMCSRF", auth.cookiesMap.get("BPMCSRF"))
                .header("Cookie", auth.cookiesString)
                .contentType(ContentType.JSON)
                .baseUri(auth.selectUrl("urlframework"))
                .get("/0/odata/ContactType?$select=Id")
                .then().log().all()
                .statusCode(200)
                .assertThat()
                .body(matchesJsonSchema(IDContactType.IDContactTypeSchema))
                .extract().response();
    }

    @Step("CORE===> Получение ID всех Типов контакта (всего пока 4) с проверкой соответствия json schema")
    public static Response getAllIdOfContactTypeCore(Auth auth) {
        auth.authHttpORHttps("urlwincore");
        LOG.info("CORE===> Получение ID всех Типов контакта (всего пока 4) с проверкой соответствия IDContactTypeSchema");
        return given()
                .when()
                //   .header("ForceUseSession", "true")
                .header("Accept", "application/json;odata=verbose")
                .header("BPMCSRF", auth.cookiesMap.get("BPMCSRF"))
                .header("Cookie", auth.cookiesString)
                .contentType(ContentType.JSON)
                .baseUri(auth.selectUrl("urlwincore"))
                .get("/odata/ContactType?$select=Id")
                .then().log().all()
                .statusCode(200)
                .assertThat()
                .body(matchesJsonSchema(IDContactType.IDContactTypeSchema))
                .extract().response();
    }

    public static void main(String[] args) {
        Auth auth = new Auth();
        getAllIdOfContactTypeFrame(auth);

    }
}
