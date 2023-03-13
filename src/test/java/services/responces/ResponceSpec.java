package services.responces;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matcher;

import java.util.ArrayList;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;

public class ResponceSpec {
    public static ResponseSpecification specRespSimpleOk200() {
        return new ResponseSpecBuilder().expectStatusCode(SC_OK).build();
    }

    public static ResponseSpecification specRespCreateOk201() {
        return new ResponseSpecBuilder().expectStatusCode(SC_CREATED).build();
    }

    public static ResponseSpecification specRespErrorCodeAndMess(int statuscode, String message) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statuscode)
                .expectBody("message", equalTo(message))
                .build();
    }

    public static ResponseSpecification specRespErrorCode(int statuscode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statuscode)
                .build();
    }

    public static ResponseSpecification specRespAnyCode(int statusCode1, int statusCode2) {
        return new ResponseSpecBuilder()
                .expectStatusCode(anyOf(equalTo(statusCode1), equalTo(statusCode2)))
                .build();
    }

    public static ResponseSpecification specRespVariableLengthArg(int... statusCode) {
        //ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        ArrayList<Matcher<Integer>> val = new ArrayList();
        for (int i : statusCode) {
            val.add(equalTo(i));
        }
        return new ResponseSpecBuilder().expectStatusCode(anyOf((Iterable) val)).build();
    }

    public static ResponseSpecification specRespSimpleDel204() {
        return new ResponseSpecBuilder().expectStatusCode(SC_NO_CONTENT).build();
    }

}
