package version_1_3.api.jsonschemas;

public class FullLead {
    public static String FullLeadSchema = """
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
                      "Notes": {
                          "type": "string"
                      },
                      "Account": {
                          "type": "string"
                      },
                      "Contact": {
                          "type": "string"
                      },
                      "TitleId": {
                          "type": "string"
                      },
                      "FullJobTitle": {
                          "type": "string"
                      },
                      "StatusId": {
                          "type": "string"
                      },
                      "InformationSourceId": {
                          "type": "string"
                      },
                      "IndustryId": {
                          "type": "string"
                      },
                      "AnnualRevenueId": {
                          "type": "string"
                      },
                      "EmployeesNumberId": {
                          "type": "string"
                      },
                      "BusinesPhone": {
                          "type": "string"
                      },
                      "MobilePhone": {
                          "type": "string"
                      },
                      "Email": {
                          "type": "string"
                      },
                      "Fax": {
                          "type": "string"
                      },
                      "Website": {
                          "type": "string"
                      },
                      "AddressTypeId": {
                          "type": "string"
                      },
                      "CountryId": {
                          "type": "string"
                      },
                      "RegionId": {
                          "type": "string"
                      },
                      "CityId": {
                          "type": "string"
                      },
                      "Zip": {
                          "type": "string"
                      },
                      "Address": {
                          "type": "string"
                      },
                      "DoNotUseEmail": {
                          "type": "boolean"
                      },
                      "DoNotUsePhone": {
                          "type": "boolean"
                      },
                      "DoNotUseFax": {
                          "type": "boolean"
                      },
                      "DoNotUseSMS": {
                          "type": "boolean"
                      },
                      "DoNotUseMail": {
                          "type": "boolean"
                      },
                      "Commentary": {
                          "type": "string"
                      },
                      "QualifiedContactId": {
                          "type": "string"
                      },
                      "QualifiedAccountId": {
                          "type": "string"
                      },
                      "LeadName": {
                          "type": "string"
                      },
                      "ProcessListeners": {
                          "type": "integer"
                      },
                      "CountryStr": {
                          "type": "string"
                      },
                      "RegionStr": {
                          "type": "string"
                      },
                      "CityStr": {
                          "type": "string"
                      },
                      "WebFormId": {
                          "type": "string"
                      },
                      "LeadTypeId": {
                          "type": "string"
                      },
                      "LeadTypeStatusId": {
                          "type": "string"
                      },
                      "LeadDisqualifyReasonId": {
                          "type": "string"
                      },
                      "AccountCategoryId": {
                          "type": "string"
                      },
                      "AccountOwnershipId": {
                          "type": "string"
                      },
                      "DepartmentId": {
                          "type": "string"
                      },
                      "GenderId": {
                          "type": "string"
                      },
                      "JobId": {
                          "type": "string"
                      },
                      "DecisionRoleId": {
                          "type": "string"
                      },
                      "QualifyStatusId": {
                          "type": "string"
                      },
                      "Dear": {
                          "type": "string"
                      },
                      "QualificationProcessId": {
                          "type": "string"
                      },
                      "OwnerId": {
                          "type": "string"
                      },
                      "RemindToOwner": {
                          "type": "boolean"
                      },
                      "SalesOwnerId": {
                          "type": "string"
                      },
                      "Budget": {
                          "type": "number"
                      },
                      "MeetingDate": {
                          "type": "string"
                      },
                      "DecisionDate": {
                          "type": "string"
                      },
                      "ShowDistributionPage": {
                          "type": "boolean"
                      },
                      "BpmHref": {
                          "type": "string"
                      },
                      "BpmRef": {
                          "type": "string"
                      },
                      "RegisterMethodId": {
                          "type": "string"
                      },
                      "LeadSourceId": {
                          "type": "string"
                      },
                      "LeadMediumId": {
                          "type": "string"
                      },
                      "OpportunityDepartmentId": {
                          "type": "string"
                      },
                      "IdentificationPassed": {
                          "type": "boolean"
                      },
                      "CampaignId": {
                          "type": "string"
                      },
                      "BulkEmailId": {
                          "type": "string"
                      },
                      "Qualified": {
                          "type": "integer"
                      },
                      "SaleParticipant": {
                          "type": "integer"
                      },
                      "QualifiedPercent": {
                          "type": "number"
                      },
                      "SalePercent": {
                          "type": "number"
                      },
                      "StartLeadManagementProcess": {
                          "type": "boolean"
                      },
                      "SaleType": {
                          "type": "string"
                      },
                      "Score": {
                          "type": "number"
                      },
                      "QualificationPassed": {
                          "type": "boolean"
                      },
                      "EventId": {
                          "type": "string"
                      },
                      "NextActualizationDate": {
                          "type": "string"
                      },
                      "BpmSessionId": {
                          "type": "string"
                      },
                      "PredictiveScore": {
                          "type": "integer"
                      },
                      "MovedToFinalStateOn": {
                          "type": "string"
                      },
                      "OpportunityId": {
                          "type": "string"
                      },
                      "PartnerId": {
                          "type": "string"
                      },
                      "PartnerOwnerId": {
                          "type": "string"
                      },
                      "PartnerTypeId": {
                          "type": "string"
                      },
                      "SalesChannelId": {
                          "type": "string"
                      },
                      "OrderId": {
                          "type": "string"
                      },
                      "LeadGenExtendedLeadInfoId": {
                          "type": "string"
                      },
                      "SocialMetadata": {
                          "type": "string"
                      }
                  },
                  "additionalProperties": false,
                  "required": [
                      "@odata.context",
                      "Id",
                      "CreatedOn",
                      "CreatedById",
                      "ModifiedOn",
                      "ModifiedById",
                      "Notes",
                      "Account",
                      "Contact",
                      "TitleId",
                      "FullJobTitle",
                      "StatusId",
                      "InformationSourceId",
                      "IndustryId",
                      "AnnualRevenueId",
                      "EmployeesNumberId",
                      "BusinesPhone",
                      "MobilePhone",
                      "Email",
                      "Fax",
                      "Website",
                      "AddressTypeId",
                      "CountryId",
                      "RegionId",
                      "CityId",
                      "Zip",
                      "Address",
                      "DoNotUseEmail",
                      "DoNotUsePhone",
                      "DoNotUseFax",
                      "DoNotUseSMS",
                      "DoNotUseMail",
                      "Commentary",
                      "QualifiedContactId",
                      "QualifiedAccountId",
                      "LeadName",
                      "ProcessListeners",
                      "CountryStr",
                      "RegionStr",
                      "CityStr",
                      "WebFormId",
                      "LeadTypeId",
                      "LeadTypeStatusId",
                      "LeadDisqualifyReasonId",
                      "AccountCategoryId",
                      "AccountOwnershipId",
                      "DepartmentId",
                      "GenderId",
                      "JobId",
                      "DecisionRoleId",
                      "QualifyStatusId",
                      "Dear",
                      "QualificationProcessId",
                      "OwnerId",
                      "RemindToOwner",
                      "SalesOwnerId",
                      "Budget",
                      "MeetingDate",
                      "DecisionDate",
                      "ShowDistributionPage",
                      "BpmHref",
                      "BpmRef",
                      "RegisterMethodId",
                      "LeadSourceId",
                      "LeadMediumId",
                      "OpportunityDepartmentId",
                      "IdentificationPassed",
                      "CampaignId",
                      "BulkEmailId",
                      "Qualified",
                      "SaleParticipant",
                      "QualifiedPercent",
                      "SalePercent",
                      "StartLeadManagementProcess",
                      "SaleType",
                      "Score",
                      "QualificationPassed",
                      "EventId",
                      "NextActualizationDate",
                      "BpmSessionId",
                      "PredictiveScore",
                      "MovedToFinalStateOn",
                      "OpportunityId",
                      "PartnerId",
                      "PartnerOwnerId",
                      "PartnerTypeId",
                      "SalesChannelId",
                      "OrderId",
                      "LeadGenExtendedLeadInfoId",
                      "SocialMetadata"
                  ]
              }""";


}
