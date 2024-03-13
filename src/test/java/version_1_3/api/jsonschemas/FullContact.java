package version_1_3.api.jsonschemas;

public class FullContact {
    public static String FullContactSchema = """
            {
                "$schema": "http://json-schema.org/draft-07/schema#",
                "type": "object",
                "properties": {
                    "@odata.context": {
                        "type": "string"
                    },
                    "value": {
                        "type": "array",
                        "items": {
                            "type": "object",
                            "properties": {
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
                                "Dear": {
                                    "type": "string"
                                },
                                "SalutationTypeId": {
                                    "type": "string"
                                },
                                "GenderId": {
                                    "type": "string"
                                },
                                "AccountId": {
                                    "type": "string"
                                },
                                "DecisionRoleId": {
                                    "type": "string"
                                },
                                "TypeId": {
                                    "type": "string"
                                },
                                "JobId": {
                                    "type": "string"
                                },
                                "JobTitle": {
                                    "type": "string"
                                },
                                "DepartmentId": {
                                    "type": "string"
                                },
                                "BirthDate": {
                                    "type": "string"
                                },
                                "Phone": {
                                    "type": "string"
                                },
                                "MobilePhone": {
                                    "type": "string"
                                },
                                "HomePhone": {
                                    "type": "string"
                                },
                                "Skype": {
                                    "type": "string"
                                },
                                "Email": {
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
                                "DoNotUseEmail": {
                                    "type": "boolean"
                                },
                                "DoNotUseCall": {
                                    "type": "boolean"
                                },
                                "DoNotUseFax": {
                                    "type": "boolean"
                                },
                                "DoNotUseSms": {
                                    "type": "boolean"
                                },
                                "DoNotUseMail": {
                                    "type": "boolean"
                                },
                                "Notes": {
                                    "type": "string"
                                },
                                "ContactPhoto@odata.mediaEditLink": {
                                    "type": "string"
                                },
                                "ContactPhoto@odata.mediaReadLink": {
                                    "type": "string"
                                },
                                "ContactPhoto@odata.mediaContentType": {
                                    "type": "string"
                                },
                                "PhotoId": {
                                    "type": "string"
                                },
                                "GPSN": {
                                    "type": "string"
                                },
                                "GPSE": {
                                    "type": "string"
                                },
                                "Surname": {
                                    "type": "string"
                                },
                                "GivenName": {
                                    "type": "string"
                                },
                                "MiddleName": {
                                    "type": "string"
                                },
                                "Confirmed": {
                                    "type": "boolean"
                                },
                                "IsNonActualEmail": {
                                    "type": "boolean"
                                },
                                "Completeness": {
                                    "type": "integer"
                                },
                                "LanguageId": {
                                    "type": "string"
                                },
                                "Age": {
                                    "type": "integer"
                                },
                                "IsEmailConfirmed": {
                                    "type": "boolean"
                                }
                            },
                            "additionalProperties": false,
                            "required": [
                                "Id",
                                "Name",
                                "OwnerId",
                                "CreatedOn",
                                "CreatedById",
                                "ModifiedOn",
                                "ModifiedById",
                                "ProcessListeners",
                                "Dear",
                                "SalutationTypeId",
                                "GenderId",
                                "AccountId",
                                "DecisionRoleId",
                                "TypeId",
                                "JobId",
                                "JobTitle",
                                "DepartmentId",
                                "BirthDate",
                                "Phone",
                                "MobilePhone",
                                "HomePhone",
                                "Skype",
                                "Email",
                                "AddressTypeId",
                                "Address",
                                "CityId",
                                "RegionId",
                                "Zip",
                                "CountryId",
                                "DoNotUseEmail",
                                "DoNotUseCall",
                                "DoNotUseFax",
                                "DoNotUseSms",
                                "DoNotUseMail",
                                "Notes",
                                "ContactPhoto@odata.mediaEditLink",
                                "ContactPhoto@odata.mediaReadLink",
                                "ContactPhoto@odata.mediaContentType",
                                "PhotoId",
                                "GPSN",
                                "GPSE",
                                "Surname",
                                "GivenName",
                                "MiddleName",
                                "Confirmed",
                                "IsNonActualEmail",
                                "Completeness",
                                "LanguageId",
                                "Age",
                                "IsEmailConfirmed"
                            ]
                        }
                    }
                },
                "additionalProperties": false,
                "required": [
                    "@odata.context",
                    "value"
                ]
            }""";


}
