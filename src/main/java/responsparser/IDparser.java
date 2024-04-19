package responsparser;

import io.restassured.response.Response;

import java.util.List;

public class IDparser {

    public static List<String> parsIdFromIdResponseToList(Response response) {
        List<String> IDs;
        IDs = response.jsonPath().getList("value.Id");
        return IDs;
    }
}
