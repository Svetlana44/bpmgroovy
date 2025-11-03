package utilies;

import api.models.Contact;
import api.models.SysUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import enams.Platform;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;

public class UserService {
    private static final Random random = new Random();
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class.getName());
    private final Properties properties = new Properties();
    private String loginSupervisor;
    private String passSupervisor;
    private String url;

    //private static String createBody = "{\"Id\":\"e0a6bb40-9af7-4b21-becd-ea0faac6d78b\",\"CreatedBy\":\"410006e1-ca4e-4502-a9ec-e54d922d2c00\",\"ModifiedBy\":\"410006e1-ca4e-4502-a9ec-e54d922d2c00\",\"Name\":\"SVETuser\",\"SysAdminUnitType\":\"472e97c7-6bd7-df11-9b2a-001d60e938c6\",\"Contact\":\"c3b2db6e-84cd-48b0-b12e-01f096d2b571\",\"TimeZone\":null,\"UserPassword\":\"BPMAdmin123!\",\"Active\":true,\"SynchronizeWithLDAP\":false,\"SysCulture\":\"1a778e3f-0a8e-e111-84a3-00155d054c03\",\"ConnectionType\":0,\"LDAPElement\":null,\"UserConnectionType\":\"df8ff7a2-c9bf-4f28-80b1-ed16fa77818d\"}";

    @Step("Добавление нового Юзера с логином и паролем")
    public static Response createUser(String login, Platform platform) throws IOException {
        int randomNumber = Math.abs(random.nextInt(333));
        String platformStr = (platform == Platform.Frame) ? "urlframework" : "urllinuxcore";
        Auth auth = new Auth();
        Contact contact = Contact.builder()
                .name(login + randomNumber)
                .build();
        Response contactResponce = ContactServicies.addRandomContact(auth, platformStr, contact);


        auth.authHttpORHttps(platformStr);
        String requestPathOData = "/odata";
        String requestPathAdministrationService = "/rest/AdministrationService";
        // /rest/AdministrationService/UpdateOrCreateUser
        if (platformStr.equals("urlframework")) {
            requestPathOData = "/0" + requestPathOData;
            requestPathAdministrationService = "/0" + requestPathAdministrationService;
        }
        //   return
        String contactId = contactResponce.body().as(Contact.class).getId();
        String userId = java.util.UUID.randomUUID().toString();
        String supervisorId = "410006e1-ca4e-4502-a9ec-e54d922d2c00";

        SysUser sysUser = SysUser.builder()
                .id(userId)
                .createdBy(supervisorId)
                .modifiedBy(supervisorId)
                .contact(contactId)
                .name(login)
                .userPassword("BPMAdmin123!")
                .timeZone(null)
                .lDAPElement(null)
                .build();
        LOG.info(platformStr + " ==> Добавление SysUser -> SysAdminUnit + /n"
                + sysUser + "//n");

        ObjectMapper mapper = new ObjectMapper();
        String roleId = "a29a3ba5-4b0d-de11-9a51-005056c00008";
        String jsonObject = mapper.writeValueAsString(sysUser);

        Map<String, String> requestBody = new LinkedHashMap<>();
        requestBody.put("jsonObject", jsonObject);
        requestBody.put("roleId", roleId);

        LOG.info("Request body: " + mapper.writeValueAsString(requestBody));

        return given()
                .relaxedHTTPSValidation()  //отключение проверки сертификатов https
                .when()
                .header("ForceUseSession", "true")
                //            .header("Accept", "application/json;odata=verbose")
                .header("BPMCSRF", auth.cookiesMap.get("BPMCSRF"))
                .header("Cookie", auth.cookiesString)
                //.header("Cookie", auth.cookiesMap.get(".ASPXAUTH"))
                // 500 .header("Cookie", ".ASPXAUTH=CE145F778919033192C7A5D2FA3EF47E96629FEDFAF59D53A4AEDCDB9033DCBB8B2401B39D80BC16BA3AB5ADED03FC22BB12BF108B6EC28FC954F662746BE435D64A6B3BF8500310A05AE28B5569793A69D86EFA040A4D1B6EBF94CA7F6B99068743DD7147A6EEC3599F642161D9F64B2567C14468EB39BC09E187D218C96E19C4FD22E71553192F73F886E347E49F2EE527CF4DC495A113C96A0B3BA435620D850D272BA0F2ECFF11A82E60A3BF25C1DCD5EF53771AB95E341D970A0B5A533CB3C166DA6FD072B4C55D2196BE95F6E715EDB7F99410247F853ABFA9D40639F99C0173A8F50E7BF4E45AB6F618F24CB6524F72D298CE5DAF1466FB985D8BAE307BA0DF275B091FA3E9C91152DE5E771A8A20D653C028B213862DE370C5C5A473251F09367F789EA1A2762FA74AEE9F9F204E02D6C3289AD262460ED6132B4B43508621D4714C2FABAE2539540592C4462C209A2C5A3F3CEED291F3F3ECBC4542D4208C9D82980E9CAB4AEEBE9D8B77DA770FDAEC2F209C1031229E690CDBB926A5FA4B97184B2F217CAA84594C8A25A282E1AAA168970BF2019DD8D25A844FF94762D06E945AB061C1573D7B56826077EED8349652F34AE99A776FCE9640D8EB24CBF1C4F210A8BD9F45E76037E1262482F5DF10CF0A0869C52EC6E64E7056FCF9BFE71D3B6CB0A8638C5127FE8DCABA500DD345; BPMCSRF=jjZVd3aGq19rJdaTVTBUa.; BPMLOADER=u1j2vi4teop31jbswlowh0mf; UserName=83|117|112|101|114|118|105|115|111|114")
                .contentType(ContentType.JSON)
                .body(requestBody)
                .baseUri(auth.selectUrl(platformStr))
                .post(requestPathAdministrationService + "/UpdateOrCreateUser")
                .then().log().all()
                .statusCode(200)
                .extract().response();

    }

    @Step("Нахождение Юзера по Id")
    public static Response getUserById(Auth auth, String typeUrl, String id) {
        auth.authHttpORHttps(typeUrl);
        String requestPath = "/odata";
        if (typeUrl.equals("urlframework")) {
            requestPath = "/0" + requestPath;
        }
        LOG.info(typeUrl + "Нахождение Юзера по Id");
        return
                given()
                        .relaxedHTTPSValidation()  //отключение проверки сертификатов https
                        .when()
                        .header("ForceUseSession", "true")
                        //   .header("ForceUseSession", "true")
                        .header("Accept", "application/json;odata=verbose")
                        .header("BPMCSRF", auth.cookiesMap.get("BPMCSRF"))
                        .header("Cookie", auth.cookiesString)

                        .baseUri(auth.selectUrl(typeUrl))
                        /*     {{BaseURI}}/0/odata/{{CollectionName1}}({{ObjectId1}})/{{FieldName1}}/$value */
                        .get(requestPath + "/SysAdminUnit(" + id + ")")
                        .then().log().all()
                        .statusCode(200)
                        .extract().response();
                        /* не забыть обрезать пробел  (ZWNBSP)  str.replace("\uFEFF", "").replace("\u200B", "")
                        , чтобы удалить все невидимые пробелы из ответа */
    }

    @Step("Нахождение Юзера по параметру. Name или Id")
    public static Response getUserByParam(Auth auth, String typeUrl, String param) {
        auth.authHttpORHttps(typeUrl);
        String requestPath = "/odata";
        if (typeUrl.equals("urlframework")) {
            requestPath = "/0" + requestPath;
        }
        LOG.info(typeUrl + "Нахождение Юзера по параметру. Name или Id");
        // Если param — GUID, берём сущность по ключу. Иначе фильтруем по Name
        final Pattern guidPattern = Pattern.compile("(?i)^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$");
        String path;
        if (guidPattern.matcher(param).matches()) {
            path = requestPath + "/SysAdminUnit(" + param + ")";
        } else {
            String safeName = param.replace("'", "''"); // OData экранирование одинарных кавычек
            path = requestPath + "/SysAdminUnit?$filter=Name eq '" + safeName + "'&$select=Id,Name";
        }

        return given()
                .relaxedHTTPSValidation()  //отключение проверки сертификатов https
                .when()
                .header("ForceUseSession", "true")
                //   .header("ForceUseSession", "true")
                .header("Accept", "application/json;odata=verbose")
                .header("BPMCSRF", auth.cookiesMap.get("BPMCSRF"))
                .header("Cookie", auth.cookiesString)
                .baseUri(auth.selectUrl(typeUrl))
                .get(path)
                .then().log().all()
                .statusCode(200)
                .extract().response();
                        /* не забыть обрезать пробел  (ZWNBSP)  str.replace("\uFEFF", "").replace("\u200B", "")
                        , чтобы удалить все невидимые пробелы из ответа */
    }

    public static void main(String[] args) throws IOException {
        //AE9CFAF6-CD8C-4FEF-9ECB-32CDE707774B
        //Supervisor
        // 7F3B869F-34F3-4F20-AB4D-7480A5FDF647
        //   getUserById(new Auth(), "urlframework", "AE9CFAF6-CD8C-4FEF-9ECB-32CDE707774B");
        createUser("SVETuser", Platform.CoreNet8);
// 0/rest/AdministrationService/GetIsUserBlocked
        // 0/rest/SocialSubscriptionService/GetIsUserSubscribed
        // POST
        // https://alcm-bpms-005.bpmrnd.ru/0/rest/AdministrationService/UpdateOrCreateUser
    }

    @Step("Загрузка stands.properties")
    public void authProperti() {

        try (InputStream in = UserService.class.getClassLoader().getResourceAsStream("configs/stands.properties")) {
            properties.load(in);
            this.loginSupervisor = properties.getProperty("login");
            this.passSupervisor = properties.getProperty("password");
            this.url = properties.getProperty("url");
            //.replaceAll("^\"|\"$", "")
        } catch (IOException e) {
            System.out.println("Спасите, помогите, проперти не грузятся.");
            e.printStackTrace();
        }
        LOG.info("Загрузка проперти.");
    }

}
/*
{"jsonObject":"{\"Id\":\"d0d5b229-1778-4e85-9c51-fd5d39399195\",\"CreatedBy\":\"410006e1-ca4e-4502-a9ec-e54d922d2c00\",\"ModifiedBy\":\"410006e1-ca4e-4502-a9ec-e54d922d2c00\",\"Name\":\"SVETuser32603333\",\"SysAdminUnitType\":\"472e97c7-6bd7-df11-9b2a-001d60e938c6\",\"Contact\":\"f7aac61b-28fb-448c-86f1-f2a4a99c8cbb\",\"TimeZone\":null,\"UserPassword\":\"SVETuser3260!3333\",\"Active\":true,\"SynchronizeWithLDAP\":false,\"SysCulture\":\"1a778e3f-0a8e-e111-84a3-00155d054c03\",\"ConnectionType\":0,\"LDAPElement\":null,\"UserConnectionType\":\"df8ff7a2-c9bf-4f28-80b1-ed16fa77818d\"}","roleId":"a29a3ba5-4b0d-de11-9a51-005056c00008"}
*/
