package utilies;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import lombok.Data;
import version_1_3.ui.selenide.pages.AuthPageSelenide;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.sessionId;

@Data
public class Auth {
    public String bpmcsrf; /* BPMCSRF cookie  response.cookie("BPMCSRF")  */
    public String cookiesBPMSESSIONID = "v4fgnegnqqal2bashjkrbtjm";
    public Map<String, String> cookiesMap;
    public String cookiesString;
    public String cookiesASPXAUTH;
    public String cookiesBPMLOADER;
    public String cookiesUserName;
    private String login;
    private String pass;
    private String url;
    private String urlframework;
    private String urlwincore;
    private String urllinuxcore;
    private String body;
    private Properties properties = new Properties();

    public Auth() {
        this.authProperti();
        body = "{" +
                "\"UserName\":\"" + login + "\"," +
                "\"UserPassword\":\"" + pass + "\"" +
                "}";
    }

    public static void main(String[] args) {
        Auth auth = new Auth();
        auth.authHttpORHttps("urlframework");
    }

    @Step("Загрузка stands.properties")
    public void authProperti() {

        try (InputStream in = AuthPageSelenide.class.getClassLoader().getResourceAsStream("configs/stands.properties")) {
            properties.load(in);
            this.login = properties.getProperty("login");
            this.pass = properties.getProperty("password");
            this.url = properties.getProperty("url");
            this.urlframework = properties.getProperty("urlframework");
            this.urlwincore = properties.getProperty("urlwincore");
            this.urllinuxcore = properties.getProperty("urllinuxcore")
            //.replaceAll("^\"|\"$", "")
            ;
        } catch (IOException e) {
            System.out.println("Спасите, помогите, проперти не грузятся.");
            e.printStackTrace();
        }
    }

    @Step("Выбор стэнда по типу операционки")
    public String selectUrl(String typeUrl) {
        return this.properties.getProperty(typeUrl);
    }


    @Step("Авторизация на стэнде")
    public void authHttpORHttps(String typeUrl) {
        //      cookiesBPMSESSIONID = GenerateId.generateSessionId();
        sessionId = this.cookiesBPMSESSIONID;
        System.out.println(this.selectUrl("urlframework"));
        ValidatableResponse response = given()
                .relaxedHTTPSValidation()  /* отключить проверку сертификатов  */
                .header("ForceUseSession", "true")
                .header("Content-Type", "application/json")
                .body(this.getBody())
                .baseUri(selectUrl(typeUrl))
                .post("/ServiceModel/AuthService.svc/Login")

                .then()
                .log().all()
                .statusCode(200);


        this.bpmcsrf = response.extract().cookie("BPMCSRF");
        System.out.println(">>>>>"
                + "BPMCSRF" + ">>>>>>" + this.bpmcsrf);
        this.cookiesASPXAUTH = response.extract().cookie(".ASPXAUTH");
        System.out.println(">>>>>"
                + "cookies: .ASPXAUTH: " + ">>>>>>" + this.cookiesASPXAUTH);
        this.cookiesBPMLOADER = response.extract().cookie("BPMLOADER");
        System.out.println("<<<<<<>>>>>>"
                + "cookies: BPMLOADER: " + ">>>>>>" + this.cookiesBPMLOADER);
        this.cookiesUserName = response.extract().cookie("UserName");
        System.out.println("<<<<<<"
                + "cookies: UserName: " + "<<<>>>>>>>>>" + this.cookiesUserName);

        cookiesMap = response.extract().cookies();
        cookiesMap.forEach((k, v) -> System.out.println(k + " :" + v));
        cookiesString = "BPMSESSIONID=" + this.cookiesBPMSESSIONID + "; .ASPXAUTH=" + this.cookiesASPXAUTH + "; BPMCSRF=" + this.bpmcsrf + "; BPMLOADER=" + this.cookiesBPMLOADER + "; UserName=" + this.cookiesUserName;
        System.out.println(cookiesString);
    }
}
