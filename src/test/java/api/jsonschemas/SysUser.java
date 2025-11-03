package api.jsonschemas;

public class SysUser {
    public static String FullSysUserSchema = """
            {
              "$schema": "http://json-schema.org/draft-07/schema#",
              "title": "Generated schema for Root",
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
                "Name": {
                  "type": "string"
                },
                "Description": {
                  "type": "string"
                },
                "SysAdminUnitTypeValue": {
                  "type": "number"
                },
                "ParentRoleId": {
                  "type": "string"
                },
                "ContactId": {
                  "type": "string"
                },
                "AccountId": {
                  "type": "string"
                },
                "IsDirectoryEntry": {
                  "type": "boolean"
                },
                "TimeZoneId": {
                  "type": "string"
                },
                "Active": {
                  "type": "boolean"
                },
                "SynchronizeWithLDAP": {
                  "type": "boolean"
                },
                "LDAPEntry": {
                  "type": "string"
                },
                "LDAPEntryId": {
                  "type": "string"
                },
                "LDAPEntryDN": {
                  "type": "string"
                },
                "LoggedIn": {
                  "type": "boolean"
                },
                "ProcessListeners": {
                  "type": "number"
                },
                "SysCultureId": {
                  "type": "string"
                },
                "LoginAttemptCount": {
                  "type": "number"
                },
                "SourceControlLogin": {
                  "type": "string"
                },
                "PasswordExpireDate": {
                  "type": "string"
                },
                "HomePageId": {
                  "type": "string"
                },
                "ConnectionType": {
                  "type": "number"
                },
                "UnblockTime": {
                  "type": "string"
                },
                "ForceChangePassword": {
                  "type": "boolean"
                },
                "DateTimeFormatId": {
                  "type": "string"
                },
                "SessionTimeout": {
                  "type": "number"
                },
                "PortalAccountId": {
                  "type": "string"
                },
                "LDAPElementId": {
                  "type": "string"
                },
                "Disable2FA": {
                  "type": "boolean"
                },
                "Email": {
                  "type": "string"
                },
                "OpenIDSub": {
                  "type": "string"
                },
                "AccountBeginTime": {
                  "type": "string"
                },
                "AccountExpireTime": {
                  "type": "string"
                },
                "LastActivityTime": {
                  "type": "string"
                }
              },
              "required": [
                "@odata.context",
                "Id",
                "CreatedOn",
                "CreatedById",
                "ModifiedOn",
                "ModifiedById",
                "Name",
                "Description",
                "SysAdminUnitTypeValue",
                "ParentRoleId",
                "ContactId",
                "AccountId",
                "IsDirectoryEntry",
                "TimeZoneId",
                "Active",
                "SynchronizeWithLDAP",
                "LDAPEntry",
                "LDAPEntryId",
                "LDAPEntryDN",
                "LoggedIn",
                "ProcessListeners",
                "SysCultureId",
                "LoginAttemptCount",
                "SourceControlLogin",
                "PasswordExpireDate",
                "HomePageId",
                "ConnectionType",
                "UnblockTime",
                "ForceChangePassword",
                "DateTimeFormatId",
                "SessionTimeout",
                "PortalAccountId",
                "LDAPElementId",
                "Disable2FA",
                "Email",
                "OpenIDSub",
                "AccountBeginTime",
                "AccountExpireTime",
                "LastActivityTime"
              ]
            }""";
}
