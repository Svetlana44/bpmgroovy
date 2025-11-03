package api.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SysUser {

    @JsonProperty("LastActivityTime")
    private String lastActivityTime;

    @JsonProperty("Description")
    private String description;

    @JsonProperty("AccountId")
    private String accountId;

    @JsonProperty("Email")
    private String email;

    @JsonProperty("ModifiedOn")
    private String modifiedOn;

    @JsonProperty("LDAPEntryId")
    private String lDAPEntryId;

    @Default
    @JsonProperty("SysCulture")
    private String sysCulture = "1a778e3f-0a8e-e111-84a3-00155d054c03";

    @JsonProperty("DateTimeFormatId")
    private String dateTimeFormatId;

    @JsonProperty("SysAdminUnitType")
    @Default
    private String sysAdminUnitType = "472e97c7-6bd7-df11-9b2a-001d60e938c6";

    @JsonProperty("SynchronizeWithLDAP")
    @Default
    private Boolean synchronizeWithLDAP = false;

    @JsonProperty("Contact")
    private String contact;

    @JsonProperty("ProcessListeners")
    private Integer processListeners;

    @JsonProperty("AccountExpireTime")
    private String accountExpireTime;

    @JsonProperty("Name")
    private String name;

    //поле пароль не выдаётся запросом
    @JsonProperty("UserPassword")
    @Default
    //  private String userPassword;
    private String userPassword = "yzMg7cbiadm7q32YnTwD7eY2za/eplHaTLQsg/G3jcNFzt64CyIqK";

    @JsonProperty("LDAPElement")
    private String lDAPElement;

    @JsonProperty("TimeZone")
    private String timeZone;

    @JsonProperty("SessionTimeout")
    @Default
    private Integer sessionTimeout = 0;

    @JsonProperty("CreatedById")
    private String createdById;

    @JsonProperty("LDAPEntryDN")
    private String lDAPEntryDN;

    @JsonProperty("ForceChangePassword")
    @Default
    private Boolean forceChangePassword = false;

    @JsonProperty("CreatedOn")
    private String createdOn;

    @JsonProperty("IsDirectoryEntry")
    @Default
    private Boolean isDirectoryEntry = false;

    @JsonProperty("UnblockTime")
    private String unblockTime;

    @JsonProperty("ModifiedById")
    private String modifiedById;

    @JsonProperty("PasswordExpireDate")
    private String passwordExpireDate;

    @JsonProperty("OpenIDSub")
    private String openIDSub;

    @JsonProperty("@odata.context")
    private String odataContext;

    @JsonProperty("LoggedIn")
    @Default
    private Boolean loggedIn = false;

    @JsonProperty("SourceControlLogin")
    private String sourceControlLogin;

    @JsonProperty("Active")
    @Default
    private Boolean active = true;

    @JsonProperty("ParentRoleId")
    private String parentRoleId;

    @JsonProperty("LDAPElementId")
    private String lDAPElementId;

    @JsonProperty("TimeZoneId")
    private String timeZoneId;

    @JsonProperty("Disable2FA")
    @Default
    private Boolean disable2FA = false;

    @JsonProperty("Id")
    private String id;

    @JsonProperty("HomePageId")
    private String homePageId;

    @JsonProperty("AccountBeginTime")
    private String accountBeginTime;

    @JsonProperty("PortalAccountId")
    private String portalAccountId;

    @JsonProperty("UserConnectionType")
    @Default
    private String userConnectionType = "df8ff7a2-c9bf-4f28-80b1-ed16fa77818d";

    @JsonProperty("ConnectionType")
    @Default
    private Integer connectionType = 0;

    @JsonProperty("CreatedBy")
    private String createdBy;

    @JsonProperty("ModifiedBy")
    private String modifiedBy;
}
//{"jsonObject":"{\"Id\":\"d0d5b229-1778-4e85-9c51-fd5d39399195\",\"CreatedBy\":\"410006e1-ca4e-4502-a9ec-e54d922d2c00\",\"ModifiedBy\":\"410006e1-ca4e-4502-a9ec-e54d922d2c00\",\"Name\":\"SVETuser32603333\",\"SysAdminUnitType\":\"472e97c7-6bd7-df11-9b2a-001d60e938c6\",\"Contact\":\"f7aac61b-28fb-448c-86f1-f2a4a99c8cbb\",\"TimeZone\":null,\"UserPassword\":\"SVETuser3260!3333\",\"Active\":true,\"SynchronizeWithLDAP\":false,\"SysCulture\":\"1a778e3f-0a8e-e111-84a3-00155d054c03\",\"ConnectionType\":0,\"LDAPElement\":null,\"UserConnectionType\":\"df8ff7a2-c9bf-4f28-80b1-ed16fa77818d\"}","roleId":"a29a3ba5-4b0d-de11-9a51-005056c00008"}