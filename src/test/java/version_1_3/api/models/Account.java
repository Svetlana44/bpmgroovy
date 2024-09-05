package version_1_3.api.models;

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
public class Account {

    private static final Random random = new Random();
    private static final Faker faker = new Faker(new Locale("ru"));
    @JsonProperty("Zip")
    private String zip;
    @JsonProperty("PrimaryContactId")
    private String primaryContactId;
    @JsonProperty("ModifiedOn")
    private String modifiedOn;
    @JsonProperty("Address")
    private String address;
    @JsonProperty("AccountLogoId")
    private String accountLogoId;
    @JsonProperty("ProcessListeners")
    private int processListeners;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("AddressTypeId")
    private String addressTypeId;
    @JsonProperty("OwnerId")
    private String ownerId;
    @JsonProperty("CreatedById")
    private String createdById;
    @JsonProperty("CityId")
    private String cityId;
    @JsonProperty("AdditionalPhone")
    private String additionalPhone;
    @JsonProperty("Phone")
    private String phone;
    @JsonProperty("Completeness")
    private int completeness;
    @JsonProperty("AUM")
    private String aUM;
    @JsonProperty("GPSE")
    private String gPSE;
    @JsonProperty("CreatedOn")
    private String createdOn;
    @JsonProperty("Notes")
    private String notes;
    @JsonProperty("EmployeesNumberId")
    private String employeesNumberId;
    @JsonProperty("ParentId")
    private String parentId;
    @JsonProperty("CountryId")
    private String countryId;
    @JsonProperty("ModifiedById")
    private String modifiedById;
    @JsonProperty("TypeId")
    private String typeId;
    @JsonProperty("IndustryId")
    private String industryId;
    @JsonProperty("GPSN")
    private String gPSN;
    @JsonProperty("AccountCategoryId")
    private String accountCategoryId;
    @JsonProperty("Logo@odata.mediaReadLink")
    private String logoOdataMediaReadLink;
    @JsonProperty("Logo@odata.mediaContentType")
    private String logoOdataMediaContentType;
    @JsonProperty("OwnershipId")
    private String ownershipId;
    @JsonProperty("@odata.context")
    private String odataContext;
    @JsonProperty("Logo@odata.mediaEditLink")
    private String logoOdataMediaEditLink;
    @JsonProperty("Code")
    private String code;
    @JsonProperty("AlternativeName")
    private String alternativeName;
    @JsonProperty("Web")
    private String web;
    @JsonProperty("PriceListId")
    private String priceListId;
    @JsonProperty("Id")
    private String id;
    @JsonProperty("RegionId")
    private String regionId;
    @JsonProperty("AnnualRevenueId")
    private String annualRevenueId;
    @JsonProperty("Fax")
    private String fax;
}


/*
{
    "@odata.context": "https://qa036wfmb.rnd.omnichannel.ru/0/odata/$metadata#Account/$entity",
    "Id": "e308b781-3c5b-4ecb-89ef-5c1ed4da488e",
    "Name": "Наша компания",
    "OwnerId": "410006e1-ca4e-4502-a9ec-e54d922d2c00",
    "CreatedOn": "2019-07-15T20:04:49.7564045Z",
    "CreatedById": "410006e1-ca4e-4502-a9ec-e54d922d2c00",
    "ModifiedOn": "2019-07-15T20:05:17.6650097Z",
    "ModifiedById": "410006e1-ca4e-4502-a9ec-e54d922d2c00",
    "ProcessListeners": 0,
    "OwnershipId": "00000000-0000-0000-0000-000000000000",
    "PrimaryContactId": "00000000-0000-0000-0000-000000000000",
    "ParentId": "00000000-0000-0000-0000-000000000000",
    "IndustryId": "00000000-0000-0000-0000-000000000000",
    "Code": "43",
    "TypeId": "57412fad-53e6-df11-971b-001d60e938c6",
    "Phone": "",
    "AdditionalPhone": "",
    "Fax": "",
    "Web": "",
    "AddressTypeId": "00000000-0000-0000-0000-000000000000",
    "Address": "",
    "CityId": "00000000-0000-0000-0000-000000000000",
    "RegionId": "00000000-0000-0000-0000-000000000000",
    "Zip": "",
    "CountryId": "00000000-0000-0000-0000-000000000000",
    "AccountCategoryId": "00000000-0000-0000-0000-000000000000",
    "EmployeesNumberId": "00000000-0000-0000-0000-000000000000",
    "AnnualRevenueId": "00000000-0000-0000-0000-000000000000",
    "Notes": "",
    "Logo@odata.mediaEditLink": "Account(e308b781-3c5b-4ecb-89ef-5c1ed4da488e)/Logo",
    "Logo@odata.mediaReadLink": "Account(e308b781-3c5b-4ecb-89ef-5c1ed4da488e)/Logo",
    "Logo@odata.mediaContentType": "application/octet-stream",
    "AlternativeName": "",
    "GPSN": "",
    "GPSE": "",
    "Completeness": 15,
    "PriceListId": "00000000-0000-0000-0000-000000000000",
    "AccountLogoId": "00000000-0000-0000-0000-000000000000",
    "AUM": ""
}
* */