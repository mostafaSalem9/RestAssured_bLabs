package api.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Base {
    public Response postRequest(String endpoint, Object payload, String apiKey) {
        Response response = given()
                .header("API-Key", apiKey)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)

                .body(payload)
                .when()
                .post(endpoint);
        return response;
    }

    public Response getRequest(String endpoint) {
        Response response = given()
                .when()
                .get(endpoint);
        return response;
    }

    public Response putRequest(String endpoint, Object payload) {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .put(endpoint);
        return response;
    }
}
