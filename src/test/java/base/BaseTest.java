package base;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import data.UserDataFactory;

import static config.ApiSpecification.requestSpec;
import static config.ApiSpecification.responseSpec;

import java.util.Map;

import requests.UsersRequest;

public abstract class BaseTest {
  private static final UsersRequest usersRequest = new UsersRequest();

  @BeforeAll
  static void setup() {
    RestAssured.requestSpecification = requestSpec();
    RestAssured.responseSpecification = responseSpec();
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

    

    Response response = usersRequest.findUsers(Map.of("nome", UserDataFactory.userAdmin().nome()));
    response.then()
        .statusCode(200);

        int quantidade = response.jsonPath().getInt("quantidade");

        if(quantidade > 0){
          String id = response.jsonPath().getString("usuarios[0]._id");

          usersRequest.delete(Map.of("_id", id)).then()
              .statusCode(200);

        }


  }

  @AfterAll
  static void tearDown() {
    RestAssured.reset();
  }
}
