package services;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static services.BaseController.*;

public abstract class BaseTestClass {
    public static RequestSpecification specReg() {
        return given()
                .header("Authorization", "OAuth " + TOKEN)
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .log().all();
    }

    public static RequestSpecification specRegNoToken() {
        return given()
                .header("Authorization", "OAuth ")
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .log().all();
    }

    public static RequestSpecification specRegIncorrectToken() {
        return given()
                .header("Authorization", "OAuth " + TOKENINCORRECT)
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .log().all();
    }
}
