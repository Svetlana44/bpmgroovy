package api.rND21624BpmsQueryBuilder;

import api.rND21624BpmsQueryBuilder.models.User;
import enams.Platform;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import listener.CustomTpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.IsNull.notNullValue;

public class GetTablesNameAndGetTableCaptionsTests {
    public static final String GetTablesName = "/0/rest/BpmsQueryBuilderService/GetTablesName";//в теле можно писать что угодно, например, {}
    public static final String GetTableCaptions = "/0/rest/BpmsQueryBuilderService/GetTableCaptions";    /*
    тело:
    {"tableName": "Contact"} */
    private static final Logger LOG = LoggerFactory.getLogger(GetTablesNameAndGetTableCaptionsTests.class.getName());
    public static User supervisor1;
    public static User svetuser;
    public static User svetuser2BpmsCanReadQueryBuilder;
    public static String url = "https://alcm-app-005.bpmrnd.ru";
    public static String port = "443";
    public final String BPMSESSIONID = "v4fgnegnqqal2bashjkrbtjm";
    public String cookiesString;
    public Map<String, String> cookiesMap;
    public String bpmcsrf;

    @BeforeAll
    public static void setUp() {
     /*   RestAssured.filters(
                new RequestLoggingFilter()
                , new ResponseLoggingFilter()
                , new AllureRestAssured());*/
        //Кастомный логер Aluure. статичный - см третий в списке.
        RestAssured.filters(
                new RequestLoggingFilter()
                , new ResponseLoggingFilter()
                , CustomTpl.customLogFilter().withCustomTemplates());
        supervisor1 = User.builder()
                .isBpmsCanUseQueryBuilder(true)
                .name("Supervisor")
                //     .isCanViewConfiguration(true)
                .pass("BPMAdmin123!")
                .build();
        svetuser = User.builder()
                .isBpmsCanUseQueryBuilder(false)
                .name("SVETuser")
                //      .isCanViewConfiguration(true)
                .pass("BPMAdmin123!")
                .build();
        svetuser2BpmsCanReadQueryBuilder = User.builder()
                .isBpmsCanUseQueryBuilder(true)
                .name("SVETuser2")
                //      .isCanViewConfiguration(true)
                .pass("BPMAdmin123!")
                .build();
    }

    public Response Auth(User user, Platform platform) {
        LOG.info(user.toString() + "============================");
        Response response = given()
                .relaxedHTTPSValidation()  //отключение проверки сертификатов https
                .header("ForceUseSession", "true")
                .header("Content-Type", "application/json")
                .body(user)
                .baseUri(url + ":" + port)
                .post("/ServiceModel/AuthService.svc/Login")
                .then().log().all()
                .statusCode(200)
                .body("Code", equalTo(0))
                .body("Message", equalTo(""))
                .body("Exception", nullValue())
                .body("PasswordChangeUrl", nullValue())
                .body("RedirectUrl", nullValue())

                .extract().response();

        bpmcsrf = response.cookie("BPMCSRF");
        cookiesMap = response.getCookies();
        cookiesMap.forEach((k, v) -> System.out.println("===" + k + " :" + v));
        String cookiesStringCore = "BPMSESSIONID=" + cookiesMap.get("BPMSESSIONID") + "; .ASPXAUTH=" + cookiesMap.get(".ASPXAUTH") + "; BPMCSRF=" + cookiesMap.get("BPMCSRF") + "; CsrfToken=" + cookiesMap.get("CsrfToken");

        String cookiesStringFrame = "BPMSESSIONID=" + BPMSESSIONID + "; .ASPXAUTH=" + cookiesMap.get(".ASPXAUTH") + "; BPMCSRF=" + cookiesMap.get("BPMCSRF") + "; BPMLOADER=" + cookiesMap.get("BPMLOADER") + "; UserName=" + cookiesMap.get("UserName");

        if (platform == Platform.Frame) {
            cookiesString = cookiesStringFrame;
        } else cookiesString = cookiesStringCore;


        if (bpmcsrf != null) {
            LOG.info("/n === токен BPMCSRF сохранён в String bpmcsrf ===");
            return response;
        } else {
            throw new RuntimeException("=== не удалось извлечь BPMCSRF токен ===");
        }
    }

