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
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Contact {

    @JsonProperty("Zip")
    private String zip;

    @JsonProperty("AccountId")
    private String accountId;

    @JsonProperty("Email")
    private String email;

    @JsonProperty("ModifiedOn")
    private String modifiedOn;

    @JsonProperty("Address")
    private String address;

    @JsonProperty("GivenName")
    private String givenName;

    @JsonProperty("ProcessListeners")
    private int processListeners;

    @JsonProperty("MobilePhone")
    private String mobilePhone;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("AddressTypeId")
    private String addressTypeId;

    @JsonProperty("CreatedById")
    private String createdById;

    @JsonProperty("ContactPhoto@odata.mediaEditLink")
    private String contactPhotoOdataMediaEditLink;

    @JsonProperty("CountryId")
    private String countryId;

    @JsonProperty("ModifiedById")
    private String modifiedById;

    @JsonProperty("TypeId")
    private String typeId;

    @JsonProperty("ContactPhoto@odata.mediaContentType")
    private String contactPhotoOdataMediaContentType;

    @JsonProperty("GPSN")
    private String gPSN;

    @JsonProperty("DoNotUseFax")
    private boolean doNotUseFax;

    @JsonProperty("@odata.context")
    private String odataContext;

    @JsonProperty("Dear")
    private String dear;

    @JsonProperty("Id")
    private String id;

    @JsonProperty("DecisionRoleId")
    private String decisionRoleId;

    @JsonProperty("Confirmed")
    private boolean confirmed;

    @JsonProperty("ContactPhoto@odata.mediaReadLink")
    private String contactPhotoOdataMediaReadLink;

    @JsonProperty("BirthDate")
    private String birthDate;

    @JsonProperty("DoNotUseMail")
    private boolean doNotUseMail;

    @JsonProperty("IsNonActualEmail")
    private boolean isNonActualEmail;

    @JsonProperty("JobTitle")
    private String jobTitle;

    @JsonProperty("OwnerId")
    private String ownerId;

    @JsonProperty("CityId")
    private String cityId;

    @JsonProperty("Phone")
    private String phone;

    @JsonProperty("Completeness")
    private int completeness;

    @JsonProperty("GPSE")
    private String gPSE;

    @JsonProperty("IsEmailConfirmed")
    private boolean isEmailConfirmed;

    @JsonProperty("CreatedOn")
    private String createdOn;

    @JsonProperty("JobId")
    private String jobId;

    @JsonProperty("Notes")
    private String notes;

    @JsonProperty("Age")
    private int age;

    @JsonProperty("SalutationTypeId")
    private String salutationTypeId;

    @JsonProperty("DoNotUseSms")
    private boolean doNotUseSms;

    @JsonProperty("MiddleName")
    private String middleName;

    @JsonProperty("PhotoId")
    private String photoId;

    @JsonProperty("DoNotUseCall")
    private boolean doNotUseCall;

    @JsonProperty("GenderId")
    private String genderId;

    @JsonProperty("Skype")
    private String skype;

    @JsonProperty("HomePhone")
    private String homePhone;

    @JsonProperty("DepartmentId")
    private String departmentId;

    @JsonProperty("RegionId")
    private String regionId;

    @JsonProperty("DoNotUseEmail")
    private boolean doNotUseEmail;

    @JsonProperty("Surname")
    private String surname;

    @JsonProperty("LanguageId")
    private String languageId;

    private static final Random random = new Random();
    private static final Faker faker = new Faker(new Locale("ru"));

}
/*
{
    "@odata.context": "http://qa026wfmb.rnd.omnichannel.ru:8533/0/odata/$metadata#Contact/$entity",
    "Id": "410006e1-ca4e-4502-a9ec-e54d922d2c00",
    "Name": "Supervisor",
    "OwnerId": "00000000-0000-0000-0000-000000000000",
    "CreatedOn": "2019-07-15T20:04:57.2908376Z",
    "CreatedById": "410006e1-ca4e-4502-a9ec-e54d922d2c00",
    "ModifiedOn": "2020-06-26T13:30:25.9945528Z",
    "ModifiedById": "410006e1-ca4e-4502-a9ec-e54d922d2c00",
    "ProcessListeners": 0,
    "Dear": "",
    "SalutationTypeId": "7426ffb3-56e6-df11-971b-001d60e938c6",
    "GenderId": "eeac42ee-65b6-df11-831a-001d60e938c6",
    "AccountId": "e308b781-3c5b-4ecb-89ef-5c1ed4da488e",
    "DecisionRoleId": "00000000-0000-0000-0000-000000000000",
    "TypeId": "60733efc-f36b-1410-a883-16d83cab0980",
    "JobId": "00000000-0000-0000-0000-000000000000",
    "JobTitle": "",
    "DepartmentId": "00000000-0000-0000-0000-000000000000",
    "BirthDate": "0001-01-01T00:00:00Z",
    "Phone": "",
    "MobilePhone": "",
    "HomePhone": "",
    "Skype": "",
    "Email": "",
    "AddressTypeId": "00000000-0000-0000-0000-000000000000",
    "Address": "",
    "CityId": "00000000-0000-0000-0000-000000000000",
    "RegionId": "00000000-0000-0000-0000-000000000000",
    "Zip": "",
    "CountryId": "00000000-0000-0000-0000-000000000000",
    "DoNotUseEmail": true,
    "DoNotUseCall": true,
    "DoNotUseFax": false,
    "DoNotUseSms": true,
    "DoNotUseMail": true,
    "Notes": "",
    "ContactPhoto@odata.mediaEditLink": "Contact(410006e1-ca4e-4502-a9ec-e54d922d2c00)/ContactPhoto",
    "ContactPhoto@odata.mediaReadLink": "Contact(410006e1-ca4e-4502-a9ec-e54d922d2c00)/ContactPhoto",
    "ContactPhoto@odata.mediaContentType": "application/octet-stream",
    "PhotoId": "00000000-0000-0000-0000-000000000000",
    "GPSN": "",
    "GPSE": "",
    "Surname": "",
    "GivenName": "",
    "MiddleName": "",
    "Confirmed": false,
    "IsNonActualEmail": false,
    "Completeness": 90,
    "LanguageId": "00000000-0000-0000-0000-000000000000",
    "Age": 0,
    "IsEmailConfirmed": false
}

 */