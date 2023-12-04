package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseApi {
    Gson gson;
    ContentType contentType = ContentType.JSON;
    public RequestSpecification requestSpecification;

    public BaseApi() {
        gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        RestAssured.baseURI="https://reqres.in/";
        requestSpecification = given().header("content-type",contentType)
                .log().all();
    }

    public Response get(String endpoint, int expectedCode){
        return requestSpecification.when()
                .get(endpoint).then().statusCode(expectedCode)
                .extract().response();
    }

    public Response post(String endpoint, int expectedCode, String body){
        return requestSpecification.body(body).when()
                .post(endpoint).then().statusCode(expectedCode)
                .extract().response();
    }

    public Response put(String endpoint, int expectedCode, String body){
        return requestSpecification.body(body).when()
                .put(endpoint).then().statusCode(expectedCode)
                .extract().response();
    }

    public Response patch(String endpoint, int expectedCode, String body){
        return requestSpecification.body(body).when()
                .patch(endpoint).then().statusCode(expectedCode)
                .extract().response();
    }

    public Response delete(String endpoint, int expectedCode){
        return requestSpecification.when()
                .delete(endpoint).then().statusCode(expectedCode)
                .extract().response();
    }

    public void restoreSpecification(){
        requestSpecification = given().header("content-type",contentType)
                .log().all();
    }
}
