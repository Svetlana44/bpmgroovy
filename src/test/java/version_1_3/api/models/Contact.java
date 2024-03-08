package version_1_3.api.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/* Для того чтобы заменить пустое значение null на пустую строку в Lombok,
 можно использовать аннотацию @JsonInclude с параметром Include.NON_NULL.
  Добавьте аннотацию @JsonInclude(Include.NON_NULL) к вашему классу Contact,
   и это позволит сериализатору Jackson исключать значения null при преобразовании объекта в JSON.  */
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
}