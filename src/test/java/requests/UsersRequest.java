package requests;

import data.UserData;
import io.restassured.response.Response;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class UsersRequest {
  private static final String USERS_ENDPOINT = "/usuarios";

  public Response create(UserData user) {
    return given()
        .body(user)
        .when()
        .post(USERS_ENDPOINT);
  }

  public Response findUserId(Map<String, ?> pathParams) {
    return given()
        .pathParams(pathParams)
        .when()
        .get(USERS_ENDPOINT);
  }

  public Response findUsers(Map<String, ?> queryParams) {
    return given()
        .queryParams(queryParams)
        .when()
        .get(USERS_ENDPOINT);
  }

  public Response delete(Map<String, ?> pathParams) {
    return given()
        .pathParams(pathParams)
        .when()
        .delete(USERS_ENDPOINT + "/{_id}");
  }
}
