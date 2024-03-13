package utilies;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.Data;
import version_1_3.api.models.AuthUser;
import version_1_3.ui.selenide.pages.AuthPageSelenide;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

@Data
public class Auth {
    public String bpmcsrf; /* BPMCSRF cookie  response.cookie("BPMCSRF")  */
    public final String BPMSESSIONID = "v4fgnegnqqal2bashjkrbtjm";

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
    AuthUser user;
    String expected = """
            {
                "Code": 0,
                "Message": "",
                "Exception": null,
                "PasswordChangeUrl": null,
                "RedirectUrl": null
            }
            """;


    public Auth() {
        this.authProperti();
        this.body = "{" +
                "\"UserName\":\"" + login + "\"," +
                "\"UserPassword\":\"" + pass + "\"" +
                "}";
        this.user = AuthUser.builder()
                .userName(this.login)
                .userPassword(this.pass)
                .build();
    }

    public static void main(String[] args) {
        Auth auth = new Auth();
//        auth.authHttpORHttps("urlframework");


        ContactServicies.getNameOfContactById(auth, "410006e1-ca4e-4502-a9ec-e54d922d2c00");
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
        Response response = given()
                .when()
                .header("ForceUseSession", "true")
                .header("Content-Type", "application/json")
                .header("Cookie", ".ASPXAUTH=CE145F778919033192C7A5D2FA3EF47E96629FEDFAF59D53A4AEDCDB9033DCBB8B2401B39D80BC16BA3AB5ADED03FC22BB12BF108B6EC28FC954F662746BE435D64A6B3BF8500310A05AE28B5569793A69D86EFA040A4D1B6EBF94CA7F6B99068743DD7147A6EEC3599F642161D9F64B2567C14468EB39BC09E187D218C96E19C4FD22E71553192F73F886E347E49F2EE527CF4DC495A113C96A0B3BA435620D850D272BA0F2ECFF11A82E60A3BF25C1DCD5EF53771AB95E341D970A0B5A533CB3C166DA6FD072B4C55D2196BE95F6E715EDB7F99410247F853ABFA9D40639F99C0173A8F50E7BF4E45AB6F618F24CB6524F72D298CE5DAF1466FB985D8BAE307BA0DF275B091FA3E9C91152DE5E771A8A20D653C028B213862DE370C5C5A473251F09367F789EA1A2762FA74AEE9F9F204E02D6C3289AD262460ED6132B4B43508621D4714C2FABAE2539540592C4462C209A2C5A3F3CEED291F3F3ECBC4542D4208C9D82980E9CAB4AEEBE9D8B77DA770FDAEC2F209C1031229E690CDBB926A5FA4B97184B2F217CAA84594C8A25A282E1AAA168970BF2019DD8D25A844FF94762D06E945AB061C1573D7B56826077EED8349652F34AE99A776FCE9640D8EB24CBF1C4F210A8BD9F45E76037E1262482F5DF10CF0A0869C52EC6E64E7056FCF9BFE71D3B6CB0A8638C5127FE8DCABA500DD345; BPMCSRF=jjZVd3aGq19rJdaTVTBUa.; BPMLOADER=u1j2vi4teop31jbswlowh0mf; UserName=83|117|112|101|114|118|105|115|111|114")
                /*      .body("{    \r\n    \"UserName\":\"Supervisor\",\r\n    \"UserPassword\":\"Supervisor1-\"\r\n}")   */
                .body(user)
                .baseUri(this.selectUrl("urlframework"))
                .post("/ServiceModel/AuthService.svc/Login")
                .then().log().all()
                .statusCode(200)
                .body("Code", equalTo(0))
                .body("Message", equalTo(""))
                .body("Exception", nullValue())
                .body("PasswordChangeUrl", nullValue())
                .body("RedirectUrl", nullValue())
                .extract().response();


        cookiesMap = response.getCookies();
        cookiesMap.forEach((k, v) -> System.out.println(k + " :" + v));
        cookiesString = "BPMSESSIONID=" + BPMSESSIONID + "; .ASPXAUTH=" + cookiesMap.get(".ASPXAUTH") + "; BPMCSRF=" + cookiesMap.get("BPMCSRF") + "; BPMLOADER=" + cookiesMap.get("BPMLOADER") + "; UserName=" + cookiesMap.get("UserName");


        this.bpmcsrf = response.getCookie("BPMCSRF");
        System.out.println(">>>>>"
                + "BPMCSRF" + ">>>>>>" + this.bpmcsrf);
        System.out.println("cookies as String: " + cookiesString);
    }
}
