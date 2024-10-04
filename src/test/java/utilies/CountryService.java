package utilies;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import version_1_3.api.jsonschemas.IDCountry;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public class CountryService {
    private static final Logger LOG = LoggerFactory.getLogger(CountryService.class.getName());

    //  AccountId
    @Step("Получение ID всех Стран с проверкой соответствия json schema")
    public static Response getAllIdOfCountries(Auth auth, String typeUrl) {
        auth.authHttpORHttps(typeUrl);
        String requestPath = "/odata";
        if (typeUrl.equals("urlframework")) {
            requestPath = "/0" + requestPath;
        }
        LOG.info(typeUrl + "===> Получение ID всех Стран с проверкой соответствия IDCountrySchema");
        return given()
                .when()
                //   .header("ForceUseSession", "true")
                .header("Accept", "application/json;odata=verbose")
                .header("BPMCSRF", auth.cookiesMap.get("BPMCSRF"))
                .header("Cookie", auth.cookiesString)
                .contentType(ContentType.JSON)
                .baseUri(auth.selectUrl(typeUrl))
                .get(requestPath + "/Country?$select=Id")
                .then().log().all()
                .statusCode(200)
                .assertThat()
                .body(matchesJsonSchema(IDCountry.IDCountrySchema))
                .extract().response();
    }

    @Step("CORE===> Получение ID всех Стран с проверкой соответствия json schema")
    public static Response getAllIdOfAccountsCore(Auth auth) {
        auth.authHttpORHttps("urlwincore");
        LOG.info("CORE===> Получение ID всех Компаний с проверкой соответствия IDCountrySchema");
        return given()
                .when()
                //   .header("ForceUseSession", "true")
                .header("Accept", "application/json;odata=verbose")
                .header("BPMCSRF", auth.cookiesMap.get("BPMCSRF"))
                .header("Cookie", auth.cookiesString)
                .contentType(ContentType.JSON)
                .baseUri(auth.selectUrl("urlwincore"))
                .get("/odata/Country?$select=Id")
                .then().log().all()
                .statusCode(200)
                .assertThat()
                .body(matchesJsonSchema(IDCountry.IDCountrySchema))
                .extract().response();
    }

    public static void main(String[] args) {
        Auth auth = new Auth();
        getAllIdOfCountries(auth, "urlframework");

    }
}
