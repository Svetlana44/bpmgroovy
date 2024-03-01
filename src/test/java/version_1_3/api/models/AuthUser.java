package version_1_3.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthUser {

    @JsonProperty("UserName")
    private String userName;

    @JsonProperty("UserPassword")
    private String userPassword;

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    @Override
    public String toString() {
        return
                "AuthUser{" +
                        "userName = '" + userName + '\'' +
                        ",userPassword = '" + userPassword + '\'' +
                        "}";
    }
}