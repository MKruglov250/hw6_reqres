package org.example;

import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpStatus;
import org.example.models.RegisterUser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

@Log4j2
public class ErrorsTest extends BaseTest {

    RegisterUser worstUser = new RegisterUser("","");

    @AfterMethod(description = "Log symbols")
    public void logDivision(){
        log.info("----------------------***TEST FINISHED***----------------------");
    }

    @Test(description = "Check Error Message: Register user without password")
    public void registerBadRequest(){
        log.info("Check Error Message: POST register user without password");
        Response response = requests.registerUser(badUser, HttpStatus.SC_BAD_REQUEST);
        response.then().assertThat().statusCode(400);
        log.info("Check that 'Missing Password' error returned");
        response.then().assertThat().body("error",equalTo("Missing password"));
    }

    @Test(description = "Check Error Message: Register user without email")
    public void registerWorstRequest(){
        log.info("Check Error Message: POST register user without email");
        Response response = requests.registerUser(worstUser, HttpStatus.SC_BAD_REQUEST);
        response.then().assertThat().statusCode(400);
        log.info("Check that 'Missing Username or Email' error returned");
        response.then().assertThat().body("error",equalTo("Missing email or username"));
    }

    @Test(description = "Check Error Message: Login user without password")
    public void loginBadRequest(){
        log.info("Check Error Message: POST login user without password");
        Response response = requests.loginUser(badUser, HttpStatus.SC_BAD_REQUEST);
        response.then().assertThat().statusCode(400);
        log.info("Check that 'Missing Password' error returned");
        response.then().assertThat().body("error",equalTo("Missing password"));
    }

}
