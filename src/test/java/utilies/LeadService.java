package utilies;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import version_1_3.api.jsonschemas.FullLead;
import version_1_3.api.jsonschemas.IDContact;
import version_1_3.api.models.IDs;
import version_1_3.api.models.Lead;

import java.util.Locale;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public class LeadService {
    private static final Logger LOG = LoggerFactory.getLogger(LeadService.class.getName());
    private static final Faker faker = new Faker(new Locale("ru"));
    private static final Random random = new Random();
    static int randomNumber = Math.abs(random.nextInt(333));

    static IDs ids;
    private static boolean flagGetIDs = false;

    //  AccountId
    @Step("Получение ID всех Компаний с проверкой соответствия json schema")
    public static Response getAllIdOfAccountsFrame(Auth auth, String typeUrl) {
        //       auth.authHttpORHttps("urlframework");
        auth.authHttpORHttps(typeUrl);
        LOG.info(typeUrl + "===> Получение ID всех Компаний с проверкой соответствия IDContactSchema");
        String requestPath = "/odata";
        if (typeUrl.equals("urlframework")) {
            requestPath = "/0" + requestPath;
        }
        return given()
                .relaxedHTTPSValidation()  //отключение проверки сертификатов https
                .when()
                .header("ForceUseSession", "true")
                .header("Accept", "application/json;odata=verbose")
                .header("BPMCSRF", auth.cookiesMap.get("BPMCSRF"))
                .header("Cookie", auth.cookiesString)
                .contentType(ContentType.JSON)
                .baseUri(auth.selectUrl(typeUrl))
                .get(requestPath + "/Account?$select=Id")
                .then().log().all()
                .statusCode(200)
                .assertThat()
                //схема не та, но подходит, можно скопировать схему в accountschema
                .body(matchesJsonSchema(IDContact.IDContactSchema))
                .extract().response();
    }

    @Step("CORE===> Получение ID всех Компаний с проверкой соответствия json schema")
    public static Response getAllIdOfAccountsCore(Auth auth) {
        auth.authHttpORHttps("urlwincore");
        LOG.info("CORE===> Получение ID всех Компаний с проверкой соответствия IDContactSchema");
        return given()
                .relaxedHTTPSValidation()  //отключение проверки сертификатов https
                .when()
                .header("ForceUseSession", "true")
                .header("Accept", "application/json;odata=verbose")
                .header("BPMCSRF", auth.cookiesMap.get("BPMCSRF"))
                .header("Cookie", auth.cookiesString)
                .contentType(ContentType.JSON)
                .baseUri(auth.selectUrl("urlwincore"))
                .get("/odata/Account?$select=Id")
                .then().log().all()
                .statusCode(200)
                .assertThat()
                .body(matchesJsonSchema(IDContact.IDContactSchema))
                .extract().response();
    }

    /*  сделать схему на компанию полностью, для этого нужен запрос-ответ  */
    @Step("Получение Лида (LEAD) по ID с проверкой соответствия json schema  (FullLeadSchema)")
    public static Response getLeadById(Auth auth, String typeUrl, String id) {
        auth.authHttpORHttps(typeUrl);
        LOG.info(typeUrl + "===> Получение Лида (Lead) по ID с проверкой соответствия FullLeadSchema");
        String requestPath = "/odata";
        if (typeUrl.equals("urlframework")) {
            requestPath = "/0" + requestPath;
        }
        return given()
                .relaxedHTTPSValidation()  //отключение проверки сертификатов https
                .when()
                .header("ForceUseSession", "true")
                .header("Accept", "application/json;odata=verbose")
                .header("BPMCSRF", auth.cookiesMap.get("BPMCSRF"))
                .header("Cookie", auth.cookiesString)
                .contentType(ContentType.JSON)
                .baseUri(auth.selectUrl(typeUrl))
                .get(requestPath + "/Lead(" + id + ")")
                .then().log().all()
                .statusCode(200)
                .assertThat()
                .body(matchesJsonSchema(FullLead.FullLeadSchema))
                .extract().response();
    }

    @Step("Генерация рандомного Полного Лида leadTypeStatusId Discovered=5b3d1046-fc16-45c8-a5a1-298dfc857546 ")
    /* нужно списки данных сделать общими на класс, чтобы быстрее делать несколько Контрагентов  класс IDs при инициализации запрашивает IDs */
    public static Lead generateRandomFullLead(Auth auth, String typeUrl) {
        /*     List<String> ownerIDs = IDparser.parsIdFromIdResponseToList(getAllIdOfContacts(auth, "urlframework"));    */

        if (!flagGetIDs) {
            ids = new IDs(auth, typeUrl);
            flagGetIDs = true;
        }
        String name = faker.company().name();
        String leadType = ids.leadTypeIDs.get(ids.getRandomNumberId(ids.leadTypeIDs));
        String account = "SVET_" + name + randomNumber;
        return Lead.builder()
                .account(account)
                //      .contact(ids.contactIDs.get(ids.getRandomNumberId(ids.contactIDs)))  // из-за этого подвисает
                //     .ownerId(ids.ownerIDs.get(ids.getRandomNumberId(ids.ownerIDs)))   //если это поле писать не null, задачи при МИСЛ переводе в др статус не создаются автоматом
                .leadTypeId(leadType)
                .leadTypeStatusId("5b3d1046-fc16-45c8-a5a1-298dfc857546")  //  Discovered
                .qualifyStatusId("d790a45d-03ff-4ddb-9dea-8087722c582c")   //Квалификация
                /* с этим статусом нужно уточнить поля в БД
                при переводе в продажу, создаётся контрагент из названия контрагента в лиде
                и в БД заполняется поле "QualifiedAccountId" этим Контрагентом из таблицы Account
                + заполняется "QualificationProcessId" ни с чем не связан в БД, просто id
                + "QualificationPassed"  становится true
                т.е. создаётся контрагент из лида и три поля в БД меняются */
                //    .qualifyStatusId("ceb70b3c-985f-4867-ae7c-88f9dd710688") //  Перевод в продажу с этим статусом нужно уточнить поля в БД Перевод в продажу   ceb70b3c-985f-4867-ae7c-88f9dd710688
                .registerMethodId("240ab9c6-4d7c-4688-b380-af44dd147d7a")  //неизвестный id у всех вручную созданных
                .salesChannelId("3c3865f2-ada4-480c-ac91-e2d39c5bbaf9")    //тоже непонятно, даже таблицы такой нет, но у всех лидов такой id
                //         .leadName(leadType + " / " + account) // автоматом подставляется строка с конкатенацией
                .showDistributionPage(true)
                //     .businesPhone(faker.phoneNumber().phoneNumber())
                //     .mobilePhone(faker.phoneNumber().phoneNumber())
                //     .countryId(ids.countryIDs.get(ids.getRandomNumberId(ids.countryIDs)))
                .createdById("c943b0df-5ce5-4d7a-a9d3-616035cec548")
                .build();
    }

    @Step("Добавление нового рандомного Лида")
    public static Response addRandomLead(Auth auth, String typeUrl, Lead lead) {

        //     auth.authHttpORHttps("urlframework");
        auth.authHttpORHttps(typeUrl);
        String requestPath = "/odata";
        if (typeUrl.equals("urlframework")) {
            requestPath = "/0" + requestPath;
        }
        LOG.info(typeUrl + "==> Добавление нового рандомного Лида");
        return given()
                .relaxedHTTPSValidation()  //отключение проверки сертификатов https
                .when()
                .header("ForceUseSession", "true")
                .header("Accept", "application/json;odata=verbose")
                .header("BPMCSRF", auth.cookiesMap.get("BPMCSRF"))
                .header("Cookie", auth.cookiesString)
                .contentType(ContentType.JSON)
                .body(lead)
                .baseUri(auth.selectUrl(typeUrl))
                .post(requestPath + "/Lead")
                .then().log().all()
                .statusCode(201)
                .extract().response();
    }

    //  AccounTypetId
    @Step("Получение ID всех Типов потребностей с проверкой соответствия json schema")
    public static Response getAllIdOfLeadType(Auth auth, String typeUrl) {
        //     auth.authHttpORHttps("urlframework");
        auth.authHttpORHttps(typeUrl);
        LOG.info(typeUrl + "==> Получение ID всех Типов потребностей лидов с проверкой соответствия IDContactSchema");
        String requestPath = "/odata";
        if (typeUrl.equals("urlframework")) {
            requestPath = "/0" + requestPath;
        }
        return given()
                .relaxedHTTPSValidation()  //отключение проверки сертификатов https
                .when()
                .header("ForceUseSession", "true")
                .header("Accept", "application/json;odata=verbose")
                .header("BPMCSRF", auth.cookiesMap.get("BPMCSRF"))
                .header("Cookie", auth.cookiesString)
                .contentType(ContentType.JSON)
                .baseUri(auth.selectUrl(typeUrl))
                .get(requestPath + "/LeadType?$select=Id")
                .then().log().all()
                .statusCode(200)
                .assertThat()
                //схема не та, но подходит, можно скопировать схему в accountschema
                .body(matchesJsonSchema(IDContact.IDContactSchema))
                .extract().response();
    }

    public static void main(String[] args) {
        Auth auth = new Auth();
        //       getLeadById(auth, "urlwincore", "aed07d10-ad1a-45e3-a95c-8d1715cb7569");
        //       getAllIdOfLeadType(auth, "urlwincore");

        for (int i = 0; i < 1; i++) {
            addRandomLead(auth, "urlwincore", generateRandomFullLead(auth, "urlwincore"));
            //    addRandomAccount(auth, "urllinuxcore", generateRandomFullAccount(auth, "urllinuxcore"));
        }
    }
}
/*
{
    "@odata.context": "https://qa-bpms-012.rnd.omnichannel.ru:5002/odata/$metadata#Lead/$entity",
    "Id": "aed07d10-ad1a-45e3-a95c-8d1715cb7569",
    "CreatedOn": "2024-10-02T13:26:10.246402Z",
    "CreatedById": "c943b0df-5ce5-4d7a-a9d3-616035cec548",
    "ModifiedOn": "2024-10-02T13:27:45.272454Z",
    "ModifiedById": "c943b0df-5ce5-4d7a-a9d3-616035cec548",
    "Notes": "",
    "Account": "svet8",
    "Contact": "",
    "TitleId": "00000000-0000-0000-0000-000000000000",
    "FullJobTitle": "",
    "StatusId": "00000000-0000-0000-0000-000000000000",
    "InformationSourceId": "00000000-0000-0000-0000-000000000000",
    "IndustryId": "00000000-0000-0000-0000-000000000000",
    "AnnualRevenueId": "00000000-0000-0000-0000-000000000000",
    "EmployeesNumberId": "00000000-0000-0000-0000-000000000000",
    "BusinesPhone": "",
    "MobilePhone": "",
    "Email": "",
    "Fax": "",
    "Website": "",
    "AddressTypeId": "00000000-0000-0000-0000-000000000000",
    "CountryId": "00000000-0000-0000-0000-000000000000",
    "RegionId": "00000000-0000-0000-0000-000000000000",
    "CityId": "00000000-0000-0000-0000-000000000000",
    "Zip": "",
    "Address": "",
    "DoNotUseEmail": false,
    "DoNotUsePhone": false,
    "DoNotUseFax": false,
    "DoNotUseSMS": false,
    "DoNotUseMail": false,
    "Commentary": "",
    "QualifiedContactId": "00000000-0000-0000-0000-000000000000",
    "QualifiedAccountId": "a6a906c2-9ba6-4ea7-8bcf-89ec784ff5fc",
    "LeadName": "Программное обеспечение / svet8",
    "ProcessListeners": 0,
    "CountryStr": "",
    "RegionStr": "",
    "CityStr": "",
    "WebFormId": "00000000-0000-0000-0000-000000000000",
    "LeadTypeId": "84c42d58-10af-4a63-9ca7-845bab573daa",
    "LeadTypeStatusId": "5b3d1046-fc16-45c8-a5a1-298dfc857546",
    "LeadDisqualifyReasonId": "00000000-0000-0000-0000-000000000000",
    "AccountCategoryId": "00000000-0000-0000-0000-000000000000",
    "AccountOwnershipId": "00000000-0000-0000-0000-000000000000",
    "DepartmentId": "00000000-0000-0000-0000-000000000000",
    "GenderId": "00000000-0000-0000-0000-000000000000",
    "JobId": "00000000-0000-0000-0000-000000000000",
    "DecisionRoleId": "00000000-0000-0000-0000-000000000000",
    "QualifyStatusId": "51adc3ec-3503-4b10-a00b-8be3b0e11f08",
    "Dear": "",
    "QualificationProcessId": "00000000-0000-0000-0000-000000000000",
    "OwnerId": "00000000-0000-0000-0000-000000000000",
    "RemindToOwner": false,
    "SalesOwnerId": "00000000-0000-0000-0000-000000000000",
    "Budget": 0.00,
    "MeetingDate": "0001-01-01T00:00:00Z",
    "DecisionDate": "0001-01-01T00:00:00Z",
    "ShowDistributionPage": true,
    "BpmHref": "",
    "BpmRef": "",
    "RegisterMethodId": "240ab9c6-4d7c-4688-b380-af44dd147d7a",
    "LeadSourceId": "00000000-0000-0000-0000-000000000000",
    "LeadMediumId": "00000000-0000-0000-0000-000000000000",
    "OpportunityDepartmentId": "00000000-0000-0000-0000-000000000000",
    "IdentificationPassed": false,
    "CampaignId": "00000000-0000-0000-0000-000000000000",
    "BulkEmailId": "00000000-0000-0000-0000-000000000000",
    "Qualified": 0,
    "SaleParticipant": 0,
    "QualifiedPercent": 0.00,
    "SalePercent": 0.00,
    "StartLeadManagementProcess": false,
    "SaleType": "Opportunity",
    "Score": 0.00,
    "QualificationPassed": true,
    "EventId": "00000000-0000-0000-0000-000000000000",
    "NextActualizationDate": "0001-01-01T00:00:00Z",
    "BpmSessionId": "00000000-0000-0000-0000-000000000000",
    "PredictiveScore": 0,
    "MovedToFinalStateOn": "2024-10-02T13:27:45.384081Z",
    "OpportunityId": "00000000-0000-0000-0000-000000000000",
    "PartnerId": "00000000-0000-0000-0000-000000000000",
    "PartnerOwnerId": "00000000-0000-0000-0000-000000000000",
    "PartnerTypeId": "00000000-0000-0000-0000-000000000000",
    "SalesChannelId": "3c3865f2-ada4-480c-ac91-e2d39c5bbaf9",
    "OrderId": "00000000-0000-0000-0000-000000000000",
    "LeadGenExtendedLeadInfoId": "00000000-0000-0000-0000-000000000000",
    "SocialMetadata": ""
}
*/