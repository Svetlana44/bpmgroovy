package utilies;

import enams.Platform;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ui.seleniumwebdriver.pagesTests.BaseTests;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;


public class GrantsPermissionService extends BaseTests {
    private static final Logger LOG = LoggerFactory.getLogger(GrantsPermissionService.class.getName());
    private final Properties properties = new Properties();
    private String loginSupervisor;
    private String passSupervisor;
    private String url;

    /**
     * Метод для выдачи прав на операцию
     * нужно указать Id операции и массив Id пользователей системы.
     * canExecute Флаг разрешения выполнения данной операции.
     * выдача прав:
     * https://alcm-bpms-005.bpmrnd.ru/rest/RightsService/SetAdminOperationGrantee
     * тело:
     * {"adminOperationId":"c1fce04a-473f-49b2-9100-a06c449c6fec","adminUnitIds":["0b964d58-3cd3-4de3-9480-0eb62dac6cb7","0b14a01a-4ec9-4bad-b73f-df3b47b43669"],"canExecute":true}
     * ответ: 200Ок
     * {SetAdminOperationGranteeResult: "{"Success":true,"ExMessage":""}"}
     * SetAdminOperationGranteeResult: "{\"Success\":true,\"ExMessage\":\"\"}"
     * Посмотреть права в mssql
     * SELECT TOP (1000) [Id]
     * , (SELECT [Name] FROM [SVET2_Full_House_1.9.0.4026_Net8_MSSQL].[dbo].[SysAdminUnit] as t WHERE t.Id=tg.SysAdminUnitId)
     * ,[SysAdminOperationId]
     * ,(SELECT Code FROM [SVET2_Full_House_1.9.0.4026_Net8_MSSQL].[dbo].[SysAdminOperation] tn WHERE tn.Id=tg.SysAdminOperationId) name
     * ,[SysAdminUnitId]
     * ,[CanExecute]
     * ,[Position]
     * ,[ProcessListeners]
     * FROM [SVET2_Full_House_1.9.0.4026_Net8_MSSQL].[dbo].[SysAdminOperationGrantee] tg
     * WHERE SysAdminOperationId in (SELECT Id FROM [SVET2_Full_House_1.9.0.4026_Net8_MSSQL].[dbo].[SysAdminOperation] tn WHERE tn.Code in ('CanViewConfiguration','CanManageSolution')
     * )
     */
    @Step("Метод для выдачи/выключения(true/false) прав на операцию пользователю системы.")
    public static Response SetAdminOperationGrantee(String adminOperationId, String[] adminUnitIds, boolean canExecute, Platform platform) throws IOException {
        Auth auth = new Auth();
        String platformStr = (platform == Platform.Frame) ? "urlframework" : "urllinuxcore";
        auth.authHttpORHttps(platformStr);
        String requestPathOData = "/odata";
        String RightsService = "/rest/RightsService/SetAdminOperationGrantee";
        if (platformStr.equals("urlframework")) {
            requestPathOData = "/0" + requestPathOData;
            RightsService = "/0" + RightsService;
        }
        // String[] adminUnitIds = {"0b964d58-3cd3-4de3-9480-0eb62dac6cb7", "0b14a01a-4ec9-4bad-b73f-df3b47b43669"};
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("adminOperationId", adminOperationId);
        requestBody.put("adminUnitIds", Arrays.asList(adminUnitIds));
        requestBody.put("canExecute", canExecute);
        LOG.info("Request to " + platformStr + ": " + RightsService + "/SetAdminOperationGrantee");
        LOG.info("Request body - adminOperationId: " + adminOperationId + ", adminUnitIds: " + adminUnitIds.length + " items, canExecute: " + canExecute);

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
                .post(RightsService)
                .then().log().all()
                .statusCode(200)
                .extract().response();

    }

    public static void main(String[] args) throws IOException {
        String adminOperationId = "C1FCE04A-473F-49B2-9100-A06C449C6FEC";// CanViewConfiguration
        //   String[] adminUnitIds = {"0B14A01A-4EC9-4BAD-B73F-DF3B47B43669"};//SVETuser

        Auth auth = new Auth();
        Platform platform = Platform.CoreNet8;
        String platformStr = (platform == Platform.Frame) ? "urlframework" : "urllinuxcore";

        // Получаем пользователя по имени
        Response userByParam = UserService.getUserByParam(auth, platformStr, "SVETuser");

        // Извлекаем Id (для фильтра по имени вернется коллекция)
        String adminUnitId = userByParam.jsonPath().getString("value[0].Id");
        // Защита от отсутствия пользователя
        if (adminUnitId == null || adminUnitId.isBlank()) {
            throw new IllegalStateException("Пользователь 'SVETuser' не найден, adminUnitId пустой");
        }

        // Формируем массив GUID-ов без null
        String[] adminUnitIds = new String[]{adminUnitId};


        boolean canExecute = true;
        // Выдача права
        SetAdminOperationGrantee(adminOperationId, adminUnitIds, canExecute, platform);
        LOG.info("Права выданы");

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
