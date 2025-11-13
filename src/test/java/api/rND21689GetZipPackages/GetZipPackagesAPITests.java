package api.rND21689GetZipPackages;

import api.rND21689GetZipPackages.models.User;
import enams.Platform;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ui.seleniumwebdriver.pagesTests.BaseTests;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

public class GetZipPackagesAPITests extends BaseTests {

    private static final Logger LOG = LoggerFactory.getLogger(GetZipPackagesAPITests.class.getName());
    public final String BPMSESSIONID = "v4fgnegnqqal2bashjkrbtjm";
    Platform platform;
    String platformStr;

    public String cookiesString;
    public Map<String, String> cookiesMap;
    public String GetZipPackages = "/ServiceModel/PackageInstallerService.svc/GetZipPackages";
    public String PKGName = "[\"Custom\"]";
    public String bpmcsrf;
    String cookiesStringCore;
    String cookiesStringFrame;

    /*
        @Step(".isCanManageSolution(false) носит информативный характер, и не применён в коде")
        @BeforeAll
        public static void setUp() {
         /*   RestAssured.filters(
                    new RequestLoggingFilter()
                    , new ResponseLoggingFilter()
                    , new AllureRestAssured());*/
    /* Кастомный логер Aluure. статичный - см третий в списке.
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
*/
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
                .baseUri(url)
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
        cookiesStringCore = "BPMSESSIONID=" + cookiesMap.get("BPMSESSIONID") + "; .ASPXAUTH=" + cookiesMap.get(".ASPXAUTH") + "; BPMCSRF=" + cookiesMap.get("BPMCSRF") + "; CsrfToken=" + cookiesMap.get("CsrfToken");
        cookiesStringFrame = "BPMSESSIONID=" + BPMSESSIONID + "; .ASPXAUTH=" + cookiesMap.get(".ASPXAUTH") + "; BPMCSRF=" + cookiesMap.get("BPMCSRF") + "; BPMLOADER=" + cookiesMap.get("BPMLOADER") + "; UserName=" + cookiesMap.get("UserName");


        if (bpmcsrf != null) {
            LOG.info("/n === токен BPMCSRF сохранён в String bpmcsrf ===");
            return response;
        } else {
            throw new RuntimeException("=== не удалось извлечь BPMCSRF токен ===");
        }
    }

    public Response GetZipPackages(String PKGName, Platform platform) {
        GetZipPackages = (platform == Platform.Frame) ? "/0" + GetZipPackages : GetZipPackages;
        cookiesString = (platform == Platform.Frame) ? cookiesStringFrame : cookiesStringCore;

        Response response = given()
                .relaxedHTTPSValidation()  //отключение проверки сертификатов https
                .header("ForceUseSession", "true")
                .header("Content-Type", "application/json")
                .header("BPMCSRF", bpmcsrf)
                .header("Cookie", cookiesString)
                .header("Content-Encoding", "gzip")
                .header("Accept", "text/plain") // <-- ожидаем base64-строку
                .body(PKGName)
                .baseUri(url)
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
        Assertions.assertEquals(200, GetZipPackages(PKGName, Platform.Frame).getStatusCode());
    }

    @Test
    @Step("Без CanManageSolution ограничить доступ до сервиса /ServiceModel/PackageInstallerService.svc/GetZipPackages")

    public void GetZipPackagesWithNotCanManageSolutionTest() {
        Auth(svetuser2);
        Assertions.assertEquals(403, GetZipPackages(PKGName, Platform.Frame).getStatusCode());
    }
}
