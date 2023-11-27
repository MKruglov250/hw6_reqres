package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

@Log4j2
public class StatusCodeTests extends BaseTest {


    @Test(description = "GET List Users status code")
    public void getUsers(){
        log.info("Checking Status Code for List Users request");
        Response response;
        response = RestAssured.given().contentType("application/json")
                .queryParam("page","2").when().get("/api/users");
        int statusCode = response.statusCode();
        System.out.println("Status code is: " + statusCode);
        log.info("Status code is " + statusCode);
        Assert.assertEquals(statusCode, HttpStatus.SC_OK);
    }

}
