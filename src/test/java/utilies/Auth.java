package utilies;

import io.restassured.http.ContentType;
import lombok.Data;
import version_1_3.ui.selenide.pages.AuthPageSelenide;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static io.restassured.RestAssured.given;

@Data
public class Auth {
    public String bpmcsrf; /* BPMCSRF cookie  response.cookie("BPMCSRF")  */
    private String login;
    private String pass;
    private String url;
    private String urlframework;
    private String urlwincore;
    private String urllinuxcore;
    private String body;

    public Auth() {
        this.authProperti();
        body = "{" +
                "\"UserName\":\"" + login + "\"," +
                "\"UserPassword\":\"" + pass + "\"" +
                "}";
    }

    public static void main(String[] args) {
        Auth auth = new Auth();
        auth.authHttpsTest();
    }

    public void authProperti() {
        Properties properties = new Properties();
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
            e.printStackTrace();
        }
    }

    public void authHttpsTest() {
        System.out.println(this.getUrlframework());
        this.bpmcsrf = given()
                .relaxedHTTPSValidation()  /* отключить проверку сертификатов  */
                .header("Accept", "application/json")
                .header("Content-Type", "application/json; charset=utf-8; IEEE754Compatible=true")
                .header("ForceUseSession", "true")
                //          .header("BPMCSRF", "<значение аутентификационного cookie>")
                .header("BPMCSRF", "fBBkz2K1mC2Y09GanrEzLe")
                .body(this.getBody())
                .contentType(ContentType.JSON)
                .when()
                .baseUri(this.getUrlframework())
                .post("/ServiceModel/AuthService.svc/Login")

                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .cookie("BPMCSRF");
        System.out.println("___________________"
                + "BPMCSRF" + "------------------" + this.bpmcsrf);
    }
}
