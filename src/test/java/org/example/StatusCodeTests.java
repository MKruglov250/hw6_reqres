package org.example;

import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpStatus;
import org.example.models.JobUser;
import org.example.models.RegisterUser;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Log4j2
public class StatusCodeTests extends BaseTest {

    JobUser user = new JobUser("morpheus", "leader", "", "");
    JobUser putUser = new JobUser("peter", "hero", "", "");
    JobUser patchUser = new JobUser("peter", "student", "", "");
    RegisterUser regUser = new RegisterUser("eve.holt@reqres.in","12345");
    RegisterUser badUser = new RegisterUser("mark@mail.com","");

    @Test(description = "GET List Users status code")
    public void getUsers(){
        log.info("Checking Status Code for List Users request");
        Response response = given().contentType("application/json")
                .queryParam("page","2").when().get("/api/users");
        int statusCode = response.statusCode();
        log.info("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, HttpStatus.SC_OK);
    }

    @Test(description = "GET Single User status code")
    public void getSingleUser(){
        log.info("Checking Status Code for Single User request");
        Response response = given().contentType("application/json")
                .when().get("/api/users/2");
        int statusCode = response.statusCode();
        log.info("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, HttpStatus.SC_OK);
    }

    @Test(description = "GET Single User Not Found status code")
    public void getSingleUserNotFound(){
        log.info("Checking Status Code for non-existing Single User request");
        Response response = given().contentType("application/json")
                .when().get("/api/users/23");
        int statusCode = response.statusCode();
        log.info("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, HttpStatus.SC_NOT_FOUND);
    }

    @Test(description = "GET Resources status code")
    public void getResources(){
        log.info("Checking Status Code for all Resources request");
        Response response = given().contentType("application/json")
                .when().get("/api/unknown");
        int statusCode = response.statusCode();
        log.info("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, HttpStatus.SC_OK);
    }

    @Test(description = "GET Single Resource status code")
    public void getSingleResources(){
        log.info("Checking Status Code for single Resource request");
        Response response = given().contentType("application/json")
                .when().get("/api/unknown/2");
        int statusCode = response.statusCode();
        log.info("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, HttpStatus.SC_OK);
    }

    @Test(description = "GET Single Resource not found status code")
    public void getResourcesNotFound(){
        log.info("Checking Status Code for non-existing Resource");
        Response response = given().contentType("application/json")
                .when().get("/api/unknown/23");
        int statusCode = response.statusCode();
        log.info("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, HttpStatus.SC_NOT_FOUND);
    }

    @Test(description = "POST For Create User")
    public void postCreateUser(){
        log.info("Checking Status Code for Create User request");
        Response response = given().contentType("application/json")
                .body(gson.toJson(user))
                .when().post("/api/users");
        int statusCode = response.statusCode();
        log.info("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, HttpStatus.SC_CREATED);
    }

    @Test(description = "PUT For Update User")
    public void putUpdateUser(){
        log.info("Checking Status Code for Update User (PUT) request");
        Response response = given().contentType("application/json")
                .body(gson.toJson(putUser))
                .when().put("/api/users/2");
        int statusCode = response.statusCode();
        log.info("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, HttpStatus.SC_OK);
    }

    @Test(description = "PATCH For Update User")
    public void patchUpdateUser(){
        log.info("Checking Status Code for Update User (PATCH) request");
        Response response = given().contentType("application/json")
                .body(gson.toJson(patchUser))
                .when().patch("/api/users/2");
        int statusCode = response.statusCode();
        log.info("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, HttpStatus.SC_OK);
    }

    @Test(description = "DELETE For Delete User")
    public void deleteUser(){
        log.info("Checking Status Code for DELETE user");
        Response response = given().contentType("application/json")
                .when().delete("/api/users/2");
        int statusCode = response.statusCode();
        log.info("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, HttpStatus.SC_NO_CONTENT);
    }

    @Test(description = "POST for Register Successful")
    public void registerSuccess(){
        log.info("Checking Status Code for Register (successful)");
        Response response = given().contentType("application/json")
                .body(gson.toJson(regUser))
                .when().post("/api/register");
        int statusCode = response.statusCode();
        log.info("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, HttpStatus.SC_OK);
    }

    @Test(description = "POST for Register Fail")
    public void registerFail(){
        log.info("Checking Status Code for Register (failed)");
        Response response = given().contentType("application/json")
                .body(gson.toJson(badUser))
                .when().post("/api/register");
        int statusCode = response.statusCode();
        log.info("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, HttpStatus.SC_BAD_REQUEST);
    }

    @Test(description = "POST for Login Success")
    public void loginSuccess(){
        log.info("Checking Status Code for Login (successful)");
        Response response = given().contentType("application/json")
                .body(gson.toJson(regUser))
                .when().post("/api/login");
        int statusCode = response.statusCode();
        log.info("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, HttpStatus.SC_OK);
    }

    @Test(description = "POST for Login Fail")
    public void loginFail(){
        log.info("Checking Status Code for Register (failed)");
        Response response = given().contentType("application/json")
                .body(gson.toJson(badUser))
                .when().post("/api/login");
        int statusCode = response.statusCode();
        log.info("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, HttpStatus.SC_BAD_REQUEST);
    }

    @Test(description = "GET List Users with Delay status code")
    public void getUsersDelay(){
        log.info("Checking Status Code for List Users request");
        Response response = given().contentType("application/json")
                .queryParam("page",2)
                .queryParam("delay", 3)
                .when().get("/api/users");
        int statusCode = response.statusCode();
        log.info("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, HttpStatus.SC_OK);
    }

}
