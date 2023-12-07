package org.example;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.Matchers.*;

@Log4j2
public class ExactContentTest extends BaseTest{

    @AfterMethod(description = "Log symbols")
    public void logDivision(){
        log.info("----------------------***TEST FINISHED***----------------------");
    }

    @Test(description = "Exact Content: GET Users, assert top 5 values")
    public void getUsers(){
        log.info("Checking Data Values for List Users request");
        Response response = requests.getUsers(2, HttpStatus.SC_OK);
        log.info("Check values of returned JSON body");
        response.then().assertThat()
                .body("page", equalTo(2))
                .body("per_page", equalTo(6))
                .body("total", equalTo(12))
                .body("total_pages", equalTo(2))
                .body("data", hasSize(6));
    }

    @Test(description = "Exact Content: GET Users, assert full JSON")
    public void getUsersFull(){
        log.info("Checking Full JSON for List Users request");
        JsonPath expectedJson = new JsonPath(new File("src/test/resources/references/listUsersRef.json"));
        Response response = requests.getUsers(2, HttpStatus.SC_OK);
        log.info("Check values of returned JSON body");
        response.then().assertThat()
                .body("",equalTo(expectedJson.getMap("")));
    }

    @Test(description = "Exact Content: GET Single User, assert full JSON")
    public void getSingleUserFull(){
        log.info("Checking Full JSON for Single User request");
        JsonPath expectedJson = new JsonPath(new File("src/test/resources/references/singleUserRef.json"));
        Response response = requests.getSingleUser(2, HttpStatus.SC_OK);
        log.info("Check values of returned JSON body");
        response.then().assertThat()
                .body("",equalTo(expectedJson.getMap("")));
    }


    @Test(description = "Exact Content: GET Single Resource, assert full JSON")
    public void getSingleResourceFull(){
        log.info("Checking Full JSON for Single Resource request");
        JsonPath expectedJson = new JsonPath(new File("src/test/resources/references/singleResourceRef.json"));
        Response response = requests.getSingleResource(2, HttpStatus.SC_OK);
        log.info("Check values of returned JSON body");
        response.then().assertThat()
                .body("",equalTo(expectedJson.getMap("")));
    }
}