    @Step("Post запрос с телом")
    public Response postQuery(String query, String body) {
        Response response = given()
                .relaxedHTTPSValidation()  //отключение проверки сертификатов https
                .header("ForceUseSession", "true")
                .header("Content-Type", "application/json")
                .header("BPMCSRF", bpmcsrf)
                .header("Cookie", cookiesString)
                .header("Content-Encoding", "gzip")
                .header("Accept", "text/plain") // <-- ожидаем base64-строку
                .baseUri(url + ":" + port)
                .body(body)//тело запроса перед запросом
                .post(query)
                .then().log().all()

                .extract().response();
        return response;
    }

    @Test
    @Step("Supervisor со всеми правами.")
    public void GetTablesNameSupervisorTest() {
        Auth(supervisor1, Platform.Frame);
        Response response = postQuery(GetTablesName, "{}");
        Assertions.assertEquals(200, response.getStatusCode());
        response.then().assertThat()
                .body("GetTablesNameResult.tableNames", notNullValue());

        Response response2 = postQuery(GetTableCaptions, "{\"tableName\": \"Contact\"}");
        Assertions.assertEquals(200, response2.getStatusCode());
        response2.then().assertThat()
                .body("GetTableCaptionsResult.tableCaptions", notNullValue());
    }

    /* 200ОК и текст ошибки д.б.
    {
        "GetTablesNameResult": {
            "tableNames": null,
            "responseStatus": null,
            "queryId": null,
            "rowsAffected": 0,
            "nextPrcElReady": false,
            "success": false,
            "errorInfo": {
                "errorCode": "OperationRightsException",
                "message": "Недостаточно прав для чтения данных через Конструктор Запросов. Обратитесь к администратору системы.",
                "stackTrace": "   at BPMSoft.Configuration.BpmsOperationChecker.OperationChecker.CheckContainsOneOperation(String errorText, String[] operationCodes)\r\n   at BPMSoft.Configuration.BpmsQueryBuilderService.CheckCanUseMethodByOperation()\r\n   at BPMSoft.Configuration.BpmsQueryBuilderService.GetTablesName()"
            }
        }
    }
    * {
        "GetTableCaptionsResult": {
            "tableCaptions": null,
            "responseStatus": null,
            "queryId": null,
            "rowsAffected": -1,
            "nextPrcElReady": false,
            "success": false,
            "errorInfo": {
                "errorCode": "OperationRightsException",
                "message": "Недостаточно прав для чтения данных через Конструктор Запросов. Обратитесь к администратору системы.",
                "stackTrace": "   at BPMSoft.Configuration.BpmsOperationChecker.OperationChecker.CheckContainsOneOperation(String errorText, String[] operationCodes)\r\n   at BPMSoft.Configuration.BpmsQueryBuilderService.CheckCanUseMethodByOperation()\r\n   at BPMSoft.Configuration.BpmsQueryBuilderService.GetTableCaptions(String tableName, EntitySchemaRecordRightOperation operation)"
            }
        }
    }
    * */
    @Test
    @Step("SVETuser без прав из списка.")
    public void NotGetTablesNameTest() {
        Auth(svetuser, Platform.Frame);
        Response response = postQuery(GetTablesName, "{}");
        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertEquals(RequestsMessages.erroreMessageGetTablesName, response.jsonPath().getString("GetTablesNameResult.errorInfo.message"));
//сообщения об ошибке одинаковы, отличается текст др полей.
        Response response2 = postQuery(GetTableCaptions, "{\"tableName\": \"Contact\"}");
        Assertions.assertEquals(200, response2.getStatusCode());
        Assertions.assertEquals(RequestsMessages.erroreMessageGetTablesName, response2.jsonPath().getString("GetTableCaptionsResult.errorInfo.message"));
    }

    @Test
    @Step("svetuser2BpmsCanReadQueryBuilder одно из списка - BpmsCanReadQueryBuilder.")
    public void BpmsCanReadQueryBuilderGetTablesNameTest() {
        Auth(svetuser2BpmsCanReadQueryBuilder, Platform.Frame);
        Response response = postQuery(GetTablesName, "{}");
        Assertions.assertEquals(200, response.getStatusCode());
        response.then().assertThat()
                .body("GetTablesNameResult.tableNames", notNullValue());

        Response response2 = postQuery(GetTableCaptions, "{\"tableName\": \"Contact\"}");
        Assertions.assertEquals(200, response2.getStatusCode());
        response2.then().assertThat()
                .body("GetTableCaptionsResult.tableCaptions", notNullValue());
    }

}
