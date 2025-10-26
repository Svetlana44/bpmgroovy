package api.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Locale;
import java.util.Random;

/*
 Для того чтобы заменить пустое значение null на пустую строку в Lombok,
 можно использовать аннотацию @JsonInclude с параметром Include.NON_NULL.
  Добавьте аннотацию @JsonInclude(Include.NON_NULL) к вашему классу Contact,
   и это позволит сериализатору Jackson исключать значения null при преобразовании объекта в JSON.
Builder от ломбок для удобного построения объектов вместо get/set и присвоения значений
*/
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Lead {
    private static final Random random = new Random();
    private static final Faker faker = new Faker(new Locale("ru"));
    @JsonProperty("FullJobTitle")
    private String fullJobTitle;

    @JsonProperty("Zip")
    private String zip;

    @JsonProperty("Account")
    private String account;

    @JsonProperty("SaleType")
    private String saleType;

    @JsonProperty("Email")
    private String email;

    @JsonProperty("ModifiedOn")
    private String modifiedOn;

    @JsonProperty("Address")
    private String address;

    @JsonProperty("PartnerOwnerId")
    private String partnerOwnerId;

    @JsonProperty("PartnerId")
    private String partnerId;

    @JsonProperty("LeadGenExtendedLeadInfoId")
    private String leadGenExtendedLeadInfoId;

    @JsonProperty("MobilePhone")
    private String mobilePhone;

    @JsonProperty("ProcessListeners")
    private int processListeners;

    @JsonProperty("AccountOwnershipId")
    private String accountOwnershipId;

    @JsonProperty("DoNotUsePhone")
    private boolean doNotUsePhone;

    @JsonProperty("AddressTypeId")
    private String addressTypeId;

    @JsonProperty("OpportunityDepartmentId")
    private String opportunityDepartmentId;

    @JsonProperty("CreatedById")
    private String createdById;

    @JsonProperty("BpmHref")
    private String bpmHref;

    @JsonProperty("QualificationProcessId")
    private String qualificationProcessId;

    @JsonProperty("EventId")
    private String eventId;

    @JsonProperty("RegionStr")
    private String regionStr;

    @JsonProperty("BpmSessionId")
    private String bpmSessionId;

    @JsonProperty("SalesOwnerId")
    private String salesOwnerId;

    @JsonProperty("CountryId")
    private String countryId;

    @JsonProperty("Commentary")
    private String commentary;

    @JsonProperty("ModifiedById")
    private String modifiedById;

    @JsonProperty("LeadDisqualifyReasonId")
    private String leadDisqualifyReasonId;

    @JsonProperty("AccountCategoryId")
    private String accountCategoryId;

    @JsonProperty("DoNotUseFax")
    private boolean doNotUseFax;

    @JsonProperty("BpmRef")
    private String bpmRef;

    @JsonProperty("RegisterMethodId")
    private String registerMethodId;

    @JsonProperty("QualifiedAccountId")
    private String qualifiedAccountId;

    @JsonProperty("WebFormId")
    private String webFormId;

    @JsonProperty("OrderId")
    private String orderId;

    @JsonProperty("LeadTypeStatusId")
    private String leadTypeStatusId;

    @JsonProperty("MovedToFinalStateOn")
    private String movedToFinalStateOn;

    @JsonProperty("SalePercent")
    private Object salePercent;

    @JsonProperty("CityStr")
    private String cityStr;

    @JsonProperty("SocialMetadata")
    private String socialMetadata;

    @JsonProperty("NextActualizationDate")
    private String nextActualizationDate;

    @JsonProperty("Dear")
    private String dear;

    @JsonProperty("Score")
    private Object score;

    @JsonProperty("LeadMediumId")
    private String leadMediumId;

    @JsonProperty("SalesChannelId")
    private String salesChannelId;

    @JsonProperty("StatusId")
    private String statusId;

    @JsonProperty("QualifiedPercent")
    private Object qualifiedPercent;

    @JsonProperty("QualifiedContactId")
    private String qualifiedContactId;

    @JsonProperty("Id")
    private String id;

    @JsonProperty("InformationSourceId")
    private String informationSourceId;

    @JsonProperty("AnnualRevenueId")
    private String annualRevenueId;

    @JsonProperty("DecisionRoleId")
    private String decisionRoleId;

    @JsonProperty("TitleId")
    private String titleId;

    @JsonProperty("DoNotUseMail")
    private boolean doNotUseMail;

    @JsonProperty("QualifyStatusId")
    private String qualifyStatusId;

    @JsonProperty("Website")
    private String website;

    @JsonProperty("LeadSourceId")
    private String leadSourceId;

    @JsonProperty("LeadTypeId")
    private String leadTypeId;

    @JsonProperty("MeetingDate")
    private String meetingDate;

    @JsonProperty("CityId")
    private String cityId;

    @JsonProperty("OwnerId")
    private String ownerId;

    @JsonProperty("CampaignId")
    private String campaignId;

    @JsonProperty("OpportunityId")
    private String opportunityId;

    @JsonProperty("IdentificationPassed")
    private boolean identificationPassed;

    @JsonProperty("Budget")
    private Object budget;

    @JsonProperty("DecisionDate")
    private String decisionDate;

    @JsonProperty("CreatedOn")
    private String createdOn;

    @JsonProperty("BulkEmailId")
    private String bulkEmailId;

    @JsonProperty("Notes")
    private String notes;

    @JsonProperty("JobId")
    private String jobId;

    @JsonProperty("EmployeesNumberId")
    private String employeesNumberId;

    @JsonProperty("RemindToOwner")
    private boolean remindToOwner;

    @JsonProperty("IndustryId")
    private String industryId;

    @JsonProperty("PartnerTypeId")
    private String partnerTypeId;

    @JsonProperty("PredictiveScore")
    private int predictiveScore;

    @JsonProperty("DoNotUseSMS")
    private boolean doNotUseSMS;

    @JsonProperty("Qualified")
    private int qualified;

    @JsonProperty("Contact")
    private String contact;

    @JsonProperty("GenderId")
    private String genderId;

    @JsonProperty("QualificationPassed")
    private boolean qualificationPassed;

    @JsonProperty("BusinesPhone")
    private String businesPhone;

    @JsonProperty("ShowDistributionPage")
    private boolean showDistributionPage;

    @JsonProperty("CountryStr")
    private String countryStr;

    @JsonProperty("StartLeadManagementProcess")
    private boolean startLeadManagementProcess;

    @JsonProperty("RegionId")
    private String regionId;

    @JsonProperty("DoNotUseEmail")
    private boolean doNotUseEmail;

    @JsonProperty("DepartmentId")
    private String departmentId;

    @JsonProperty("Fax")
    private String fax;

    @JsonProperty("LeadName")
    private String leadName;

    @JsonProperty("SaleParticipant")
    private int saleParticipant;
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