package utils;

import io.restassured.response.Response;

public final class ResponseUtils {
  private ResponseUtils() {
  }

  public static String getId(Response response) {
    return response.jsonPath().getString("_id");
  }
}