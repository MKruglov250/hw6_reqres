package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;

public class BaseTest {

    Gson gson;

    public BaseTest() {
        gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        RestAssured.baseURI="https://reqres.in/";
    }
}