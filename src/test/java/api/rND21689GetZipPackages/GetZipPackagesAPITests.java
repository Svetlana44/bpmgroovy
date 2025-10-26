package api.rND21689GetZipPackages;

import api.rND21689GetZipPackages.models.User;
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

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

public class GetZipPackagesAPITests {

    private static final Logger LOG = LoggerFactory.getLogger(GetZipPackagesAPITests.class.getName());
    public static User supervisor1;
    public static User svetuser2;
    public static String url = "https://alcm-bpms-005.bpmrnd.ru";
    public static String port = "5002";
    public String cookiesString;
    public Map<String, String> cookiesMap;
    public String GetZipPackages = "/ServiceModel/PackageInstallerService.svc/GetZipPackages";
    public String PKGName = "[\"Custom\"]";
    public String bpmcsrf;
    String expected = """
            {
                "Code": 0,
                "Message": "",
                "Exception": null,
                "PasswordChangeUrl": null,
                "RedirectUrl": null
            }
            """;
    private String body;

    @Step(".isCanManageSolution(false) носит информативный характер, и не применён в коде")
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
                .isCanManageSolution(true)
                .name("Supervisor")
                .isCanViewConfiguration(true)
                .pass("BPMAdmin123!")
                .build();
        svetuser2 = User.builder()
                .isCanManageSolution(false)
                .name("SVETuser")
                .isCanViewConfiguration(true)
                .pass("BPMAdmin123!")
                .build();
        LOG.info(svetuser2.toString());
    }

    public Response Auth(User user) {
     /*   this.body = "{" +
                "\"UserName\":\"" + user.name + "\"," +
                "\"UserPassword\":\"" + user.pass + "\"" +
                "}";*/
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
        cookiesString = cookiesStringCore;


        if (bpmcsrf != null) {
            LOG.info("/n === токен BPMCSRF сохранён в String bpmcsrf ===");
            return response;
        } else {
            throw new RuntimeException("=== не удалось извлечь BPMCSRF токен ===");
        }
    }

    public Response GetZipPackages(String PKGName) {
        Response response = given()
                .relaxedHTTPSValidation()  //отключение проверки сертификатов https
                .header("ForceUseSession", "true")
                .header("Content-Type", "application/json")
                .header("BPMCSRF", bpmcsrf)
                .header("Cookie", cookiesString)
                .header("Content-Encoding", "gzip")
                .header("Accept", "text/plain") // <-- ожидаем base64-строку
                .body(PKGName)
                .baseUri(url + ":" + port)
                .post(GetZipPackages)
                .then().log().all()

                .extract().response();
        if (response.statusCode() == 200) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));

            String fileName = "C:\\Users\\svetlana.terekhova\\Downloads\\" + timestamp + "_GetZipPackages_Custom.gz";

//сервер возвращает уже бинарный gzip (а не base64)
            byte[] fileBytes = response.asByteArray();
            try (FileOutputStream fos = new FileOutputStream(fileName)) {
                fos.write(fileBytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            LOG.info("Файл сохранён: " + fileName);
        } else {
            LOG.info(">>> StatusCode = " + response.statusCode());
        }

        return response;
    }

    @Test
    @Step("CanManageSolution есть доступ до сервиса /ServiceModel/PackageInstallerService.svc/GetZipPackages")

    public void GetZipPackagesWithCanManageSolutionCheckTest() {
        Auth(supervisor1);
        Assertions.assertEquals(GetZipPackages(PKGName).getStatusCode(), 200);
    }

    @Test
    @Step("Без CanManageSolution ограничить доступ до сервиса /ServiceModel/PackageInstallerService.svc/GetZipPackages")

    public void GetZipPackagesWithNotCanManageSolutionTest() {
        Auth(svetuser2);
        Assertions.assertEquals(GetZipPackages(PKGName).getStatusCode(), 403);
    }
}
