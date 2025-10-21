package trany.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @JsonProperty("UserName")
    public String name;
    @JsonProperty("UserPassword")
    public String pass;
    public boolean isCanManageSolution;
    public boolean isCanViewConfiguration;
}
/*
{
  "UserName":"Supervisor",
  "UserPassword":"BPMAdmin123!"
}
*/

