package tests.users;

import base.BaseTest;
import data.UserDataFactory;
import io.restassured.response.Response;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import requests.UsersRequest;
import static org.hamcrest.Matchers.*;
import static utils.ResponseUtils.getId;

class UsersTest extends BaseTest {
  private final UsersRequest usersRequest = new UsersRequest();

  @Test
  void deveCriarUsuarioAdministrador() {
    Response response = usersRequest.create(UserDataFactory.userAdmin());
    response.then()
        .statusCode(201)
        .body("message", equalTo("Cadastro realizado com sucesso"));
    usersRequest.delete(Map.of("_id", getId(response))).then()
        .statusCode(200);
  }

  @Test
  void deveCriarUsuarioComum() {
    Response response = usersRequest.create(UserDataFactory.validCommonUser());
    response.then()
        .statusCode(201)
        .body("message", equalTo("Cadastro realizado com sucesso"));
    usersRequest.delete(Map.of("_id", getId(response))).then()
        .statusCode(200);
  }

  @Test
  void deveCListarUsuarios() {
    usersRequest.findUsers(Map.of()).then()
        .statusCode(200)
        .body("quantidade", greaterThan(0))
        .body(matchesJsonSchemaInClasspath("schemas/getUsers.schema.json"));
  }

  @Test
  void deveListarUsuarioPorId() {
    Response response = usersRequest.create(UserDataFactory.userAdmin());
    response.then()
        .statusCode(201)
        .extract()
        .path("_id");
    // String id = response.path("_id");
    usersRequest.findUsers(Map.of("_id", getId(response))).then()
        .statusCode(200)
        .body("quantidade", greaterThan(0))
        .body(matchesJsonSchemaInClasspath("schemas/getUsers.schema.json"))
        .body("usuarios[0].nome", equalTo(UserDataFactory.userAdmin().nome()))
        .body("usuarios[0].email", equalTo(UserDataFactory.userAdmin().email()))
        .body("usuarios[0].password", equalTo(UserDataFactory.userAdmin().password()))
        .body("usuarios[0].administrador", equalTo(UserDataFactory.userAdmin().administrador()))
        .body("usuarios[0]._id", not(blankOrNullString()));
    usersRequest.delete(Map.of("_id", getId(response))).then()
        .statusCode(200)
        .body("message", equalTo("Registro excluído com sucesso"));
  }

  @Test
  void deveListarUsuarioPorNome() {
    Response response = usersRequest.create(UserDataFactory.userAdmin());
    response.then()
        .statusCode(201)
        .body("message", equalTo("Cadastro realizado com sucesso"))
        .body("_id", not(blankOrNullString()));
    usersRequest.findUsers(Map.of("nome", UserDataFactory.userAdmin().nome())).then()
        .statusCode(200)
        .body("quantidade", greaterThan(0))
        .body("usuarios[0].nome", equalTo(UserDataFactory.userAdmin().nome()))
        .body("usuarios[0].email", equalTo(UserDataFactory.userAdmin().email()))
        .body("usuarios[0].password", equalTo(UserDataFactory.userAdmin().password()))
        .body("usuarios[0].administrador", equalTo(UserDataFactory.userAdmin().administrador()))
        .body("usuarios[0]._id", not(blankOrNullString()))
        .body(matchesJsonSchemaInClasspath("schemas/getUsers.schema.json"));
    usersRequest.delete(Map.of("_id", getId(response))).then()
        .statusCode(200);
  }

  @Test
  void deveListarUsuarioPorEmail() {
    Response response = usersRequest.create(UserDataFactory.userAdmin());
    response.then()
        .statusCode(201)
        .body("message", equalTo("Cadastro realizado com sucesso"))
        .body("_id", not(blankOrNullString()));
    usersRequest.findUsers(Map.of("email", UserDataFactory.userAdmin().email())).then()
        .statusCode(200)
        .body("quantidade", greaterThan(0))
        .body("usuarios[0].nome", equalTo(UserDataFactory.userAdmin().nome()))
        .body("usuarios[0].email", equalTo(UserDataFactory.userAdmin().email()))
        .body("usuarios[0].password", equalTo(UserDataFactory.userAdmin().password()))
        .body("usuarios[0].administrador", equalTo(UserDataFactory.userAdmin().administrador()))
        .body(matchesJsonSchemaInClasspath("schemas/getUsers.schema.json"));
    usersRequest.delete(Map.of("_id", getId(response))).then()
        .statusCode(200);
  }





  @Test
  void deveDeletarUsuario() {
    Response response = usersRequest.create(UserDataFactory.userAdmin());
    response.then()
        .statusCode(201)
        .body("message", equalTo("Cadastro realizado com sucesso"));
    usersRequest.delete(Map.of("_id", getId(response))).then()
        .statusCode(200)
        .body("message", equalTo("Registro excluído com sucesso"));
  }
}
