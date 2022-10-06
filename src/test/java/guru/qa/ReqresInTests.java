package guru.qa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.core.Is.is;

public class ReqresInTests {
    @Test
    @DisplayName("Позитивный. Тест на регистрацию")
    void registerSuccessfulTest(){
        String JSONbody = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\" }";
        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(JSONbody)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("id", is(4));
    }
    @Test
    @DisplayName("Позитивный. Тест на авторизацию")
    void loginSuccessfulTest() {
        String JSONbody = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }";
        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(JSONbody)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }
    @Test
    @DisplayName("Негативный. Тест на регистрацию")
    void registerUnuccessfulTest() {
        String JSONbody = "{ \"email\": \"sydney@fife\" }";
        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(JSONbody)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }
    @Test
    @DisplayName("Негативный. Тест на авторизацию")
    void loginUnuccessfulTest() {
        String JSONbody = "{ \"email\": \"peter@klaven\" }";
        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(JSONbody)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }
    @Test
    @DisplayName("Негативный. Тест на авторизацию")
    void loginUnuccessfulTest1() {
        String JSONbody = "{ \"password\": \"cityslicka\" }";
        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(JSONbody)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing email or username"));
    }

}
