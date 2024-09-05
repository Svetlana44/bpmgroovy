package version_1_3.api.jsonschemas;

public class FullAccount {
    public static String FullAccountSchema = """
            {
                 "$schema": "http://json-schema.org/draft-07/schema#",
                 "type": "object",
                 "properties": {
                     "@odata.context": {
                         "type": "string"
                     },
                     "Id": {
                         "type": "string"
                     },
                     "Name": {
                         "type": "string"
                     },
                     "OwnerId": {
                         "type": "string"
                     },
                     "CreatedOn": {
                         "type": "string"
                     },
                     "CreatedById": {
                         "type": "string"
                     },
                     "ModifiedOn": {
                         "type": "string"
                     },
                     "ModifiedById": {
                         "type": "string"
                     },
                     "ProcessListeners": {
                         "type": "integer"
                     },
                     "OwnershipId": {
                         "type": "string"
                     },
                     "PrimaryContactId": {
                         "type": "string"
                     },
                     "ParentId": {
                         "type": "string"
                     },
                     "IndustryId": {
                         "type": "string"
                     },
                     "Code": {
                         "type": "string"
                     },
                     "TypeId": {
                         "type": "string"
                     },
                     "Phone": {
                         "type": "string"
                     },
                     "AdditionalPhone": {
                         "type": "string"
                     },
                     "Fax": {
                         "type": "string"
                     },
                     "Web": {
                         "type": "string"
                     },
                     "AddressTypeId": {
                         "type": "string"
                     },
                     "Address": {
                         "type": "string"
                     },
                     "CityId": {
                         "type": "string"
                     },
                     "RegionId": {
                         "type": "string"
                     },
                     "Zip": {
                         "type": "string"
                     },
                     "CountryId": {
                         "type": "string"
                     },
                     "AccountCategoryId": {
                         "type": "string"
                     },
                     "EmployeesNumberId": {
                         "type": "string"
                     },
                     "AnnualRevenueId": {
                         "type": "string"
                     },
                     "Notes": {
                         "type": "string"
                     },
                     "Logo@odata.mediaEditLink": {
                         "type": "string"
                     },
                     "Logo@odata.mediaReadLink": {
                         "type": "string"
                     },
                     "Logo@odata.mediaContentType": {
                         "type": "string"
                     },
                     "AlternativeName": {
                         "type": "string"
                     },
                     "GPSN": {
                         "type": "string"
                     },
                     "GPSE": {
                         "type": "string"
                     },
                     "Completeness": {
                         "type": "integer"
                     },
                     "PriceListId": {
                         "type": "string"
                     },
                     "AccountLogoId": {
                         "type": "string"
                     },
                     "AUM": {
                         "type": "string"
                     }
                 },
                 "additionalProperties": false,
                 "required": [
                     "@odata.context",
                     "Id",
                     "Name",
                     "OwnerId",
                     "CreatedOn",
                     "CreatedById",
                     "ModifiedOn",
                     "ModifiedById",
                     "ProcessListeners",
                     "OwnershipId",
                     "PrimaryContactId",
                     "ParentId",
                     "IndustryId",
                     "Code",
                     "TypeId",
                     "Phone",
                     "AdditionalPhone",
                     "Fax",
                     "Web",
                     "AddressTypeId",
                     "Address",
                     "CityId",
                     "RegionId",
                     "Zip",
                     "CountryId",
                     "AccountCategoryId",
                     "EmployeesNumberId",
                     "AnnualRevenueId",
                     "Notes",
                     "Logo@odata.mediaEditLink",
                     "Logo@odata.mediaReadLink",
                     "Logo@odata.mediaContentType",
                     "AlternativeName",
                     "GPSN",
                     "GPSE",
                     "Completeness",
                     "PriceListId",
                     "AccountLogoId",
                     "AUM"
                 ]
             }""";


}
