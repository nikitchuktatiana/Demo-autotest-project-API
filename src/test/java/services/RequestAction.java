package services;
import io.qameta.allure.Step;
import io.restassured.http.Method;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.given;

public final class RequestAction {
    @Step("Request {method} to endpoint - {endpoint}")
    public static ValidatableResponse methodExucute(Method method, String endpoint, RequestSpecification reqSpec, ResponseSpecification respSpec) {
        return given()
                .spec(reqSpec)
                .when().log().all()
                .request(method, endpoint)
                .then().log().all()
                .spec(respSpec);
    }
}
