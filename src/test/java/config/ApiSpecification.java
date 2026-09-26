package config;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.lessThan;

public final class ApiSpecification {
  private ApiSpecification() {
  }

  public static RequestSpecification requestSpec() {
    return new RequestSpecBuilder()
        .setBaseUri(ApiConfig.BASE_URL)
        .setContentType(ApiConfig.CONTENT_TYPE)
        .build();
  }

  public static ResponseSpecification responseSpec() {
    return new ResponseSpecBuilder()
        .expectResponseTime(lessThan(5000L))
        .build();
  }
}
